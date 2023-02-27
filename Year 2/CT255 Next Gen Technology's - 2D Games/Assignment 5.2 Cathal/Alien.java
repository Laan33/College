import java.awt.*;
import java.awt.Image;
import java.util.Iterator;
import java.util.List;

public class Alien extends Sprite2D {

    public Alien(Image i, Image j, Dimension WindowSize) {
        super(i, j, WindowSize);
    }

    // public interface
    public void move(boolean descend) { // alien's move method, passes in if there has been a collision, where it uses
                                        // it to descend
        x += xSpeed; // move x by xSpeed
        if (descend) { // if there is a collision, descend 15 pixels in y axis
            y += 15;
        }
    }

    public boolean checkBulletCollision(List<PlayerBullet> bulletList) {
        //checking if alien has collided with a list of bullets
        Iterator<PlayerBullet> iterator = bulletList.iterator();
        while (iterator.hasNext()) {
            PlayerBullet bullet = (PlayerBullet) iterator.next();
            if (isAlive() &&
                    ((x < bullet.x && x + imgWidth > bullet.x) || (bullet.x < x && bullet.x + bullet.imgWidth > x))
                    &&
                    ((y < bullet.y && y + imgHeight > bullet.y) || (bullet.y < y && bullet.y + bullet.imgHeight > y))) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean checkWallCollision() {
        if ((x + 60) >= 800 || (x - 15) <= 0) { // if near a wall in these bounds
            return true; // return there is a collision
        } else {
            return false; // else return false - no collision
        }
    }

}
