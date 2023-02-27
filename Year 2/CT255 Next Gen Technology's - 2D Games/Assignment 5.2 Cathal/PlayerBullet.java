import java.awt.*;
import java.awt.Image;

public class PlayerBullet extends Sprite2D {

    public PlayerBullet(Image i, Dimension WindowSize) {
        super(i, WindowSize);
        ySpeed = -5;
    }

    public void move() {
        y += ySpeed;
    }

    public boolean outOfBounds() { //if bullet is outside of play area returns a bool to remove itself
        if ((y) <= 0) {
            return true;
        }
        return false;
    }

}
