package car.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import car.model.Buyer;
import car.model.Cars;
import car.model.Saves;
import car.model.Sellers;
import car.model.Users;
/**
 * Saves Dao
 * @author yansen
 *
 */
public class SavesDao{
	protected ConnectionManager connectionManager;

	private static SavesDao instance = null;
	protected SavesDao() {
		connectionManager = new ConnectionManager();
	}
	public static SavesDao getInstance() {
		if(instance == null) {
			instance = new SavesDao();
		}
		return instance;
	}
	
	
	public Saves create(Saves save) throws SQLException {
		String insertSave =
			"INSERT INTO Saves(Vin, UserId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Saves has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertSave,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, save.getCar().getVin());
			
			insertStmt.setInt(2, save.getBuyer().getUserId());
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int saveId = -1;
			if(resultKey.next()) {
				saveId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			save.setSaveId(saveId);
			return save;
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
	 * Get the Saves record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Saves instance.
	 * to join the Saves, BlogUsers tables and then build each object.
	 */
	public Saves getSaveById(int saveId) throws SQLException {
		String selectSave =
			"SELECT SaveId, Vin, UserId" +
			"FROM Saves " +
			"WHERE SaveId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CarDao carDao = CarDao.getInstance();
		UserDao userDao = UserDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSave);
			selectStmt.setInt(1, saveId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultSaveId = results.getInt("SaveId");
				String vin = results.getString("Vin");
				Cars car = carDao.getCarByVin(vin);
				int userId = results.getInt("UserId");
				Users buyer = userDao.getUserByUserId(userId);
				Saves save = new Saves(resultSaveId,car,buyer);
				return save;
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
	 * Get the Saves record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a list of Cars from Saves of a user.
	 */
	public List<Cars> getCarsFromSavesByUserId(int userId) throws SQLException {
		List<Cars> cars = new ArrayList<>();
		String selectCarsFromSaves =
			"SELECT Saves.Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,"
			+ "CarCondition,Color,Interior,Mmr,SellingPrice,Cars.UserId " +
			"FROM Saves INNER JOIN Cars ON Saves.Vin = Cars.Vin " +
			"WHERE Saves.UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UserDao userDao = UserDao.getInstance();

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCarsFromSaves);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String vin = results.getString("Vin");
				int year = results.getInt("Year");
				String make = results.getString("Make");
				String model = results.getString("Model");
				String trim = results.getString("Trim");
				String body = results.getString("Body");
				String transmission = results.getString("Transmission");
				String state = results.getString("State");
				int odometer = results.getInt("Odometer");
				double carCondition = results.getDouble("CarCondition");
				String color = results.getString("Color");
				String interior = results.getString("Interior");
				int mmr = results.getInt("Mmr");
				int sellingPrice = results.getInt("SellingPrice");
				int sellerId = results.getInt("UserId");
				Users seller = userDao.getUserByUserId(sellerId);
				Cars car = new Cars(vin,year,make,model,trim,body,transmission,state,odometer,
						carCondition,color,interior,mmr,sellingPrice,seller);
				cars.add(car);
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
		return cars;
	}
	
	
	
	
	/**
	 * Delete the Saves instance.
	 * This runs a DELETE statement.
	 */
	public Saves delete(Saves saves) throws SQLException {
		String deleteSave = "DELETE FROM Saves WHERE SaveId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSave);
			deleteStmt.setInt(1, saves.getSaveId());
			deleteStmt.executeUpdate();
			// Return null so the caller can no longer operate on the Saves instance.
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