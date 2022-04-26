package car.model;

/**
 * The model for car which includes detailed information on the car.
 * 
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */
public class Cars {
	protected String vin;
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
	protected int mmr;
	protected int sellingPrice;
	protected Users seller;
	
	/**
	 * Constructor for reading records from MySQL, where we have all fields.
	 * 
	 * @param vin The unique Vin number of the car.
	 * @param year The year of the car.
	 * @param make The make of the car.
	 * @param model The model of the car.
	 * @param trim The trim of the car.
	 * @param body The body type of the car.
	 * @param transmission The type of transmission of the car.
	 * @param state The state of the car.
	 * @param odometer The odometer of the car.
	 * @param carCondition The condition of the car.
	 * @param color The color of the car.
	 * @param interior The interior of the car.
	 * @param mmr The mmr of the car.
	 * @param seller The seller of the car.
	 */
	public Cars(String vin, int year, String make, String model, String trim, String body, String transmission,
			String state, int odometer, double carCondition, String color, String interior, int mmr, int sellingPrice,Users seller) {
		this.vin = vin;
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
		this.mmr = mmr;
		this.sellingPrice = sellingPrice;
		this.seller = seller;
	}
	
	/**
	 * Constructor for reading records from MySQL, where we only have the Vin number,
	 * such as a foreign key reference to Vin number.
	 * Given Vin number, we can fetch the full Cars record.
	 * 
	 * @param vin The unique Vin number for the car.
	 */
	public Cars(String vin) {
		this.vin = vin;
	}
	
	/**
	 * The getter for the Vin number of a car.
	 * 
	 * @return The Vin number of a car.
	 */
	public String getVin() {
		return vin;
	}
	
	/**
	 * The setter for the Vin number of a car.
	 * 
	 * @param vin The Vin number of a car.
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	/**
	 * The getter for the year of the car.
	 * 
	 * @return The year of the car.
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * The setter for the year of the car.
	 * 
	 * @param year The year of the car.
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * The getter for the make of the car.
	 * 
	 * @return The make of the car.
	 */
	public String getMake() {
		return make;
	}
	
	/**
	 * The setter for the make of the car.
	 * 
	 * @param make The make of the car.
	 */
	public void setMake(String make) {
		this.make = make;
	}
	
	/**
	 * The getter for the model of the car.
	 * 
	 * @return The model of the car.
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * The setter for the model of the car.
	 * 
	 * @param model The model of the car.
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * The getter for the trim of the car.
	 * 
	 * @return The trim of the car.
	 */
	public String getTrim() {
		return trim;
	}
	
	/**
	 * The setter for the trim of the car.
	 * 
	 * @param trim The trim of the car.
	 */
	public void setTrim(String trim) {
		this.trim = trim;
	}
	
	/**
	 * The getter for the body of the car.
	 * 
	 * @return The body of the car.
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * The setter for the body of the car.
	 * 
	 * @param body The body of the car.
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * The getter for the transmission of the car.
	 * 
	 * @return the transmission of the car.
	 */
	public String getTransmission() {
		return transmission;
	}
	
	/**
	 * The setter for the transmission of the car.
	 * 
	 * @param transmission The transmission of the car.
	 */
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	
	/**
	 * The getter for the state of the car.
	 * 
	 * @return The state of the car.
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * The setter for the state of the car.
	 * 
	 * @param state The state of the car.
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * The getter for the odometer of the car.
	 * 
	 * @return The odometer of the car.
	 */
	public int getOdometer() {
		return odometer;
	}
	
	/**
	 * The setter for the odometer of the car.
	 * 
	 * @param odometer The odometer of the car.
	 */
	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}
	
	/**
	 * The getter for the condition of the car.
	 * 
	 * @return The condition of the car.
	 */
	public double getCarCondition() {
		return carCondition;
	}
	
	/**
	 * The setter for the condition of the car.
	 * 
	 * @param carCondition The condition of the car.
	 */
	public void setCarCondition(double carCondition) {
		this.carCondition = carCondition;
	}
	
	/**
	 * The getter for the color of the car.
	 * 
	 * @return The color of the car.
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * The setter for color of the car.
	 * 
	 * @param color The color of the car.
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * The getter for the interior of the car.
	 * 
	 * @return The interior of the car.
	 */
	public String getInterior() {
		return interior;
	}
	
	/**
	 * The setter for the interior of the car.
	 * 
	 * @param interior The interior of the car.
	 */
	public void setInterior(String interior) {
		this.interior = interior;
	}
	
	/**
	 * The getter for the Mmr of the car.
	 * 
	 * @return The mmr of the car.
	 */
	public int getMmr() {
		return mmr;
	}
	
	/**
	 * The setter for the Mmr of the car.
	 * 
	 * @param mmr The mmr of the car.
	 */
	public void setMmr(int mmr) {
		this.mmr = mmr;
	}
	
	/**
	 * The getter for the seller of the car.
	 * 
	 * @return The seller of the car.
	 */
	public Users getSeller() {
		return seller;
	}
	
	/**
	 * The setter for the seller of the car.
	 * 
	 * @param seller The seller of the car.
	 */
	public void setSeller(Users seller) {
		this.seller = seller;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	
}
