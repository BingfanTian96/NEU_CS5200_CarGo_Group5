package car.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import car.model.*;
/**
 * 
 * @author Bo Chen
 *
 */
public class BuyerDao extends UserDao{
	// Single pattern: instantiation is limited to one object.
	private static BuyerDao instance = null;
	protected BuyerDao() {
		super();
	}
	public static BuyerDao getInstance() {
		if(instance == null) {
			instance = new BuyerDao();
		}
		return instance;
	}
	
	/**
	 * Save the Buyer instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Buyer create(Buyer buyer) throws SQLException {
        // Insert into the superclass table first.
		Users user = create(new Users(buyer.getFirstName(), buyer.getLastName(),
        buyer.getEmail(), buyer.getPassword()));

		String insertBuyer = "INSERT INTO Buyers(UserId,DOB,Zip) " +
        "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
      
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBuyer);
		
			insertStmt.setInt(1, user.getUserId());
            insertStmt.setTimestamp(2, new Timestamp(buyer.getDob().getTime()));
			insertStmt.setInt(3, buyer.getZip());

			insertStmt.executeUpdate();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			
			return buyer;
			
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
		}
	}

    public Buyer getBuyerByUserId(int userId) throws SQLException {
		String selectBuyer = "SELECT UserId,DOB,ZIP FROM Buyers WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBuyer);
			selectStmt.setInt(1, userId);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int resultUserId = results.getInt("UserId");
				Date dob = results.getDate("DOB");
				int zip = results.getInt("ZIP");
				
			
				Buyer buyer = new Buyer(resultUserId, dob, zip);
				return buyer;
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

    public List<Buyer> getBuyersFromZip(int zip) throws SQLException {
		List<Buyer> buyers = new ArrayList<Buyer>();
		String selectUsers = "SELECT UserId,DOB,ZIP FROM Buyers WHERE ZIP=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setInt(1, zip);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			while(results.next()) {
				int userId = results.getInt("UserId");
				Date dob = results.getDate("DOB");
				int resultZip = results.getInt("ZIP");
			
			
				Buyer buyer = new Buyer(userId, dob, resultZip);
				buyers.add(buyer);
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
		return buyers;
	}



    public List<Buyer> getBuyersFromLastName(String lastName) throws SQLException {
		List<Buyer> buyers = new ArrayList<Buyer>();
        String selectBuyers =
		"SELECT Buyers.UserId AS UserId, FirstName, LastName, Email, DOB, ZIP " +
			"FROM Buyers INNER JOIN Users " +
			"  ON Buyers.UserId = Users.UserId " +
			"WHERE Buyers.LastName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBuyers);
			selectStmt.setString(1, lastName);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			while(results.next()) {
				int userId = results.getInt("UserId");
				Date dob = results.getDate("DOB");
				int resultZip = results.getInt("ZIP");
			
			
				Buyer buyer = new Buyer(userId, dob, resultZip);
				buyers.add(buyer);
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
		return buyers;
	}
	

	
	public Buyer delete(Buyer buyer) throws SQLException {
		String deleteBuyer = "DELETE FROM Buyers WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBuyer);
			deleteStmt.setInt(1, buyer.getUserId());
			deleteStmt.executeUpdate();
            super.delete(buyer);
			// Return null so the caller can no longer operate on the Persons instance.
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


	/**
	 * Update the DOB of the Buyer instance.
	 * This runs a UPDATE statement.
	 */
	public Buyer updateBuyer(Buyer buyer, Date newDob, int newZip) throws SQLException {
		String updateBuyer = "UPDATE Buyers SET DOB=?,ZIP=? WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBuyer);
			updateStmt.setTimestamp(1, new Timestamp(newDob.getTime()));
			updateStmt.setInt(2, newZip);
		
			updateStmt.setInt(3, buyer.getUserId());
			updateStmt.executeUpdate();

			// Update the buyer param before returning to the caller.
            buyer.setDob(newDob);
			buyer.setZip(newZip);
			
			return buyer;
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
}
