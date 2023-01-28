
/**
 * Item class. Holds item attributes with get and set methods
 *
 * @Cathal
 * @V1.3
 */
public class Item
{
    // Instance variables
    private double cost;
    private String name;
    private int itemID;
    private static int prevId = 0;
    
        public Item(String name) { // taking in a new item with no cost amount
        this.name = name;
        itemID = generateID();
    }
    
        public Item(String name, double cost) { //taking in a new item with cost
        this.name = name;
        this.cost = cost;
        itemID = generateID();
    }
    
        private int generateID() { //sequential ID generator for items
        int id = prevId + 1;
        prevId = id;
 
        return id;
    }
    
    @Override
    public String toString() { 
        String out = "Id: " + itemID + "  -  " + name + "\tCost: " + cost; //sending out the order's list
 
        return out;
    }
    
    
    
    //Get and set methods
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public String getName(Item item) {
        return name;
    }
    
    public double getCost()
    {
        return cost;
    }
    
}
    

