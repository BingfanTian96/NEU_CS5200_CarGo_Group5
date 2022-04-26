package car.model;
/**
 * This class is used for representing Searches.
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */
public class Searches{
	protected int searchId;
	protected int year;
	protected String make;
	protected String model;
	protected String trim;
	protected String body;
	protected String transmission;
	protected String state;
	protected int odometer;
	protected double carCondition;
	protected String color;
	protected String interior;
	protected Users user;
	//this constructor is used for reading records
	public Searches(int searchId, int year, String make, String model, String trim, String body, String transmission,
			String state, int odometer, double carCondition, String color, String interior, Users user) {
		super();
		this.searchId = searchId;
		this.year = year;
		this.make = make;
		this.model = model;
		this.trim = trim;
		this.body = body;
		this.transmission = transmission;
		this.state = state;
		this.odometer = odometer;
		this.carCondition = carCondition;
		this.color = color;
		this.interior = interior;
		this.user = user;
	}
	
	//this constructor is used for reference
	public Searches(int searchId) {
		super();
		this.searchId = searchId;
	}


	//this constructor is used for creating records
	public Searches(int year, String make, String model, String trim, String body, String transmission, String state,
			int odometer, double carCondition, String color, String interior, Users user) {
		super();
		this.year = year;
		this.make = make;
		this.model = model;
		this.trim = trim;
		this.body = body;
		this.transmission = transmission;
		this.state = state;
		this.odometer = odometer;
		this.carCondition = carCondition;
		this.color = color;
		this.interior = interior;
		this.user = user;
	}
	public int getSearchId() {
		return searchId;
	}
	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getTrim() {
		return trim;
	}
	public void setTrim(String trim) {
		this.trim = trim;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getOdometer() {
		return odometer;
	}
	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}
	public double getCarCondition() {
		return carCondition;
	}
	public void setCarCondition(double carCondition) {
		this.carCondition = carCondition;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getInterior() {
		return interior;
	}
	public void setInterior(String interior) {
		this.interior = interior;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
}