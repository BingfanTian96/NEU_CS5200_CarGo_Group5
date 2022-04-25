package car.model;
/**
 * This class is used for representing saves of users.
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */
public class Saves{
	protected int saveId;
	protected Cars car;
	protected Buyer buyer;
	//this constructor is used for reading records
	public Saves(int saveId, Cars car, Buyer buyer) {
		super();
		this.saveId = saveId;
		this.car = car;
		this.buyer = buyer;
	}
	//this constructor is used for reference
	public Saves(int saveId) {
		super();
		this.saveId = saveId;
	}

	//this constructor is used for creating records
	public Saves(Cars car, Buyer buyer) {
		super();
		this.car = car;
		this.buyer = buyer;
	}
	public int getSaveId() {
		return saveId;
	}
	public void setSaveId(int saveId) {
		this.saveId = saveId;
	}
	public Cars getCar() {
		return car;
	}
	public void setCar(Cars car) {
		this.car = car;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
}