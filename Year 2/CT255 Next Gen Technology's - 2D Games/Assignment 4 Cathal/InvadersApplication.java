import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class InvadersApplication extends JFrame implements
        Runnable, KeyListener {
    // member data

    static final int windowWidth = 800;
    private static String workingDirectory;
    private static boolean isGraphicsInitialised = false;
    private static final Dimension WindowSize = new Dimension(windowWidth, 600);

    private static final int NUMALIENS = 30;

    private Alien[] AliensArray = new Alien[NUMALIENS];
    private Spaceship PlayerShip;
    private BufferStrategy strategy;
    

    // constructor
    public InvadersApplication() {
        // Display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        this.setTitle("Space Invaders! .. (getting there slowly)");

        createBufferStrategy(2);
        strategy = getBufferStrategy();

        // load image from disk. Make sure the path is right! For Mac use / rather than
        // \\
        ImageIcon icon = new ImageIcon(workingDirectory + "\\alien_ship_1.png");
        Image alienImage = icon.getImage();

        // create and initialise some aliens, passing them each the image we have loaded
        for (int i = 0; i < NUMALIENS; i++) {
            AliensArray[i] = new Alien(alienImage);

            //alien is 50 wide x 32 tall - you want a gap space of 30, so 30 + 50 on each sprite using modulo for 5 per row
            //if over 5 we start again from 25 in on x
            int xArray = ((i % 5) * 80) + 25;
            
            //again we want a gap, so it is 25 ish gap between each alien, where if the floor of i / 5 is the row we have the sprite on
            int yArray = (int)(java.lang.Math.floor(i / 5) * 45) + 40;

            //set position
            AliensArray[i].setPosition(xArray, yArray);
        }


        // create and initialise the player's spaceship
        icon = new ImageIcon(workingDirectory + "\\player_ship.png");
        Image shipImage = icon.getImage();
        PlayerShip = new Spaceship(shipImage);
        PlayerShip.setPosition(300, 530);

        // create and start our animation thread
        Thread t = new Thread(this);
        t.start();

        // send keyboard events arriving into this JFrame back to its own event handlers
        addKeyListener(this);
        isGraphicsInitialised = true; // it’s now safe to paint the images
    }

    // thread's entry point
    public void run() {

        boolean wallCollision = false; //application having a general collision boolean - if any alien hits a wall it descends
        double xSpeed = 2.5; //general xSpeed for aliens

        while (1 == 1) { // the game loop
            // 1: sleep for 1/50 sec
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
            // 2: animate game objects
            
            if(wallCollision) {
                    xSpeed = -xSpeed; //reversing alien direction of travel
                } 

            for (int i = 0; i < NUMALIENS; i++) {
                
                AliensArray[i].setXSpeed(xSpeed); //letting all aliens change direction of travel at once

                //calling alien to move, and passing in if there was a collision - used to prompt alien to descend
                AliensArray[i].move(wallCollision); 


            }

            //reseting the collision boolean as all aliens are moving away from collision
            wallCollision = false;
                            
            for (int i = 0; i < NUMALIENS; i++) { //for all aliens
                if(!wallCollision) { //if there hasn't been a collision
                    //we check if there is any collisions detected - setting wallCollision to the result
                    wallCollision = AliensArray[i].checkCollision(); 
                }
            }

            PlayerShip.move();
            this.repaint(); // 3: force an application repaint
        }
    }

    // Three Keyboard Event-Handler functions
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            PlayerShip.setXSpeed(-4);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            PlayerShip.setXSpeed(4);
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
            PlayerShip.setXSpeed(0);
    }

    public void keyTyped(KeyEvent e) {
    }

    //
    // application's paint method
    public void paint(Graphics g) {
        g = strategy.getDrawGraphics(); //redirecting drawing calls to offscreen buffer

        if (isGraphicsInitialised) { // don’t try to draw uninitialized objects!
            // clear the canvas with a big black rectangle
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WindowSize.width, WindowSize.height);
            // redraw all game objects
            for (int i = 0; i < NUMALIENS; i++)
                AliensArray[i].paint(g);
            PlayerShip.paint(g);
        }

        strategy.show(); //flip the buffers 
    }

    // application entry point
    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        InvadersApplication w = new InvadersApplication();
    }
}