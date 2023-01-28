
/**
 * Encapsulated in engine, there is only one wheel. It holds the maths for getCirc.
 *
 * @Cathal Lawlor   
 * @V1.0
 */
public class Wheel  {
    private float radius;
    private String name;

    public Wheel(String name, int radius)
    {
        this.name = name;
        this.radius = radius;
    }

    public double getCirc() { //circumferance calculation
        return  (2 * Math.PI * radius); 
    } 
    
    public String getName() {
        return name;
    }   
   
}
