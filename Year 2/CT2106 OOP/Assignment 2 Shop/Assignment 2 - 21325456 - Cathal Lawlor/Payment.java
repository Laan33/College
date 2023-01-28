
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.text.ParseException;

/**
 * Payment class, holds the card details and then validates the card details based on card number and card type
 *
 * @Cathal
 * @V1.3
 */

public class Payment
{
    // Instance variables
  
    private String cardDate;
    private String cardType;
    private long cardNumber;
    private String cardBankName;
    private Customer customer;
    private boolean billingAddressValid;
    private Address billingAddress;  
    
    public Payment(String cardType, long cardNumber, String cardDate, String cardBankName) {
        // Copying passed through variables into local variables
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cardDate = cardDate;
        this.cardBankName = cardBankName;
    }
    
    // This function validates if payment details are correct
    public boolean isValid() {
        String cardNumStr = Long.toString(cardNumber); // Converting to string so we can then check position [0]
        if (String.valueOf(cardNumber).length() == 16) { // Check if card number is 16 digits long 
            // I've made a check for the first digit on cardNumber. Master card has a specific first number with 2 for example. If it doesn't match then it is rejected. 
            // Check if cardType is an accepted payment type. Checks for the payment type name and if it's a valid type e.g. not Vita or MateUrCard
            if ((cardType == "Mastercard" ) || (cardType == "Visa")) {
                return true;    
            }
        }
        return false;
    }
    
    public void setBillAddress(Address billingAddress) { 
        billingAddressValid = true;
        this.billingAddress = billingAddress;
    }
    
    
    // method for the string out to aggregate the getter functions on billing address - same as 
    public String getBillAddress() {
        if (billingAddressValid) { // Check for if billing address has been set
            String out = billingAddress.getStreet() + ",\n\t\t\t\t" + billingAddress.getTown() + ",\n\t\t\t\t" + billingAddress.getCounty() + ",\n\t\t\t\t" + billingAddress.getCountry() + ",\n\t\t\t\t" + billingAddress.getEircode() + ".";
            return out;
        } else {
            String out = "Address not found";
            return out;
        }
    }
}




