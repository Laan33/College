import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Cathal Lawlor - 21325456

/**
 * A class to represent a music library
 * <p>
 *     Details of the albums (Artist, album name, cover image file, track listing file) are contained in
 *     ‘music_library.txt’
 *     <p>
 *         The album tracks are contained in their own .txt files in a ‘library’ folder.
 *         • The cover art images are contained in a ‘covers’ folder.
 *     </p>
 * </p>
 *
 * @version 1.0
 * @author Cathal Lawlor
 *
 *
 */
public class MusicLibrary {
    private final ArrayList<Album> albums; //ArrayList of albums
    JFrame frame = new JFrame("Music Library"); //Frame to display the GUI
    private static final String RESOURCES_PATH = "src/main/resources/"; //Path to the resources folder

    /**
     * Constructor for the MusicLibrary class
     * <p>
     *     Creates an ArrayList of albums, and calls the loadAlbums method to load the albums from the file
     *     Creates the GUI
     * </p>
     */
    public MusicLibrary() {
        albums = new ArrayList<>();
        loadAlbums();
        createGUI();
    }

    /**
     * Load the albums from the file
     * <p>
     *     Reads the albums from the file, and adds them to the ArrayList if they have the correct number of parts
     * </p>
     */
    public void loadAlbums() {
        File file = new File(RESOURCES_PATH + "/music_library.txt");
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    albums.add(new Album(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Music library file not found");
        }
    }

    /**
     * Create the GUI
     * <p>
     *     Create a panel with a button for each album, and add it to the frame. The button calls the showTrackListing method.
     *     Album cover images are loaded from the covers folder, where they are scaled to 250x250
     * </p>
     */
    private void createGUI() {
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        JPanel albumPanel = new JPanel();
        albumPanel.setLayout(new FlowLayout());

        for (Album album : albums) {
            try {
                BufferedImage bi = ImageIO.read(new File(RESOURCES_PATH + album.getCoverImageFile()));

                JButton albumButton = new JButton(new ImageIcon(bi.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));

                //Make a button that calls the showTrackListing
                albumButton.addActionListener(e -> showTrackListing(album));
                albumPanel.add(albumButton);

            } catch (IOException e) {
                System.out.println("Album cover image file not found");
            }
        }

        frame.add(albumPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    /**
     * Show the track listing for an album
     * <p>
     *     Reads the track listing from the file, splits the line and adds it to a JTable
     *     Adds a button to close the frame
     * </p>
     * @param album - the album to show the track listing for
     */
    private void showTrackListing(Album album) {
        frame.getContentPane().removeAll(); //Remove all components from the frame

        JTable table = new JTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Add the column headers
        model.addColumn("No.");
        model.addColumn("Track name");
        model.addColumn("Length");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_PATH + album.getTrackListingFile()));
            String line;

            // Read and parse each line from the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\t|, |,"); //Split on comma, comma and tab, or comma and space
                if (parts.length == 3) {
                    model.addRow(parts);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Track-list file not found for " + album.getAlbumName());
        }

        JPanel panel = new JPanel(); //Panel to hold the table and button
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER); //Add the table to the panel

        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> createGUI()); //Add an action listener to the button to go back to the album selection screen
        panel.add(backButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    /**
     * Main method
     * <p>
     *     Create a new instance of the MusicLibrary class
     * </p>
     * @see MusicLibrary
     *
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MusicLibrary::new);
    }

}
