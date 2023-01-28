
/**
 * The main car class. It encapsulates the engine (with the wheel in that then). 
 *
 * @Cathal Lawlor
 * @V1.10
 */
public class Car
{
    // instance variables 
    
    
    private String name;
    private double distance;
    private double totalKm;
    private Engine engine_1;
    private double km;

 
    public Car(String name)  //Setting the start / basics of the car e.g. 100 fuel etc.
    {
        this.name = name;
        distance = 0.0;
        totalKm = 0.0;
    }

    
    
    public void setFuel(double fuel) 
    {
        engine_1.setFuelLevel(fuel); //sending fuel to engine fuel
    }
    
    public void add(Engine engine_1)
    {
        this.engine_1 = engine_1;
    }

    public void add(Wheel wheel) {
        engine_1.add(wheel);
    }
    
    public double fetchFuelL() { //I used fetch, but in future I will use get as a disc tutor told me it's the common standard.
        return engine_1.fetchFuel();
    }
    
    
    
    public void go() {
        double km = engine_1.go((int) fetchFuelL()); //km is current ditance
        totalKm += km; //adding to totalKm
        setDistance(km); //current distance being passed
    }
    
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
        
    public void printState()
    {
        System.out.println("Name: "+getName()+"; Engine: "+engine_1.getName()+"; Turns Per Litre: "+engine_1.getTpl()+
        "; " + '\n' + "Wheels: "+engine_1.getWheel().getName()+ '\n' +"Distance with "+fetchFuelL()+" litres used: "+getDistance()+"; Total distance travelled:"+ totalKm + '\n');
    }
    
      public String getName() {
        return name;
    }
    
    
}
