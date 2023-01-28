
/**
 * Test class for all of the rest of the classes.
 *
 * @Cathal Lawlor - 21325456
 * @V1.1
 */
public class TransactionTest
{
    
    public static void main(String[] args)
    {
       TransactionTest test = new TransactionTest();
       test.transaction1(); // calls the method holding our first test scenario
       test.transaction2(); // calls the method holding our secon test scenario
    }
    
    public void transaction1() {
        System.out.println("\nWelcome to the greatest shop known to mankind!\n"); // Opening Print Statement
        Customer customer1 = new Customer("Cathal", "Davis", "c.Lettuce@nuigalway.ie"); // Creating Customer object
        ShoppingCart cart1 = new ShoppingCart(); // Creating ShoppingCart object
        
        // instantiating item objects
        Item item1 = new Item("Headphones", 120);
        Item item2 = new Item("Mountain Bike", 899);
        Item item3 = new Item("Harrier Jet Wreckage", 24600);
        
        // Adding in the items to the shoppingcart
        cart1.addItem(item1);
        cart1.addItem(item2);
        cart1.addItem(item3);
    
        cart1.confirmCart(); //confirming cart - user is asked whether they want to confirm
        cart1.closeCart(); //boolean variable is controlled by this method. Cart will be sealed off when cartOpen is set to false
        
        // Passing in addresses
        Address deliveryAddress = new Address("22 Pound Hill", "Gortnaclohy", "Co. Cork", "Ireland", "P81 Y499");
        Address billingAddress = new Address("16 Fairview Manor, Ballyederowen", "Burnfoot", "Co. Donegal", "Ireland", "F93 Y523");
        
        Payment firstPayment = new Payment("Mastercard", 2334113943320401L, "03/24", "AIB"); // Payment object instantiated - Valid information
                                                    
        Order firstOrder = new Order(cart1, firstPayment); // Order Object instantiated
        
        // Setting in the addresses in delivery and billing address
        firstOrder.setDelAddress(deliveryAddress);
        firstPayment.setBillAddress(billingAddress);
        
        // Creating email object and sending email
        Email email = new Email(customer1);
        email.emailSend(firstOrder);
    }
    
    public void transaction2() {  
        System.out.println("\n Ok, perhaps not the best shop but it's up there!\n");
        Customer customer2 = new Customer("Liam", "Schukat", "Liam.Sch@mouseMail.ie");
        ShoppingCart cart2 = new ShoppingCart();
        
        Item item1 = new Item("Black pudding", 2); //Lovely for a good fry 
        Item item2 = new Item("Porridge", 3);
        Item item3 = new Item("Milk", 1.5);
        
        cart2.addItem(item1);
        cart2.addItem(item2);
        cart2.addItem(item3);
        
        cart2.removeItem(1); //removing the 1st item in the arrayList        
        
        cart2.confirmCart();        
        cart2.closeCart();
        
        Address deliveryAddress = new Address("6 Glenard Avenue", "Salthill", "Co. Galway", "Ireland", "H91 PT4V");
        Address billingAddress = new Address("5 Manor Way", "Shanagolden", "Co. Limerick", "Ireland", "V94 CCH6");
        Payment firstPayment = new Payment("Vita", 8654644668811230L, "10/18", "Bank Of Ireland"); // Payment object instantiated - Not valid information - Vita card and wrong starting number for cardNum
        Order orderTwo = new Order(cart2, firstPayment);
        orderTwo.setDelAddress(deliveryAddress);
        firstPayment.setBillAddress(billingAddress);
        
        Email email = new Email(customer2);
        email.emailSend(orderTwo);
    }
}
    
    
    
    
    
