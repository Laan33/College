import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import javax.swing.*;

public class InvadersApplication extends JFrame implements
        Runnable, KeyListener {
    // member data
    
    public boolean gameRunning, isRunning = false;
    private static final int NUMALIENS = 30;
    private static String workingDirectory;
    private static boolean isGraphicsInitialised = false;    
    private static final Dimension WindowSize = new Dimension(800, 600);
    

    private int deadAliens, redrawnCount = 0;
    public Alien[] AliensArray = new Alien[NUMALIENS];
    private Spaceship PlayerShip;
    public final List<PlayerBullet> bulletsList = new ArrayList<PlayerBullet>();
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
        Image alienImage1 = icon.getImage();
        icon = new ImageIcon(workingDirectory + "\\alien_ship_2.png");
        Image alienImage2 = icon.getImage();

        // create and initialise some aliens, passing them each the image we have loaded
        for (int i = 0; i < NUMALIENS; i++) {
            AliensArray[i] = new Alien(alienImage1, alienImage2, WindowSize);

            // alien is 50 wide x 32 tall - you want a gap space of 30, so 30 + 50 on each
            // sprite using modulo for 5 per row
            // if over 5 we start again from 25 in on x
            int xArray = ((i % 5) * 80) + 25;

            // again we want a gap, so it is 25 ish gap between each alien, where if the
            // floor of i / 5 is the row we have the sprite on
            int yArray = (int) (java.lang.Math.floor(i / 5) * 45) + 40;

            // set position
            AliensArray[i].setPosition(xArray, yArray);
        }

        // create and initialise the player's spaceship
        icon = new ImageIcon(workingDirectory + "\\player_ship.png");
        Image shipImage = icon.getImage();
        PlayerShip = new Spaceship(shipImage, WindowSize);
        PlayerShip.setPosition(300, 530);

        // create and start our animation thread
        Thread t = new Thread(this);
        t.start();

        // send keyboard events arriving into this JFrame back to its own event handlers
        addKeyListener(this);
        isGraphicsInitialised = true; // it’s now safe to paint the images
    }

    public void startNewGame(){
        isRunning = true;
        gameRunning = true;
    }

    public void startNewWave() {
        
    }
    // thread's entry point
    public void run() {

        boolean wallCollision = false; // application having a general collision boolean - if any alien hits a wall it
                                       // descends
        double xSpeed = 2.5; // general xSpeed for aliens

        while (1 == 1) { // the game loop
            // 1: sleep for 1/50 sec
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
            // 2: animate game objects

            if (wallCollision) {
                xSpeed = -xSpeed; // reversing alien direction of travel
            }

            for (int i = 0; i < NUMALIENS; i++) {
                
                if(AliensArray[i].checkBulletCollision(bulletsList)){
                    AliensArray[i].setAlive(false);
                    deadAliens++;
                } 
                AliensArray[i].setXSpeed(xSpeed); // letting all aliens change direction of travel at once
                // calling alien to move, and passing in if there was a collision - used to
                // prompt alien to descend
                AliensArray[i].move(wallCollision);
                if(AliensArray[i].y < PlayerShip.y) {
                    //TODO - Game over
                }

            }
            wallCollision = false; // reseting the collision boolean as all aliens are moving away from collision

            for (int i = 0; i < NUMALIENS; i++) { // for all aliens - if there hasn't been a collision already - we check if there is any collisions detected - setting wallCollision to the
                if (!wallCollision && AliensArray[i].isAlive()) { 
                    wallCollision = AliensArray[i].checkWallCollision();
                }
            }

            Iterator iterator = bulletsList.iterator();
            while (iterator.hasNext()) {
                PlayerBullet b = (PlayerBullet) iterator.next();
                b.move();                
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
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
            shootBullet();
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
        g = strategy.getDrawGraphics(); // redirecting drawing calls to offscreen buffer

        if (isGraphicsInitialised) { // don’t try to draw uninitialized objects!
            // clear the canvas with a big black rectangle
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WindowSize.width, WindowSize.height);
            // redraw all game objects
            for (int i = 0; i < NUMALIENS; i++)
                AliensArray[i].paint(g);
            PlayerShip.paint(g);

            Iterator iterator = bulletsList.iterator();
            while (iterator.hasNext()) {
                PlayerBullet b = (PlayerBullet) iterator.next();
                b.paint(g);
            }
        }

        strategy.show(); // flip the buffers
    }

    public void shootBullet() {
        // add a new bullet to our list
        ImageIcon icon = new ImageIcon(workingDirectory + "\\bullet.png");
        Image bulletImage = icon.getImage();

        PlayerBullet b = new PlayerBullet(bulletImage, WindowSize);
        b.setPosition(PlayerShip.x + 54 / 2, PlayerShip.y);
        bulletsList.add(b);
    }

    // application entry point
    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        InvadersApplication w = new InvadersApplication();
    }
}