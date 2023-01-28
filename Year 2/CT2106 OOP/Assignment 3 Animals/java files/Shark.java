
public class Shark extends Fish
{
    
    
    boolean isDangerous; //sharks bite!
    boolean bite;

    /**
     * Constructor for objects of class Shark
     */
    public Shark(String name)
    {
        super(); // call the constructor of the superclass Fish
        //Name & Colour inherited from animal class - Values assigned now override the inherited value
        this.name = name;
        isDangerous = true;
        bite = true;
    }
    
    
    public Boolean isDangerous(){
        return isDangerous;
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
        String classTemp = "Shark";
        return classTemp;
    } 
    
    /**
     * toString method returns a String representation of the fish
     * What superclass has Shark inherited this method from? 
     * 
     */
    @Override
    public String toString(){
        String strng ="";
        strng+= "Shark;\n";
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
        
        
        strng+= "\nDangerous: ";
        strng+= isDangerous;
        
        strng += "\nDo I bite you?:";
        strng += bite;
        strng += "\n";
        
        strng += move(500);
        strng += "\n";
        
        // TOD0 Your job is to include the fields and attributes inherited 
        //from Fish and Animal in the String representation
        return strng;
    }

    
    /**
     * equals method defines how equality is defined between 
     * the instances of the Shark class
     * param Object
     * return true or false depending on whether the input object is 
     * equal to this Shark object
     */
    
     @Override
    public boolean equals(java.lang.Object object){
        
        // Checking did we get given any object.
        if (object == null) {
            System.out.print("Object given is NULL\n");
            return false;
        }

        // instanceof checking if the given object is the same type, otherwise the object cannot be casted        
        if (object instanceof Shark) {
            // Casting given object & running checks if all the details are the same as our existing object 
            Shark shark = (Shark) object;
            
            //in this if statement I'm not checking everything as it would look messy, e.g. Lets assume that both birds breathe (hopefully)
            if (this.getName() == shark.getName() && this.getColour() == shark.getColour() && this.isDangerous() == shark.isDangerous()) {
                return true;
            }
        }

        return false;
    }
}
