package car.model;

/**
 * User model for Cargo.
 * 
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */
public class Users {
    protected int userId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;

    public Users(int userId){
        this.userId = userId;
    }

    public Users(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        
    }
 

    public Users(int userId, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    
    /** 
     * @return String
     */
    public String getFirstName() {
        return this.firstName;
    }

    
    /** 
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    /** 
     * @return String
     */
    public String getLastName() {
        return this.lastName;
    }

    
    /** 
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    /** 
     * @return String
     */
    public String getEmail() {
        return this.email;
    }

    
    /** 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /** 
     * @return String
     */
    public String getPassword() {
        return this.password;
    }

    
    /** 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
