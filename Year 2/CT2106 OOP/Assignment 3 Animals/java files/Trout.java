
public class Trout extends Fish
{
    
    boolean spikes;
    boolean isEdible; //smoked trout is a lovely dinner
    String spawnMethod;
    

    /**
     * Constructor for objects of class Trout
     */
   
    public Trout(String name)
    {   
        super(); // call the constructor of the superclass Fish
        //Name & Colour inherited from animal class - Values assigned now override the inherited value
        this.name = name;
        colour = "brown";
        spawnMethod = "I swim upriver to lay eggs.";
        spikes = true;
        
               
    }
    
    
    public boolean spikes(){
        return spikes;
    }
    public boolean isEdible() {
        return isEdible;
    }
    public String spawnMethod() {
        return spawnMethod;
    }
    public boolean hasSpikes() {
        return spikes;
    }
    
    
    /**
     * Sing method overrides the sing method
     * inherited from superclass Bird
     
    @Override // good programming practice to use @Override to denote overridden methods
    public void sing(){
        System.out.println("tweet tweet tweet");
    }*/
    
    @Override
    public String getInstanceName(){ //this is for returning the instance type
        String classTemp = "Trout";
        return classTemp;
    } 
    
    
    /**
     * toString method returns a String representation of the fish
     * What superclass has Trout inherited this method from? 
     * 
     */
    @Override
    public String toString(){
        String strng ="";
        strng+= "\nTrout;\n";
        strng+= "name: ";
        strng+= name;
        strng+= "; ";
        strng+= "colour: ";
        strng+= colour;
        strng+= "\n";
        
        strng += "Do I breathe?: ";
        strng += breathes();
        strng += "\nDo I have skin?: ";
        strng += hasSkin();
        strng += "\nDo I eat?: ";
        strng += eats();
        strng += "\nDo I have gills?: ";
        strng += hasGills();
        strng += "\nDo I have gills?: ";
        strng += hasFins();
        
        strng += "\nAm I edible?: ";
        strng += isEdible();
        
        strng += "\nDo I have spikes?: ";
        strng += spikes();
        
        strng += "\nHow are new trout created?: ";
        strng += spawnMethod();
        
        strng += "\n";
        strng += move(200);
        
        // TOD0 Your job is to include the fields and attributes inherited 
        //from Fish and Animal in the String representation
        return strng;
    }

    
    /**
     * equals method defines how equality is defined between 
     * the instances of the Trout class
     * param Object
     * return true or false depending on whether the input object is 
     * equal to this Trout object
     */
    
     @Override
    public boolean equals(java.lang.Object object){
        
        // Checking did we get given any object.
        if (object == null) {
            System.out.print("Object given is NULL\n");
            return false;
        }

        // instanceof checking if the given object is the same type, otherwise the object cannot be casted.       
        if (object instanceof Trout) {
            // Casting given object & running checks if all the details are the same as our existing object 
            Trout trout = (Trout) object;
            
            //in this if statement I'm not checking everything as it would look messy, e.g. Lets assume that both birds breathe (hopefully)
            if (this.getName() == trout.getName() && this.getColour() == trout.getColour() && this.hasSpikes() == trout.hasSpikes()) {
                return true;
            }
        }

        return false;
    }
}
