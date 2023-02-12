import java.awt.Image;

public class Alien extends Sprite2D {


    public Alien(Image i) {
        super(i);
    }

    
    // public interface
    public void move(boolean descend) { //alien's move method, passes in if there has been a collision, where it uses it to descend        
        x += xSpeed; //move x by xSpeed
        if (descend) { //if there is a collision, descend 15 pixels in y axis
            y += 15;
        }

    }

    

    public boolean checkCollision() {
        if ((x + 60) >= 800 || (x - 15) <= 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
