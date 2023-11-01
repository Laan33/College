import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicLibrary {
    private final ArrayList<Album> albums;
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
        JFrame frame = new JFrame("Music Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        JPanel albumPanel = new JPanel();
        albumPanel.setLayout(new FlowLayout());

        // Add the album cover images to the albumPanel - size 300x300 - grid layout 2x2
        for (Album album : albums) {
            try {
                BufferedImage bi = ImageIO.read(new File(RESOURCES_PATH + album.getCoverImageFile()));

                JButton albumButton = new JButton(new ImageIcon(bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

                //Make a button that just calls the showTrackListing method with the album as a parameter
                albumButton.addActionListener(e -> showTrackListing(album));
                albumPanel.add(albumButton);

            } catch (IOException e) {
                System.out.println("File not found");
            }


        }

        JPanel trackPanel = new JPanel();
        trackPanel.setLayout(new BorderLayout());
        CardLayout cardLayout = new CardLayout();
        trackPanel.setLayout(cardLayout);

        frame.add(albumPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }



    //Show a gui with the headers No. , Track name, Length. Use a table
    private void showTrackListing(Album album) {
        JFrame frame = new JFrame("Track Listing");
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
                String[] parts = line.split(",\t|,\s|,");
                if (parts.length == 3) {
                    model.addRow(parts);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");

        //Make a button that just closes the frame
        backButton.addActionListener(e -> frame.dispose());

        panel.add(backButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(MusicLibrary::new);
    }

}
