import javax.swing.JFrame;
import java.awt.*;


public class InvadersApplication extends JFrame implements Runnable{
    //Cathal Lawlor - 21325456 - Assignment 2


    
    private static final Dimension WindowSize = new Dimension(600, 600); //JFrame screen size
    private static final int NUMALIENS = 30; //number of alien enemies
    private Sprite2D[] AliensArray = new Sprite2D[NUMALIENS];//array of square objects
    private Sprite2D PlayerShip;
    
    public static void main(String[] args) { //instantiating the application
        InvadersApplication invadersGame = new InvadersApplication();              
    }   
   
    public InvadersApplication() {
        this.setTitle("Space Invaders - bootlegged"); //setting up base JFrame

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 

        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);


        //holds array of game objects
        //instanciates array.
        for(int i = 0; i < AliensArray.length; i++) {
            AliensArray[i] = new Sprite2D();            
        } 
        
        //creates and starts a thread
        Thread t = new Thread(this);
        t.start();
    }

    public void paint(Graphics g) { //paint function 
        /*
        g.setColor(Color.WHITE);       
        g.fillRect(0, 0, 600, 600);    //setting back the background each time it is called  

        //having all the objects in the array to paint themselves with a for loop
        for (GameObject currentObject : colourObjects) currentObject.paint(g); 

        */
    }



    
    //using the thread to perform animation of the gameobjects by calling their move methods
    @Override
    public void run() {
        System.out.println("Thread started");
        while(true) {            
            try {    
                //calling for paint, paints a white background, proceeds to call all objects to repaint themselves       
                repaint(); 

                //calling move on colour objects
                for (GameObject currentObject : colourObjects) currentObject.move();
                
                Thread.sleep(250); //wait 250 milliseconds to move again

            } catch (InterruptedException e) {                
                e.printStackTrace();
            }            
        }      
    }
}
}
