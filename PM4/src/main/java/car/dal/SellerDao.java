package car.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import car.model.Companies;
import car.model.Sellers;
import car.model.Users;

public class SellerDao extends UserDao{
		// Single pattern: instantiation is limited to one object.
		private static SellerDao instance = null;
		protected SellerDao() {
			super();
		}
		public static SellerDao getInstance() {
			if(instance == null) {
				instance = new SellerDao();
			}
			return instance;
		}
		
		/**
		 * Save the Seller instance by storing it in your MySQL instance.
		 * This runs a INSERT statement.
		 */
		public Sellers create(Sellers seller) throws SQLException {
	        // Insert into the superclass table first.
			create(new Users(seller.getFirstName(), seller.getLastName(),
	        seller.getEmail(), seller.getPassword()));

			String insertSeller = "INSERT INTO Sellers(UserId,CompanyId,Zip,Address,Introduction) " +
	        "VALUES(?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
	      
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertSeller);
				// PreparedStatement allows us to substitute specific types into the query template.
				// For an overview, see:
				// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
				// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
				// For nullable fields, you can check the property first and then call setNull()
				// as applicable.
				insertStmt.setInt(1, seller.getUserId());		
				insertStmt.setInt(2, seller.getCompany().getCompanyId());
	            insertStmt.setInt(3, seller.getZip());
				insertStmt.setString(4, seller.getAddress());
				insertStmt.setString(5, seller.getIntroduction());

				// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
				// statements, and it returns an int for the row counts affected (or 0 if the
				// statement returns nothing). For more information, see:
				// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
				// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
				insertStmt.executeUpdate();
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				
				return seller;
				
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

	    public Sellers getSellerByUserId(int userId) throws SQLException {
			String selectSeller = "SELECT UserId,CompanyId,ZIP,Address,Introduction FROM Sellers WHERE UserId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			CompanyDao companyDao = CompanyDao.getInstance();
			UserDao userDao = UserDao.getInstance();
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectSeller);
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
					int selectUserId = results.getInt("UserId");
					int companyId = results.getInt("CompanyId");
					int zip = results.getInt("ZIP");
					String address = results.getString("Address");
					String introduction = results.getString("Introduction");
					Companies company = companyDao.getCompanyById(companyId);
					Users user = userDao.getUserByUserId(selectUserId);
					Sellers seller = new Sellers(company,zip,address,introduction,selectUserId,user.getFirstName(),
							user.getLastName(),user.getEmail(),user.getPassword());
					return seller;
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

	    

		
		public Sellers delete(Sellers seller) throws SQLException {
			String deleteSeller = "DELETE FROM Sellers WHERE UserId=?;";
			Connection connection = null;
			PreparedStatement deleteStmt = null;
			try {
				connection = connectionManager.getConnection();
				deleteStmt = connection.prepareStatement(deleteSeller);
				deleteStmt.setInt(1, seller.getUserId());
				deleteStmt.executeUpdate();
	            super.delete(seller);
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

	
}