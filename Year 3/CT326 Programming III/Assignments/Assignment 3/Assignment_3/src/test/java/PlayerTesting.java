
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTesting {



    /*
     * Use a test-driven development approach to implement the serialisation of
     * Player
     * objects. Write a unit test that creates a list of five Player objects, each
     * Player
     * object should be also populated with a list of Achievement objects. The
     * program
     * should then write out the list of Player objects, using Object Serialisation,
     * to a file
     * called “players.ser”.
     *
     * To test the serialisation functionality, you’ll need to deserialise the
     * written objects and
     * compare them to the ones that you previously serialised. To do this, in your
     * unit test,
     * load up the Player objects from the file using Object Serialisation and
     * compare the
     * Player objects that you serialised with those that you deserialised,
     * appropriately
     */

    ArrayList<Player> playerList;

    @BeforeEach
    void setUp() throws IOException {
        playerList = new ArrayList<>();

        Files.deleteIfExists(Paths.get("achievements.csv"));
        
        ArrayList<Achievement> achievements = new ArrayList<>();
        

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

    @AfterEach
    void tearDown() {
    }


    @Test
    void testSerialisationValid() {
        ObjectOutputStream os = null;
        ObjectInputStream is = null;

        try {
            os = new ObjectOutputStream(new FileOutputStream("players.ser"));
            is = new ObjectInputStream(new FileInputStream("players.ser"));

            os.writeObject(playerList);


            ArrayList<Player> playersIn = (ArrayList<Player>) is.readObject();

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
