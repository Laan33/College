import java.awt.*;
import java.util.Random;

public class GameObject {
    
    private int x, y; //base variable initialised with a random colour
    private Color c = new Color(randomGen(256), randomGen(256), randomGen(256));
    int size = 50; //square size
 
    public GameObject() {
        //randomises position 
        x = (int)(Math.random() * 600);
        y = (int)(Math.random() * 600);
    }

    private int randomGen(int modMax) { //random number generator - used for randomising RGB values
        int randNum = (int)(Math.random() * modMax);       
        return randNum;
    } 

    public int getRandomNumberUsingInts(int min, int max) {                                                             //used for the x and y movement for squares
        Random random = new Random();
        return random.ints(min, max)
          .findFirst()
          .getAsInt();
    }

    public void move() {
        //randomly alter the x and y         
        x += getRandomNumberUsingInts(-9, 10); //wanting a random movement for x and y
        y += getRandomNumberUsingInts(-9, 10); //there is a bias towards negative numbers, added an artificial 
                                                //bias with the range being -9, 10

        if(x > 590) { //if reaches outer bounds, we reset x and y back to 0,0
            x -= 590;
        }
        if(y > 590) {
            y -= 590;
        }
        
    } 

    public void paint(Graphics g) {  
        //draws object as a square

        g.setColor(c);//setting color        
        g.fillRect(x, y, size, size); //drawing the square with specified size and x & y
    } 



}
