
/**
 * Email class for sending out email containing details of payment and address. Mainly print statements.
 *
 * @Cathal
 * @V1.3
 */
public class Email
{
    // Instance variables
    private Customer customer;
    private Order order;
    
    public Email(Customer customer) {
        this.customer = customer;
    }
    
    public void emailSend(Order order) {
        /* Function to handle the passed in order object. It takes in all of the customer data, checks
         * if the payment is valid, generates a confirmation letter. */
        
        System.out.println("- - - - - - - - - - - - - -");
        System.out.println("Mail to:" + customer.getEmailAddress());
        System.out.println("\n " + customer.getFirstName() + " " + customer.getSurname() + ",\n");
        if(order.getPayment().isValid()){
            System.out.println("Order contents and details enclosed below");
            System.out.println("\n   Order no: " + order.getOrderId() + "   Items: ");            
            order.printItems();
            System.out.println("   Total: " + order.getOrderCost());
            System.out.println("\n   Order will be delivered to: " + order.getDelAddress()); // Error statement included if no address has been found
            System.out.println("\n   Order will be billed to: " + order.getPayment().getBillAddress()); // Error statement included if no address has been found
            System.out.println("\nThank you again for shopping here\nHave a lovely day,\nShop-102 Staff\n");
            System.out.println("- - - - - - - - - - - - - -");
        }
        else{
            System.out.println("Unfortunately  your order has not been processed.\nWe're sorry, the validation on your payment has failed. Please check and retry.\n");
            System.out.println("\nMany thanks\nShop-102 Staff");
        }
    }
}
