import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicLibrary {
    private ArrayList<Album> albums;
    private JFrame frame;
    private JPanel albumPanel;
    private JPanel trackPanel;
    private CardLayout cardLayout;
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
                System.out.println(line); // TODO: Remove this line
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void createGUI() {
        frame = new JFrame("Music Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        albumPanel = new JPanel();
        albumPanel.setLayout(new FlowLayout());

        for (int i = 0; i < albums.size(); i++) {
            Album album = albums.get(i);
            // Set the imageIcon to the album cover image file in resources
            // ImageIcon imageIcon = );
            JButton albumButton = new JButton(new ImageIcon(RESOURCES_PATH + album.getCoverImageFile()));
            albumButton.setActionCommand(Integer.toString(i));
            albumButton.addActionListener(new AlbumButtonListener());
            albumPanel.add(albumButton);
        }

        trackPanel = new JPanel();
        trackPanel.setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        trackPanel.setLayout(cardLayout);

        frame.add(albumPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class AlbumButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int albumIndex = Integer.parseInt(e.getActionCommand());
            Album album = albums.get(albumIndex);
            showTrackListing(album);
            // System.out.println(album);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MusicLibrary::new);
    }

}
