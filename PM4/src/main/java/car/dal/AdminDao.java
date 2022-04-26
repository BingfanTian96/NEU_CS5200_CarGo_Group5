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
public class AdminDao extends UserDao{
	
	
	// Single pattern: instantiation is limited to one object.
	private static AdminDao instance = null;
	protected AdminDao() {
		super();
	}
	public static AdminDao getInstance() {
		if(instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	
	/**
	 * Save the Buyer instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Admin create(Admin admin) throws SQLException {
        // Insert into the superclass table first.
		create(new Users(admin.getFirstName(), admin.getLastName(),
        admin.getEmail(), admin.getPassword()));

		String insertBuyer = "INSERT INTO Admins(UserId) " +
        "VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
      
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBuyer);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, admin.getUserId());
			

			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			
			return admin;
			
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

    public Admin getAdminByUserId(int userId) throws SQLException {
		String selectAdmin = "SELECT UserId FROM Admins WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdmin);
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

				Admin admin = new Admin(resultUserId);
				return admin;
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

    public Admin getAdminByEmail(int userId) throws SQLException {
		String selectAdmin = 
        "SELECT Admins.UserId AS UserId, FirstName, Email, Password " +
        "FROM Admins INNER JOIN Users " +
        "  ON Users.UserId = Admins.UserId " +
        "WHERE Admins.UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdmin);
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

				Admin admin = new Admin(resultUserId);
				return admin;
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




    public List<Admin> getAdminsFromLastName(String lastName) throws SQLException {
		List<Admin> admins = new ArrayList<Admin>();
        String selectBuyers =
		"SELECT Admins.UserId AS UserId, FirstName, LastName, Email, Password " +
			"FROM Admins INNER JOIN Users " +
			"  ON Admins.UserId = Users.UserId " +
			"WHERE Users.LastName=?;";
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
				int resultUserId = results.getInt("UserId");

				Admin admin = new Admin(resultUserId);
				
				admins.add(admin);
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
		return admins;
	}
	

	
	public Admin delete(Admin admin) throws SQLException {
		String deleteAdmin = "DELETE FROM Admins WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdmin);
			deleteStmt.setInt(1, admin.getUserId());
			deleteStmt.executeUpdate();
            super.delete(admin);
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
