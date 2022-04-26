package car.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import car.model.Buyer;
import car.model.Reviews;
import car.model.Sellers;
import car.model.Users;

/**
 * Reviews Dao
 * @author yansen
 *
 */
public class ReviewsDao{
	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	
	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
			"INSERT INTO Reviews(Date, ReviewContent, Rating, BuyerId, SellerId) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Reviews has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setTimestamp(1, new Timestamp(review.getDate().getTime()));
			
			insertStmt.setString(2, review.getReviewContent());
			insertStmt.setDouble(3, review.getRating());
			insertStmt.setInt(4, review.getBuyer().getUserId());
			insertStmt.setInt(5, review.getSeller().getUserId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	/**
	 * Get the Reviews record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Reviews instance.
	 * to join the Reviews, BlogUsers tables and then build each object.
	 */
	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectReview =
			"SELECT ReviewID, Date, ReviewContent, Rating, BuyerId, SellerId " +
			"FROM Reviews " +
			"WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UserDao userDao = UserDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date date =  new Date(results.getTimestamp("Date").getTime());
				String reviewContent = results.getString("ReviewContent");
				double rating = results.getDouble("Rating");
				int buyerId = results.getInt("BuyerId");
				int sellerId = results.getInt("SellerId");
				Users buyer = userDao.getUserByUserId(buyerId);
				Users seller = userDao.getUserByUserId(sellerId);
				Reviews review = new Reviews(resultReviewId,date,reviewContent,rating,buyer,seller);
				return review;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	/**
	 * Get the Reviews record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a list of Reviews.
	 */
	public List<Reviews> getReviewByUserId(int userId) throws SQLException {
		List<Reviews> reviews = new ArrayList<>();
		String selectReview =
			"SELECT ReviewID, Date, ReviewContent, Rating, BuyerId, SellerId" +
			"FROM Reviews INNER JOIN Buyers ON Reviews.BuyerId = Buyers.BuyerId" +
			"WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UserDao userDao = UserDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date date =  new Date(results.getTimestamp("Date").getTime());
				String reviewContent = results.getString("ReviewContent");
				double rating = results.getDouble("Rating");
				int buyerId = results.getInt("BuyerId");
				int sellerId = results.getInt("SellerId");
				Users buyer = userDao.getUserByUserId(buyerId);
				Users seller = userDao.getUserByUserId(sellerId);
				Reviews review = new Reviews(resultReviewId,date,reviewContent,rating,buyer,seller);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return reviews;
	}
	
	
	/**
	 * Update the fields of the Reviews instance.
	 * This runs a UPDATE statement.
	 */
	public Reviews updateReview(Reviews review,String newReviewContent, double newRating) throws SQLException {
		String updateReview = "UPDATE Reviews SET ReviewContent=?,Rating=? WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReview);
			updateStmt.setString(1, newReviewContent);
			updateStmt.setDouble(2, newRating);
			updateStmt.setInt(3, review.getReviewId());
			updateStmt.executeUpdate();

			// Update the blogPost param before returning to the caller.
			review.setReviewContent(newReviewContent);
			review.setRating(newRating);
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	
	/**
	 * Delete the Reviews instance.
	 * This runs a DELETE statement.
	 */
	public Reviews delete(Reviews reviews) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReveiwId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, reviews.getReviewId());
			deleteStmt.executeUpdate();
			// Return null so the caller can no longer operate on the BlogPosts instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	


}