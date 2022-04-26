package car.model;

/**
 * Model for car sellers.
 * 
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */
public class Sellers extends Users {
	protected Companies company;
	protected int zip;
	protected String address;
	protected String introduction;
	
	
	public Sellers(Companies company, int zip, String address, String introduction, int userId, String firstName, String lastName, String email, String password) {
		super(userId, firstName, lastName, email, password);
		this.company = company;
		this.zip = zip;
		this.address = address;
		this.introduction = introduction;
	}

	public Sellers(Companies company, int zip, String address, String introduction, String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
		this.company = company;
		this.zip = zip;
		this.address = address;
		this.introduction = introduction;
	}
	
	/**
	 * Constructor for reading records from MySQL, where we only have the userId,
	 * such as a foreign key reference to userId.
	 * Given sellerId, we can fetch the full seller record.
	 * 
	 * @param userId The auto-generated and auto-incremented ID for each seller.
	 */
	public Sellers(int userId) {
		super(userId);
	}
	
	
	/**
	 * The getter for the company of the seller.
	 * 
	 * @return the company of the seller.
	 */
	public Companies getCompany() {
		return company;
	}
	
	/**
	 * The setter for the company of the seller.
	 * 
	 * @param company the company of the seller.
	 */
	public void setCompany(Companies company) {
		this.company = company;
	}
	
	/**
	 * The getter for the zip code of the seller.
	 * 
	 * @return the zip code of the seller.
	 */
	public int getZip() {
		return zip;
	}
	
	/**
	 * The setter for the zipcode of the seller.
	 * 
	 * @param zip the zip code of the seller.
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	/**
	 * The getter for the address of the seller.
	 * 
	 * @return the address of the seller.
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * The setter for the address of the seller.
	 * 
	 * @param address the address of the seller.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * The getter for the introduction of the seller.
	 * 
	 * @return The introduction for the seller.
	 */
	public String getIntroduction() {
		return introduction;
	}
	
	/**
	 * The setter for the introduction of the seller.
	 * 
	 * @param introduction A brief introduction of the seller.
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
}
