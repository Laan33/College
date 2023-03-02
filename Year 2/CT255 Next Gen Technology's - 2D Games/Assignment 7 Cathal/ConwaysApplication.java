import java.awt.*;
import java.awt.image.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

//MouseMotionListener, MouseWheelListener,
public class ConwaysApplication extends JFrame implements MouseInputListener, Runnable {

    final int cells = 40; //how many cells we wants
    final int pixel = 20; //size of each cell in pixels
    private boolean[][] gamePointsBool = new boolean[cells][cells]; //array of boolean cells 
    //private boolean[][][] gamePointsBool = new boolean[cells][cells][2]; //array of boolean cells 
    private int currentlyDisplayed = 0;

    

    private static final Dimension WindowSize = new Dimension(800, 800);    
    private BufferStrategy strategy;
    boolean isGamePlaying = false;
    
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

        JButton buttonStart = new JButton("Start");
        buttonStart.setBounds(0, 0, 100, 30);
        buttonStart.addActionListener(e -> {
            isGamePlaying = true; // set the game state to playing
            buttonStart.setVisible(false); // hide the button
        });

        JButton buttonRandom = new JButton("Random");
        buttonStart.setBounds(0, 0, 100, 30);
        buttonStart.addActionListener(e -> {
            isGamePlaying = true; // set the game state to playing
            buttonStart.setVisible(false); // hide the button
        });

        if (!isGamePlaying) { // if the game is not playing
            // create a new button and add it to the top left corner of the JFrame
            this.add(buttonStart);
            this.add(buttonRandom);
        }
    }

    public void random(boolean[][] gamePointsBool) {
        
    }

    public void conwayNeighbourChecker(boolean[][][] gamePointsBool, int currentlyDisplayed) {
        int[][][] liveNeighbours = new int[40][40][2];
        for (int x=0;x<40;x++) {
            for (int y=0;y<40;y++) {
                // count the live neighbours of cell [x][y][0]
                for (int xx=-1;xx<=1;xx++) {
                    for (int yy=-1;yy<=1;yy++) {
                        //if (gamePointsBool[x + xx][y + yy][currentlyDisplayed] != true || gamePointsBool[xx][yy][]!=0) {
                        if (gamePointsBool[(x + xx) % 40][(y + yy) % 40][currentlyDisplayed] != true) {
                            liveNeighbours[x][y][(currentlyDisplayed + 1) % 2]++;
                        // check cell [x+xx][y+yy][0]
                        // but.. what if x+xx==-1, etc. ?
                        }
                    }
                }
                int count = liveNeighbours[x][y][(currentlyDisplayed + 1) % 2];
                if(count < 2 || count > 3) {
                    gamePointsBool[x][y][(currentlyDisplayed + 1) % 2] = false;
                } else if(count == 3) { //if there is 3 neighbours this cells will be made alive regardless if previously dead
                    gamePointsBool[x][y][(currentlyDisplayed + 1) % 2] = true;
                } else if(count == 2 && gamePointsBool[x][y][(currentlyDisplayed + 1) % 2]) {
                    gamePointsBool[x][y][(currentlyDisplayed + 1) % 2] = true; //if there is two and cell is alive - keep alive
                }
            }      
        }


        


    }


    @Override
    public void paint(Graphics g) {
        
        try{
        if(!isGamePlaying) {
                    
        } else {
            //conwayNeighbourChecker(gamePointsBool, currentlyDisplayed);
        }
        
        g = strategy.getDrawGraphics(); //redirecting drawing calls to offscreen buffer

        

        for(int i = 0; i < cells; i++) { //for all the cells on each row
            for(int j = 0; j < cells; j++) { //and all the cells in a column  
                //if (gamePointsBool[i][j][(currentlyDisplayed + 1) % 2]) { //if the cell is true, set it to white
                if (gamePointsBool[i][j]) { //if the cell is true, set it to white
                    g.setColor(Color.WHITE);                     
                }
                else { //else set the cell to black
                    g.setColor(Color.BLACK);                    
                }
                g.fillRect(i * pixel, j * pixel, pixel, pixel); //paint the cell
            }
        }
        
        //ConwaysApplication.add(button);
        strategy.show(); //flip the buffers
        currentlyDisplayed = (currentlyDisplayed + 1) % 2;
        System.out.println("Currently displayed: " + currentlyDisplayed);
    } catch(Exception e) {
        System.out.println("oops");
    } 
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
        int y = e.getY() / pixel; //getting the y of the cell

        //gamePointsBool[x][y][currentlyDisplayed] = !gamePointsBool[x][y][currentlyDisplayed]; //change the cell to the inverse of itself
        gamePointsBool[x][y]= !gamePointsBool[x][y]; //change the cell to the inverse of itself
        
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
