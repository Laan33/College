import java.awt.*;
import javax.swing.*;

public class DisplayRasterImage extends JFrame {
    // member data
    private static String workingDirectory;
    private Image alienImage;

    // constructor
    public DisplayRasterImage() {
        // set up JFrame
        setBounds(100, 100, 300, 300);
        setVisible(true);
        // load image from disk. Make sure you have the path right!
        // NB Windows uses \\ in paths whereas MacOS uses / in paths
        ImageIcon icon = new ImageIcon(workingDirectory + "\\alien_ship_1.png");
        alienImage = icon.getImage();
        repaint();
    }

    // application's paint method (may first happen *before* image is finished
    // loading, hence repaint() above)
    public void paint(Graphics g) {
        // draw a black rectangle on the whole canvas
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 300, 300);
        // display the image (final argument is an ‘ImageObserver’ object)
        g.drawImage(alienImage, 150, 150, null);
    }

    // application entry point
    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        System.out.println("Working Directory = " + workingDirectory);
        DisplayRasterImage d = new DisplayRasterImage();
    }
}
