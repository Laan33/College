
/**
 * Customer class, like item it holds lots of attributes and getter and setter methods
 *
 * @Cathal
 * @V1.3
 */
public class Customer
{
    // Instance variables
    private final long customerID;
    private String fName;
    private String surname;
    private String emailAddress;
    
    public Customer(String emailAddress, String firstName, String surname) {
        this.surname = surname;
        this.fName = firstName;        
        this.emailAddress = emailAddress;
        customerID = genCustomerID();
    }

    //generating random ID for customer
      private long genCustomerID() {
        return (long) (Math.random() * 99999999999999L);
    }
    
    
    //Getter and setter methods
    
    public String getSurname() {
 
        return surname;
    }
 
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public long getID() {
        return customerID;
    }
 
    public String getFirstName() {
        return fName;
    }
    

 
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
           public void setFirstName(String first) {
        fName = first;
    }
    
    public void setSurname(String sName) {
        surname = sName;
    }
    
}
