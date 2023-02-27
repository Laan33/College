import java.awt.Image;

public class Spaceship extends Sprite2D {
    public Spaceship(Image i) {
        super(i);
    }

    public void move() {
        if ( !((x + 40) >= 800 || (x - 10) <= 0)) { //if near a wall in these bounds
            x += xSpeed; //simple move - left as is with x position + / - xSpeed
        }
        else if((x + 40) >= 800 ){
            x -= 2;
        }
        else if((x - 10) <= 0){
            x += 2;
        }
    }
    
}
