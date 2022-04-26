package car.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import car.model.Cars;
import car.model.Sellers;
import car.model.Users;


/**
 * The DAO for Car.
 * 
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */
public class CarDao {
	
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CarDao instance = null;
	protected CarDao() {
		connectionManager = new ConnectionManager();
	}
	public static CarDao getInstance() {
		if(instance == null) {
			instance = new CarDao();
		}
		return instance;
	}
	
	public Cars create(Cars car) throws SQLException {
		String insertCar = "INSERT INTO Cars(Vin,Year,Make,Model,Trim,Body,"
				+ "Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCar);
			insertStmt.setString(1,car.getVin());
			insertStmt.setInt(2,car.getYear());
			insertStmt.setString(3,car.getMake());
			insertStmt.setString(4,car.getModel());
			insertStmt.setString(5,car.getTrim());
			insertStmt.setString(6,car.getBody());
			insertStmt.setString(7,car.getTransmission());
			insertStmt.setString(8,car.getState());
			insertStmt.setInt(9,car.getOdometer());
			insertStmt.setDouble(10,car.getCarCondition());
			insertStmt.setString(11,car.getColor());
			insertStmt.setString(12,car.getInterior());
			insertStmt.setInt(13,car.getMmr());
			insertStmt.setInt(14, car.getSellingPrice());
			insertStmt.setInt(15,car.getSeller().getUserId());
			insertStmt.executeUpdate();
			return car;
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
	
	public Cars getCarByVin(String vin) throws SQLException {
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Vin=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setString(1, vin);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Cars car = getObj(results);
				return car;
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
	
	public List<Cars> getCarByYear(int year) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Year=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setInt(1, year);
			results = selectStmt.executeQuery();
			while(results.next()) {

				Cars car = getObj(results);

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
	
	public List<Cars> getCarByMake(String make) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Make=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setString(1, make);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByModel(String model) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Model=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setString(1, model);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByTrim(String trim) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Trim=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setString(1, trim);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByBody(String body) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Body=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setString(1, body);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByTransmission(String transmission) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Transmission=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setString(1, transmission);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByState(String state) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE State=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setString(1, state);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByOdometer(int odo) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Odometer=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setInt(1, odo);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByCondition(double condition) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE CarCondition=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setDouble(1, condition);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByColor(String color) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Color=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setString(1, color);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByInterior(String interior) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Interior=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setString(1, interior);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByMmr(int mmr) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Mmr=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setInt(1, mmr);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByUserId(int userId) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCar = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE UserId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public List<Cars> getCarByParameters(int year, String make, String model, String state) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String select = "SELECT Vin,Year,Make,Model,Trim,Body,Transmission,State,Odometer,CarCondition,Color,Interior,Mmr,SellingPrice,UserId "
				+ "FROM Cars "
				+ "WHERE Year=? AND Make=? AND Model=? AND State=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, year);
			selectStmt.setString(2, make);
			selectStmt.setString(3, model);
			selectStmt.setString(4, state);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Cars car = getObj(results);
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
	
	public Cars updateCar(Cars car, String trim, String state, int odometer, double carCondition, String color, String interior, int mmr,int sellingPrice, Users seller) throws SQLException {
		String updateCar = "UPDATE Cars SET Trim=?, State=?, Odometer=?, CarCondition=?, Color=?, Interior=?, Mmr=?, SellingPrice=?, UserId=? WHERE Vin=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setString(1, trim);
			updateStmt.setString(2, state);
			updateStmt.setInt(3, odometer);
			updateStmt.setDouble(4, carCondition);
			updateStmt.setString(5, color);
			updateStmt.setString(6, interior);
			updateStmt.setInt(7, mmr);
			updateStmt.setInt(8, sellingPrice);
			updateStmt.setInt(9, seller.getUserId());
			updateStmt.setString(10, car.getVin());
			updateStmt.executeUpdate();
			car.setTrim(trim);
			car.setState(state);
			car.setOdometer(odometer);
			car.setCarCondition(carCondition);
			car.setColor(color);
			car.setInterior(interior);
			car.setMmr(mmr);
			car.setSellingPrice(sellingPrice);
			car.setSeller(seller);
			return car;
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
	
	public Cars delete(Cars car) throws SQLException {
		String deleteCars = "DELETE FROM Cars WHERE Vin=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCars);
			deleteStmt.setString(1, car.getVin());
			deleteStmt.executeUpdate();
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
	
	
	
	private Cars getObj(ResultSet results) throws SQLException {
		UserDao userDao = UserDao.getInstance();
		String resultVin = results.getString("Vin");
		int resultYear = results.getInt("Year");
		String resultMake = results.getString("Make");
		String resultModel = results.getString("Model");
		String resultTrim = results.getString("Trim");
		String resultBody = results.getString("Body");
		String resultTransmission = results.getString("Transmission");
		String resultState = results.getString("State");
		int resultOdometer = results.getInt("Odometer");
		double resultCarCondition = results.getDouble("CarCondition");
		String resultColor = results.getString("Color");
		String resultInterior = results.getString("Interior");
		int resultMmr = results.getInt("Mmr");
		int resultSellingPrice = results.getInt("SellingPrice");
		int resultUserId = results.getInt("UserId");
		Users seller = userDao.getUserByUserId(resultUserId);
		Cars car = new Cars(resultVin,resultYear,
				resultMake,resultModel,resultTrim,resultBody,
				resultTransmission,resultState,resultOdometer,
				resultCarCondition,resultColor,resultInterior,resultMmr,resultSellingPrice,seller);
		return car;
	}
}
