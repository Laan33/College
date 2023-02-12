import java.awt.*;

public class Sprite2D {
    // member data
    protected double x;
    protected double y;
    protected double xSpeed = 0;
    private Image myImage;

    // constructor
    public Sprite2D(Image i) {      
        myImage = i;
    }

    public void setPosition(double xx, double yy) {
        x = xx;
        y = yy;
    }

    public void setXSpeed(double dx) {
        xSpeed = dx;
    }

    public void paint(Graphics g) {
        g.drawImage(myImage, (int) x, (int) y, null);
    }
}