package MyApplication;
import java.awt.*;
import javax.swing.*;

public class MyApplication extends JFrame {
private static final Dimension WindowSize = new Dimension(600,600);
public MyApplication() {
//Create and set up the window.
this.setTitle("Pacman, or something..");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Display the window, centred on the screen
Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
int x = screensize.width/2 - WindowSize.width/2;
int y = screensize.height/2 - WindowSize.height/2;
setBounds(x, y, WindowSize.width, WindowSize.height);
setVisible(true);
}

    public int rgbRand() {
        int randNum = (int)(Math.random()*256);


        return randNum;
    }


    //public void grid () {
        //15 x 40 y


        //int xP, yP = 0;// x & y variables passed into paint to paint square
        //int sqrSize;

/*
        for (int i = 15; i < 600; i += 15) {


            for(int j = 40; j < 600; j += 15) {
                
                


                paint(i, j);


            }


        }  */

    //}
        //new Color(rgbRand(), rgbRand(), rgbRand())


        public void paint (Graphics g) {
            super.paintComponents(g);
            g.setColor(Color.RED);
            g.drawRect(0, 0, 600, 600);
            Color mycolr = new Color(255, 200, 100);
            g.setColor(mycolr);
            g.fillRect(0, 0, 600, 600);


            //g.setColor(Color.WHITE);          

            /*
            for (int i = 0; i < 600; i += 15) {


                for(int j = 0; j < 600; j += 15 {
                    
                    
    
    
                    g.fillRect(i, j, 10, 10);
    
    
                }
    
    
            }  */

            /*
            Font f = new Font( "Times", Font.PLAIN, 24 );
            g.setFont(f);

            Color c = Color.BLACK;
            g.drawString("Pacman!", 20, 60);
            g.setColor(c); */


            //g.fillRect(30, 30, 30, 30);

          
        }      





    public static void main(String [ ] args) {
            MyApplication w = new MyApplication();
    }
}

