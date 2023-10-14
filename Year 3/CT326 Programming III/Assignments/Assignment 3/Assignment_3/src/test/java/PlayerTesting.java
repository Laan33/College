
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The PlayerTesting class tests the serialisation and deserialisation of the Player class.
 */
public class PlayerTesting {



    ArrayList<Player> playerList;

    /**
     * Set up the test data - create a list of players, list of achievements, add achievements to players, add players to player list.
     * Delete achievements.csv if it exists.
     *
     * @throws IOException
     */
    @BeforeEach
    void setUp() throws IOException {
        playerList = new ArrayList<>(); // Create list of players

        Files.deleteIfExists(Paths.get("achievements.csv")); // Delete achievements.csv if it exists

        ArrayList<Achievement> achievements = new ArrayList<>(); // Create list of achievements


        achievements.add(new Achievement("Gaisce", "Bronze", LocalDate.of(2019, 5, 2)));
        achievements.add(new Achievement("Volunteer of the month",
                "Rowing Ireland Volunteer of the month - October", LocalDate.of(2020, 11, 1)));

        playerList.add(new Player("10", "Cathal", Country.SPAIN, LocalDate.of(2021, 4, 12), achievements));

        for (int i = 0; i < 3; i++) {
            achievements = new ArrayList<>();
            achievements.add(new Achievement("Achievement num: " + i, "Level: " + i + 2, LocalDate.of(2019, 5, 2)));
            achievements.add(new Achievement("Volunteer of the month",
                    "Rowing Ireland Volunteer of the month - October", LocalDate.of(2020, 11, 1)));
            achievements.add(new Achievement("Gaisce", "Bronze", LocalDate.of(2019, 5, 2)));

            playerList.add(new Player("" + (i + 1), "Player: " + i, Country.KIRIBATI, LocalDate.of(2015, 2, 8),
                    achievements));
        }

        achievements = new ArrayList<>();
        achievements.add(new Achievement("Achievement 7", "Gold", LocalDate.of(2012, 5, 9)));
        achievements.add(new Achievement("Achieve everything", "Well done", LocalDate.of(2019, 2, 18)));

        playerList.add(new Player("562", "Matt", Country.PANAMA, LocalDate.of(2000, 2, 17), achievements));

    }

    /**
     * Tear down the test data.
     *
     */
    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(Paths.get("players.ser"));
        } catch (IOException e) {
            System.err.println("An error occurred while deleting the file: " + e.getMessage());
        }
    }

    /**
     * Test the serialisation and deserialisation of the Player class.
     *
     * Create file writer for players.ser, write players to file, close file writer, create file reader for players.ser, read players from file, close file reader,
     *
     * Check that players read from file are the same as players in player list.     *
     * Delete achievements.csv and players.ser files.     *
     * If an exception is thrown, fail the test.
     *
     **/
     @Test
    void testSerialisationValid() {
        ObjectOutputStream os = null;
        ObjectInputStream is = null;

        // Write the objects to a file
        try {
            os = new ObjectOutputStream(new FileOutputStream("players.ser"));
            is = new ObjectInputStream(new FileInputStream("players.ser"));

            os.writeObject(playerList);

            // Read the objects back in
            ArrayList<Player> playersIn = (ArrayList<Player>) is.readObject();

            // Check that the objects are the same
            for(int i = 0; i < playersIn.size(); i++) {
                assertEquals(playersIn.get(i) , playerList.get(i));}

        } catch (NotSerializableException e) {
            fail("Some object isn't serializable.");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the streams
            try {
                if(os != null)
                    os.close();
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
