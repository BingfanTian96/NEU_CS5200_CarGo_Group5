package car.model;

import java.util.Date;

/**
 * Buyer model for Cargo.
 * 
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */

public class Buyer extends Users{
    protected Date dob;
    protected int zip;

 

    public Buyer(int userId, Date dob, int zip) {
        super(userId);
        
        this.dob = dob;
        this.zip = zip;
    }


    public Buyer(Date dob, int zip, String firstName, String lastName, String email, String password) {
        super(firstName, lastName,  email,  password);
        
        this.dob = dob;
        this.zip = zip;
    }

    public Buyer(int userId, Date dob, int zip, String firstName, String lastName, String email, String password) {
        super(userId, firstName, lastName, email, password);
        this.dob = dob;
        this.zip = zip;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getZip() {
        return this.zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
    
}
