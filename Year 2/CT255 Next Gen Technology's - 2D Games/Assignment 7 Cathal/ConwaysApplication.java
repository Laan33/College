import java.awt.*;
import java.awt.image.*;
import java.util.Random;

import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

//MouseMotionListener, MouseWheelListener,
public class ConwaysApplication extends JFrame implements MouseInputListener, Runnable {

    final int cells = 40; // how many cells we wants
    final int pixel = 20; // size of each cell in pixels
    // private boolean[][] gamePointsBool = new boolean[cells][cells]; //array of
    // boolean cells
    private boolean[][][] gamePointsBool = new boolean[cells][cells][2]; // array of boolean cells
    // private int currentlyDisplayed = 0;

    private static final Dimension WindowSize = new Dimension(800, 800);
    private BufferStrategy strategy;
    private boolean isGraphicsInstantiated = false, isGamePlaying = false;

    public ConwaysApplication() {
        // Display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        this.setTitle("Conways Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createBufferStrategy(2);
        strategy = getBufferStrategy();

        addMouseListener(this);

        for (int i = 0; i < cells; i++) {
            for (int j = 0; j < cells; j++) {
                gamePointsBool[i][j][0] = false;
            }
        }

        isGraphicsInstantiated = true;

        Thread t = new Thread(this);
        t.start();
        

    }

    public void random(boolean[][][] gamePointsBool) {
        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 40; y++) {
                Random rand = new Random();
                int randomNum = rand.nextInt(2); // generate either 0 or 1 randomly
                gamePointsBool[x][y][0] = (randomNum == 1);
                gamePointsBool[x][y][1] = (randomNum == 1);
            }
        }
    }

    

    public void paint(Graphics g) {
        if(isGraphicsInstantiated) {
            g = strategy.getDrawGraphics(); //redirecting drawing calls to offscreen buffer
           
        g.setColor(Color.BLACK);   
        g.fillRect(0, 0, 800, 800); 

        /*
        if(!isGamePlaying) {
            g.setColor(Color.WHITE);
            g.fillRect(10, 35, 200, 40); 
            g.setColor(Color.GREEN); 
            g.fillRect(15, 40, 90, 30); 
            g.fillRect(115, 40, 90, 30); 

            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.BOLD, 17));
            g.drawString("START", 25, 60);
            g.drawString("RANDOM", 120, 60);
        
        } */
              
        for(int i = 0; i < cells; i++) { //for all the cells on each row
            for(int j = 0; j < cells; j++) { //and all the cells in a column  
                //if (gamePointsBool[i][j][(currentlyDisplayed + 1) % 2]) { //if the cell is true, set it to white
                if (gamePointsBool[i][j][0]) { //if the cell is true, set it to white
                    g.setColor(Color.WHITE);                     
                }
                else { //else set the cell to black
                    g.setColor(Color.BLACK);                    
                }
                g.fillRect(i * pixel, j * pixel, pixel, pixel); //paint the cell
            }
        }
        
        if(!isGamePlaying) {
            g.setColor(Color.WHITE);
            g.fillRect(10, 35, 200, 40); 
            g.setColor(Color.GREEN); 
            g.fillRect(15, 40, 90, 30); 
            g.fillRect(115, 40, 90, 30); 

            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.BOLD, 17));
            g.drawString("START", 25, 60);
            g.drawString("RANDOM", 120, 60);
        
        }
        
        strategy.show(); //flip the buffers
        
    } 
    }


    public void run() {        
        while (1 == 1) {
            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isGamePlaying) {
                
                for (int i = 0; i < cells; i++) {
                    for (int j = 0; j < cells; j++) {
                        gamePointsBool[i][j][1] = false;
                    }
                }
                for (int x = 0; x < 40; x++) {
                    for (int y = 0; y < 40; y++) {
                        int neighbourCount = 0;
                        // count the live neighbours of cell [x][y][0]
                        for (int xx = -1; xx <= 1; xx++) {
                            for (int yy = -1; yy <= 1; yy++) {
                                //if x+xx==-1, etc. we use modulus to loop back
                                //java % 39 on -1 isn't 39, it's still -1..., thats why we use .floorMod
                                int X = Math.floorMod(x + xx, 39);
                                int Y = Math.floorMod(y + yy, 39);

                                if (gamePointsBool[X][Y][0]) {
                                    neighbourCount++;
                                }
                            }
                        }

                        if (neighbourCount < 2 && gamePointsBool[x][y][0]) {
                            gamePointsBool[x][y][1] = false;
                        } else if(neighbourCount > 3 && gamePointsBool[x][y][0]) {
                            gamePointsBool[x][y][1] = false;
                        } else if ((neighbourCount == 3 || neighbourCount == 2) && gamePointsBool[x][y][0]) { // if there is 3 neighbours this cells will be kept alive / made alive
                            gamePointsBool[x][y][1] = true;
                        } else if (neighbourCount == 3 && !gamePointsBool[x][y][0]) {
                            gamePointsBool[x][y][1] = true; // if there is two and cell is alive - keep alive
                        }
                    }

                }

                for (int i = 0; i < cells; i++) {
                    for (int z = 0; z < cells; z++) {
                        gamePointsBool[i][z][0] = gamePointsBool[i][z][1];
                    }
                }
            }

            this.repaint();
        }

    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX() / pixel; // getting the x of the cell
        int y = e.getY() / pixel; // getting the y of the cell

        if (!isGamePlaying && e.getX() < 110 && e.getY() < 70) {
            isGamePlaying = true; //start game
        }
        if (!isGamePlaying && e.getX() > 110 && e.getX() < 210 && e.getY() < 70) {
            random(gamePointsBool); //randomise the cells
        }
        gamePointsBool[x][y][0] = !gamePointsBool[x][y][0]; // change the cell to the inverse of itself

        this.repaint();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    public static void main(String[] args) {
        ConwaysApplication w = new ConwaysApplication();
    }

}
