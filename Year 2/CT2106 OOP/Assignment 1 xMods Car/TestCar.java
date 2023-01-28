
/**
 * Test class to test car, engine and wheel objects
 *
 * @Cathal Lawlor
 * @V1.10
 */
public class TestCar
{
    
   
    //code from assignment
    public static void main(String[] args) 
    {
    Car car = new Car("X7");
    Engine engine = new Engine("DR9", 43);
    car.add(engine);
    Wheel wheel = new Wheel("Wichelin15", 15);
    car.add(wheel);
    car.setFuel(100);
    System.out.printf("Current fuel: %.2f\n",car.fetchFuelL());
    car.go();
    car.printState();
    car.setFuel(50);
    System.out.printf("Current fuel: %.2f\n",car.fetchFuelL());
    car.go();
    car.printState();
    }
}
