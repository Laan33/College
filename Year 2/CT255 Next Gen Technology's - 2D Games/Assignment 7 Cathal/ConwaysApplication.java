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
    private boolean[][][] gamePointsBool = new boolean[cells][cells][2]; // array of boolean cells

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

        for (int i = 0; i < cells; i++) { // initialising cells
            for (int j = 0; j < cells; j++) {
                gamePointsBool[i][j][0] = false;
            }
        }

        isGraphicsInstantiated = true; // everything is instantiated

        Thread t = new Thread(this);
        t.start();

    }

    public void random(boolean[][][] gamePointsBool) {
        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 40; y++) {
                Random rand = new Random();
                int randomNum = rand.nextInt(2); // generate either 0 or 1 randomly
                gamePointsBool[x][y][0] = (randomNum == 1); //if one then true, 0 false
            }
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

            if (!isGamePlaying) { //painting buttons
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

            strategy.show(); // flip the buffers

        }
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException r) {
                r.printStackTrace();
            }
            if (isGamePlaying) {
                for (int i = 0; i < cells; i++) {
                    for (int j = 0; j < cells; j++) {
                        gamePointsBool[i][j][1] = false;
                    }
                }
                for (int x = 0; x < 40; x++) {
                    for (int y = 0; y < 40; y++) {
                        // count the live neighbours of cell [x][y][0]
                        int count = 0;
                        for (int xx = -1; xx <= 1; xx++) {
                            for (int yy = -1; yy <= 1; yy++) {
                                if (xx != 0 || yy != 0) {
                                    // check cell [x+xx][y+yy][0] - rolls over if xxx = -1 using modulus
                                    int xxx = Math.floorMod(x + xx, 39), yyy = Math.floorMod(y + yy, 39);
                                    if (gamePointsBool[xxx][yyy][0]) count ++;
                                }
                            }
                        }
                        if (count < 2 && gamePointsBool[x][y][0]) gamePointsBool[x][y][1] = false;
                        else if ((count == 2 || count == 3) && gamePointsBool[x][y][0]) gamePointsBool[x][y][1] = true;
                        else if (count > 3 && gamePointsBool[x][y][0]) gamePointsBool[x][y][1] = false;
                        else if (!gamePointsBool[x][y][0] && count == 3) gamePointsBool[x][y][1] = true;
                    }
                }
                for (int i = 0; i < cells; i++) {
                    for (int j = 0; j < cells; j++) {
                        gamePointsBool[i][j][0] = gamePointsBool[i][j][1];
                    }
                }
            }
            this.repaint();
        }
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX() / pixel; // getting the x of the cell
        int y = e.getY() / pixel; // getting the y of the cell

        if (!isGamePlaying && e.getX() < 110 && e.getY() < 70) { //start region
            isGamePlaying = true; // start game
        }
        if (!isGamePlaying && e.getX() > 110 && e.getX() < 210 && e.getY() < 70) { //random region
            random(gamePointsBool); // randomise the cells
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
