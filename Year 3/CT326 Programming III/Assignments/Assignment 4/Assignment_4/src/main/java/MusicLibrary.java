import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class MusicLibrary {
    private final ArrayList<Album> albums;
    JFrame frame = new JFrame("Music Library");
    private static final String RESOURCES_PATH = "src/main/resources/";

    public MusicLibrary() {
        albums = new ArrayList<>();
        loadAlbums();
        createGUI();
    }

    // Using scanner to read the file, and then using the split method to split the
    // string into an array of strings
    public void loadAlbums() {
        try {
            File file = new File(RESOURCES_PATH + "/music_library.txt");

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    albums.add(new Album(parts[0], parts[1], parts[2], parts[3]));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

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
                System.out.println("File not found");
            }
        }

        frame.add(albumPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    //Show a gui with the headers No. , Track name, Length. Use a table - Use the same frame as createGUI
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

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");

        //Button to close the frame
        backButton.addActionListener(e -> createGUI());
        panel.add(backButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(MusicLibrary::new);
    }

}
