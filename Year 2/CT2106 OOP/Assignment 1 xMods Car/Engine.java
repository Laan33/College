
/**
 * Engine class. It encapsulates the wheel and also calculates distance. 
 *
 * @Cathal Lawlor
 * @V1.10
 */
public class Engine
{
    // instance variables - replace the example below with your own
    private String name;
    private int tpl;
    private int totalNumTurns;
    private Wheel wheel;
    private double fuelLevel; //I realised at the end it would have been easier having fuel in car.

    Engine(String name, int tpl) 
    {
        this.name = name;
        this.tpl = tpl;
        fuelLevel = 100;
    }
    
    
    public void add(Wheel wheel1) 
    {
        this.wheel = wheel1; 
    }
  
    public double fetchFuel() {
        return fuelLevel;
    }
    
       public void setFuelLevel(double fuelLevel) {
        if (fuelLevel < 0) { //Error to check for negative fuel 
            System.out.println("Negative fuel not accepted");
            return;
        }
        this.fuelLevel = fuelLevel;
    }
    
    public int go(int fuelLevel) { 
        int turn = fuelLevel * tpl; //fuel amount x turns per litre
        int totalNumTurns = 0; 
        
        while (turn > 0) { //for each turn we will get the distance of one circumference
            totalNumTurns += wheel.getCirc();
            turn--; //decrementing
        }        
        return totalNumTurns;
    }
    
    
    
    public double getTpl(){
        return tpl;
    }
    
    public String getName() {
        return name;
    }
    
    public Wheel getWheel() {
        return wheel;
    }
    
  
}
