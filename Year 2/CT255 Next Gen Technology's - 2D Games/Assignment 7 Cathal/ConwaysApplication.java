import java.awt.*;
import java.awt.image.*;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

//MouseMotionListener, MouseWheelListener,
public class ConwaysApplication extends JFrame implements MouseInputListener, Runnable {

    final int cells = 40; //how many cells we wants
    final int pixel = 20; //size of each cell in pixels
    private boolean[][] gamePointsBool = new boolean[cells][cells]; //array of boolean cells 

    private static final Dimension WindowSize = new Dimension(800, 800);    
    private BufferStrategy strategy;
    
    public ConwaysApplication() {
        addMouseListener(this);
        Thread t = new Thread();
        t.start();

        // Display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        this.setTitle("Conways Game of Life");

        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }

    @Override
    public void paint(Graphics g) {
        g = strategy.getDrawGraphics(); //redirecting drawing calls to offscreen buffer

        for(int i = 0; i < cells; i++) { //for all the cells on each row
            for(int j = 0; j < cells; j++) { //and all the cells in a column  
                if (gamePointsBool[i][j]) { //if the cell is true, set it to white
                    g.setColor(Color.WHITE);                     
                }
                else { //else set the cell to black
                    g.setColor(Color.BLACK);                    
                }
                g.fillRect(i * pixel, j * pixel, pixel, pixel); //paint the cell
            }
        }
        strategy.show(); //flip the buffers 
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / pixel; //getting the x of the cell  
        int y = e.getY() / pixel; //gettign the y of the cell

        gamePointsBool[x][y] = !gamePointsBool[x][y]; //change the cell to the inverse of itself
        
        repaint();
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
