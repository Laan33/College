
public abstract class Fish extends Animal
{
    //instance variables (fields) - inherited by fish subclasses
    boolean hasFins;
    boolean hasGills;
    

    /**
     * Constructor for objects of class Fish
     */
    public Fish()
    {
        super(); //calls the constructor of its superclass  - Animal
        colour = "black"; //overrides the value of colour inherited from Animal
        hasFins = true; //all the subclasses of Fish inherit this property and value
        hasGills = true; //all the subclasses of Fish inherit this property and value
        swims = true; //all the subclasses of Fish inherit this property and value
    }

    /**
     * move method overrides the move method
     * inherited from superclass Animal
     */
    @Override // good programming practice to use @Override to denote overridden methods
    public String move(int distance){  //move method overwritten for swimming 
        distanceTxt = "Look at me, I swam ";
        distanceTxt += distance;
        distanceTxt += " metres!";
        
        return  distanceTxt;
    }
    
      
    /**
     * 'getter' method for the hasGills field
     */
    public boolean hasGills(){
        return hasGills;
    }
    
    /**
     * 'getter' method for the hasFins field
     */
    public boolean hasFins(){
        return hasFins;
    }
}
