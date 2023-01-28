import java.util.ArrayList;
import java.util.Arrays;

/**
 * Order class, holds lots of attributes and then takes in shopping cart onto a new arraylist.
 * It then processes the addresses and prompts the payment class to process the card details 
 *
 * @Cathal
 * @V1.3
 */


public class Order
{
    // Instance variables
    private Address deliveryAddress;
    private boolean deliveryAddressValid;
    private Payment payment;
    private ShoppingCart cart;
    private ArrayList<Item> confirmedItems;
    private long orderNumber;
    private double totalCost;
    
    public Order(ShoppingCart cart, Payment payment) {
        confirmedItems = new ArrayList<>(); // Initialising ArrayList for the order
        orderNumber = setOrderId(); // Creating order ID
        
        // Copying passed through objects
        this.cart = cart;
        this.payment = payment;
        
        this.totalCost = cart.getTotal(); // Getting total cost for order
        
        for (int i = 0; i < cart.numItem(); i++) {
            confirmedItems.add(cart.getItem(i)); // Adding items individually to order
        }
        
        cart.emptyCart(); // Emptying shopping cart of items
    }
    
    // Setter Functions
    public long setOrderId() {
        return (long)(Math.random() * 99999999999999L);
    }
    
    public void setDelAddress(Address deliveryAddress) {
        deliveryAddressValid = true; //confirming that the address is available to be used.
        this.deliveryAddress = deliveryAddress;
    }
    
    public void printItems() {
        for (int i = 0; i < confirmedItems.size(); i++) {
            System.out.println("\t" + confirmedItems.get(i)); //going through the confirmed items and printing 
        }
    }
    
        public Payment getPayment() {
        return payment;
    }
    
    public double getOrderCost() {
        return totalCost;
    }
    
    public long getOrderId() {
        return orderNumber;
    }
    

    
    
    
    // method for the string out to aggregate the getter functions on delivery address
    public String getDelAddress() {   
        if (deliveryAddressValid) { // Checks if deliveryAddress has been set
            String out = deliveryAddress.getStreet() + ",\n\t\t\t\t" + deliveryAddress.getTown() + ",\n\t\t\t\t" + deliveryAddress.getCounty() + ",\n\t\t\t\t" + deliveryAddress.getCountry() + ",\n\t\t\t\t" + deliveryAddress.getEircode() + ".";
            return out;
        } else {
            String out = "Address not found.";
            return out;
        }
    }
    
    
}
