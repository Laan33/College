import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import javax.swing.JFrame;

public class CaveGen extends JFrame implements Runnable {

    final int cells = 200; // how many cells we wants
    final int pixel = 4; // size of each cell in pixels
    private boolean[][][] gamePointsBool = new boolean[cells][cells][2]; // array of boolean cells

    private static final Dimension WindowSize = new Dimension(800, 800);

    //I have left buffers in as it looks much nicer
    private BufferStrategy strategy;
    private boolean isGraphicsInstantiated = false;

    public CaveGen() {
        // Display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        this.setTitle("Cave procedurally generated");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        for (int i = 0; i < cells; i++) { // initialising cells
            for (int j = 0; j < cells; j++) {
                gamePointsBool[i][j][0] = sixtyFourtyGen();
            }
        }

        isGraphicsInstantiated = true; // everything is instantiated

        Thread t = new Thread(this);
        t.start();

    }

    public boolean sixtyFourtyGen() {
        //wall is 60% - true - 0-2 - 3 
        //cave is 40% - false - 3-4 - 2
        //total is 4 -> 0-4 
        Random rand = new Random();
        int n = rand.nextInt(5); //[0 - 4]
        //System.out.println(n);
        if(n <= 2) {
            return true;
        } else {
            return false;
        }
    }

    public void paint(Graphics g) {
        if (isGraphicsInstantiated) {
            g = strategy.getDrawGraphics(); // redirecting drawing calls to offscreen buffer

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 800);

            for (int i = 0; i < cells; i++) { // for all the cells on each row
                for (int j = 0; j < cells; j++) { // and all the cells in a column
                    if (gamePointsBool[i][j][0]) { // if the cell is true, set it to white
                        g.setColor(Color.WHITE);
                    } else { // else set the cell to black
                        g.setColor(Color.BLACK);
                    }
                    g.fillRect(i * pixel, j * pixel, pixel, pixel); // paint the cell
                }
            }

            strategy.show(); // flip the buffers


        }
    }

    public void reCalc() {
        for (int x = 0; x < cells; x++) {
            for (int y = 0; y < cells; y++) {
                // count the live neighbours of cell [x][y][0]
                int count = 0;
                for (int xx = -1; xx <= 1; xx++) {
                    for (int yy = -1; yy <= 1; yy++) {
                        if (xx != 0 || yy != 0) {
                            // check cell [x+xx][y+yy][0] - rolls over if xxx = -1 using modulus
                            int xxx = Math.floorMod(x + xx, (cells -1)), yyy = Math.floorMod(y + yy, (cells -1));
                            if (gamePointsBool[xxx][yyy][0])
                                count++;
                        }
                    }
                }
                if (count < 5)
                    gamePointsBool[x][y][1] = false;//if has less than 5 neightbours then it is a floor / cave 
                else 
                    gamePointsBool[x][y][1] = true; //if has at least 5 neightbours then it is a wall 
            }
        }



    }

    public void run() {
        int numRuns = 0;
        while (numRuns < 4) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException r) {
                r.printStackTrace();
            }
            reCalc();                                         
                for (int i = 0; i < cells; i++) {
                    for (int j = 0; j < cells; j++) {
                        gamePointsBool[i][j][0] = gamePointsBool[i][j][1];
                    }
                }            
            this.repaint();
            numRuns++;
        }
    }




    public static void main(String[] args) {
        CaveGen w = new CaveGen();
    }

}
