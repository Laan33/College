import java.awt.*;

public class Sprite2D {
    // member data
    protected int x, y, xCoord, yCoord, imgHeight, imgWidth, xSpeed, ySpeed;
    private Image myImage, myImage2;
    private boolean isAlive = true;
    int framesDrawn = 0;
    int winWidth, winHeight;

    // constructor
    public Sprite2D(Image i, Image j, Dimension WindowSize) {
        myImage = i;
        myImage2 = j;
        winWidth = (int) WindowSize.getWidth();
        winHeight = (int) WindowSize.getHeight();

        imgHeight = i.getHeight(null);
        imgWidth = i.getWidth(null);
    }

    public Sprite2D(Image i, Dimension WindowSize) {
        myImage = i;
        myImage2 = i;
        winWidth = (int) WindowSize.getWidth();
        winHeight = (int) WindowSize.getHeight();

        imgHeight = i.getHeight(null);
        imgWidth = i.getWidth(null);
    }

    public void setPosition(double xx, double yy) {
        x = (int) xx;
        y = (int) yy;
    }

    public void setXSpeed(double dx) {
        xSpeed = (int) dx;
    }

    public void setYSpeed(double dy) {
        xSpeed = (int) dy;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean bool) {
        isAlive = bool;
    }

    public void paint(Graphics g) {
        framesDrawn++;
        if (!isAlive) //don't paint if sprite is dead
            ;
        else if (framesDrawn % 100 < 50) { //otherwise paint images altering between
            g.drawImage(myImage, (int) x, (int) y, null);
        } else {
            g.drawImage(myImage2, (int) x, (int) y, null);
        }
    }
}