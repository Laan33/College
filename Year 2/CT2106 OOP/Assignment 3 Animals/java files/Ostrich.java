
public class Ostrich extends Bird
{
    
    //String name; // the name of this Ostrich
    String legType;
    boolean isTall;
    
    /**
     * Constructor for objects of class Ostrich
     */
    public Ostrich(String name)
    {
        super(); // call the constructor of the superclass Bird
        //Name & Colour inherited from animal class - Values assigned now override the inherited value
        this.name = name;
        canSing = true;
        colour = "yellow"; // this overrides the value inherited from Bird
        //Fliesinherited from animal class
        legType = "long thin legs!";
        flies = false;
        isTall = true;
        distance = 10;
    }
    
    /**
     * Sing method overrides the sing method
     * inherited from superclass Bird
     */
    @Override // good programming practice to use @Override to denote overridden methods
    public String sing(){
        String temp = "gawk gawk gawk";
        return temp;
    }
    
    @Override
    public String getInstanceName(){ //this is for returning the instance type
        String classTemp = "Ostrich";
        return classTemp;
    } 
    
    public boolean isTall(){
        return isTall;
    } 
    
    public String legType() { 
        return legType; 
    }
    
    
    /**
     * toString method returns a String representation of the bird
     * What superclass has Ostrich inherited this method from? 
     * 
     */
    @Override
    public String toString(){
        String strng ="";
        strng+= "Ostrich;\n";
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
        strng += "\nDo I have feathers?: ";
        strng += hasFeathers();
        strng += "\nDo I have wings?: ";
        strng += hasWings();
        
        strng += "\nListen to me sing: ";
        strng += sing(); //hehe         
        
        strng +="\nTall?: ";
        strng += isTall;
        
        strng += "\nLeg type?: ";
        strng += legType();        
        
        strng+= move(distance);
        strng += "\n\n";
 
        
        // TOD0 Your job is to include the fields and attributes inherited 
        //from Bird and Animal in the String representation
        return strng;
    }

    
    /**
     * equals method defines how equality is defined between 
     * the instances of the Ostrich class
     * param Object
     * return true or false depending on whether the input object is 
     * equal to this Ostrich object
     */
    
    @Override
    public boolean equals(java.lang.Object object){
                
        // Checking did we get given any object.
        if (object == null) {
            System.out.print("NULL object given\n");
            return false;
        }

        // instanceof checking if the given object is the same type, otherwise the object cannot be casted        
        if (object instanceof Ostrich) {
            // Casting given object & running checks if all the details are the same as our existing object 
            Ostrich ostrich = (Ostrich) object;
            
            //in this if statement I'm not checking everything as it would look messy, e.g. Lets assume that both birds breathe (hopefully)
            if (this.getName() == ostrich.getName() && this.getColour() == ostrich.getColour() && this.isTall() == ostrich.isTall()) {
                return true;
            }
        }

        return false;
    }
}
