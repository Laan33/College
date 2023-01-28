
import java.util.ArrayList;
import java.util.Scanner;

// For date handling but found it too tricky to get working
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;

/**
 * The 'class in charge' at the start. It holds an array for the items added to it and then to be sent to order.
 *
 * @Cathal
 * @V1.3
 */
public class ShoppingCart
{
    // Instance variables
    private ArrayList<Item> sList;
    private Item item;
    private long cartId;
    private int total;
    private boolean cartOpen;
    
    public ShoppingCart()
    {
        //Initialised to a new empty arraylist (for holding Item objects)
        sList = new ArrayList<Item>();
        cartId = getCartId();
        cartOpen = true;
    }
    
    public void addItem(Item i)
    {
        //Reads in a Item object and adds it to the list
        
        if (cartOpen) { //only happens if cart is open
            sList.add(i);
            System.out.println(i.getName(i) + " added to the shopping cart");
        } else { //failure statement
            System.out.println("Failure to add item; shopping card is closed");
        }
    }
    
    public Item getItem(int index)
    {
        //Reads in an index and returns the item at that index
        if(sList.get(index)!=null)
        {
            return sList.get(index);
        }
        else //There is no stored Item at that index. :(
        {
            System.out.println("The shopping cart is empty!");
            return null;
        }
    }
    
    public void closeCart() {
        cartOpen = false; //closes off the cart when called
    }
    
    public void confirmCart() {
        listItems();
        System.out.println("\nAbove items added to your cart, thank you.\n Confirm your order, Yes || No?");
        
        Scanner in = new Scanner(System.in);
        
        /* Using scanner we take the user input to determine if they want to proceed & confirm
         * current cart. Takes yes/no and y/n as inputs */
        
        char input = in.next().charAt(0);
        
        //if else statements to let user know of machine result
        if (input == 'y') { 
            System.out.println("Cart confirmed"); 
        }
        else if (input == 'n') {
            System.out.println("Cart cancelled, closing down shop");
            System.exit(0);
        }
        else {
            System.out.println("Out of bounds input, closing down shop");
            System.exit(0);
        }
        
    }
    
    
    
    
    public boolean removeItem(int index)
    {
        if (cartOpen) { //only works if the cart is open              
            if(sList.get(index)!=null) // if there is an item we proceed
            {
                System.out.println("\nRemoved the item: " + getItem(index).getName(item) + " from your cart\n"); //notifying user of change
                sList.remove(index);
                return true;
            }
            else
            {
                return false;
            }            
        }
        return false;
    }
    
     public long getCartId() { //for finding a random cart ID we use the random function
        return (long)(Math.random() * 999999999999L);
    }
    
    public int numItem() //number of items in the array
    {
        return sList.size(); 
    }
    
    public double getTotal(){ //rudimentry enough but it works by summing everything going through the array
                              //tried implementing a running counter when adding / removing items but was too buggy
        total = 0;
        int size = numItem();
        for(int idx = 0; idx < size; idx++) {
            total += sList.get(idx).getCost();
        }
        
        return total;
    }
    
    public void emptyCart() { //clears off the cart if needed
        sList = null;
    }
    
    public void listItems() //method for listing off the items, and their fields
    {
        if(numItem()>0)
        {
            for(int i=0; i<numItem(); i++)
            {
                System.out.println(getItem(i).toString());
            }
        }
        else
        {
            System.out.println("This group is currently empty.");
        }
    }
    
}
