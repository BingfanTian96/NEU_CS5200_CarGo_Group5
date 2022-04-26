package car.model;
/**
 * Admin model for Cargo.
 * 
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */

 /**
  * @param userId
  */
public class Admin extends Users{
    protected int userId;
    

  

    public Admin(int userId) {
        super(userId);
    }
    
    /** 
     * @return int
     */
    public int getUserId() {
        return this.userId;
    }

    
    /** 
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }    
}
