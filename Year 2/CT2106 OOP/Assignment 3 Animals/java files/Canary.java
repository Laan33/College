
public class Canary extends Bird
{
     
    /**
     * Constructor for objects of class Canary
     */
    public Canary(String name)
    {
        super(); // call the constructor of the superclass Bird
        //Name & Colour inherited from animal class - Values assigned now override the inherited value
        this.name = name;
        colour = "yellow"; // this overrides the value inherited from Bird
        canSing = true;
        hasFeathers = true;
        
        
    }
    
    
    /**
     * Sing method overrides the sing method
     * inherited from superclass Bird
     */
    @Override // good programming practice to use @Override to denote overridden methods
    public String sing(){
        String temp = "tweet tweet tweet";
        return temp;
        
    }
    
    @Override //this is for returning the instance type
    public String getInstanceName(){
        String classTemp = "Canary";
        return classTemp;
    } 
    
    
    /**
     * toString method returns a String representation of the bird
     * What superclass has Canary inherited this method from? 
     * 
     */
    @Override
    public String toString(){
        String strng ="";
        strng+= "Canary;\n";
        strng+= "Name: ";
        strng+= name;
        strng+= "; ";
        strng+= "colour: ";
        strng+= colour;
        strng+= "\n";
        // TOD0 Your job is to include the fields and attributes inherited 
        //from Bird and Animal in the String representation
        
        strng += "Do I breathe?: ";
        strng += breathes();
        strng += "\nDo I have skin?: ";
        strng += hasSkin();
        strng += "\nDo I eat?: ";
        strng += eats();
        strng += "\nDo I have feathers?: ";
        strng += hasFeathers();
        strng += "\nDo I have wings?: ";
        strng += hasWings();
        
        strng += "\nListen to me sing: ";
        strng += sing();
        strng += move(40);
        strng += "\n\n";
        
        
        
        return strng;
    }

    
    /**
     * equals method defines how equality is defined between 
     * the instances of the Canary class
     * param Object
     * return true or false depending on whether the input object is 
     * equal to this Canary object
     */
    
    @Override
    public boolean equals(java.lang.Object object){
                
        // Checking did we get given any object.
        if (object == null) {
            System.out.print("NULL object given\n");
            return false;
        }

        // instanceof checking if the given object is the same type, otherwise the object cannot be casted        
        if (object instanceof Canary) {
            // Casting given object & running checks if all the details are the same as our existing object
            Canary canary = (Canary) object;
            
            //in this if statement I'm not checking everything as it would look messy, e.g. Lets assume that both birds breathe (hopefully)
            if (this.getName() == canary.getName() && this.getColour() == canary.getColour() && this.hasFeathers() == canary.hasFeathers()) {
                return true;
            }
        }

        return false;
    }
}
