import java.awt.Image;

public class Spaceship extends Sprite2D {
    public Spaceship(Image i) {
        super(i);
    }

    public void move() {
        x += xSpeed; //simple move - left as is with x position + / - xSpeed
    }
    
}
