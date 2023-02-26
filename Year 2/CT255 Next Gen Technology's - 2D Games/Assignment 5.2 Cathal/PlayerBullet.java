import java.awt.*;
import java.awt.Image;

public class PlayerBullet extends Sprite2D {

    
    public PlayerBullet(Image i, Dimension WindowSize) {
        super(i, WindowSize);
        ySpeed = -5;
        
        //TODO Auto-generated constructor stub
    }

    public void move() {
        y += ySpeed;        
    }
    public boolean outOfBounds() {
        if((y) <= 0 ){
            return true;
        }
        return false;
    }
    
}
