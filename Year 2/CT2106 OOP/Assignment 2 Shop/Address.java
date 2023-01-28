/**
 * Address class, basic in that it holds address info and just getter methods
 *
 * @Cathal
 * @V1.3
 */


public class Address
{
    // Instance variables
    private String street;
    private String town;
    private String county;
    private String country;
    private String eircode;
    
    public Address(String street, String town, String county, String country, String eircode) {
        this.eircode = eircode;
        this.street = street;
        this.town = town;
        this.county = county;
        this.country = country;        
    }
    
    // Getter Functions
        public String getEircode() {
        return eircode;
    }
    
    public String getStreet() {
        return street;
    }
    
    public String getTown() {
        return town;
    }
    
    public String getCounty() {
        return county;
    }
    
    public String getCountry() {
        return country;
    }
    

    
}