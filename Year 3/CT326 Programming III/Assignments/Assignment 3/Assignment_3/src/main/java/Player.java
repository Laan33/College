

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Cathal Lawlor - 21325456
 *
 * The Player class represents a player in the system.
 *
 * Has a unique id, username, country, join date and achievements.
 *
 */
public class Player implements Serializable {
    private final String id;
    private String username;
    private LocalDate joinDate;
    transient List<Achievement> achievements;


    /**
     * Constructor for the Player class.
     *
     * @param id
     * @param username
     * @param country
     * @param joinDate
     * @param achievements
     */
    public Player(String id, String username, Country country, LocalDate joinDate, List<Achievement> achievements) {
        this.id = id;
        this.username = username;
        this.joinDate = joinDate;
        this.achievements = achievements;
    }

    /**
     * Return the id of the player.
     * @return Id
     */
    public String getId() {
        return id;
    }

    /**
     * Using default serialization, write the object to the object output stream.
     * Write the default fields, then write the achievements to the achievements.csv file.
     * Close the file writer.
     *
     * @param os
     * @throws IOException
     */
    @Serial
    private void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();

        FileWriter writer = new FileWriter("achievements.csv", true);
        for (Achievement achievement : achievements) {
            writer.append(id + "," + achievement.getAchievementName() + "," + achievement.getDescription() + "," + achievement.getDateOfAward() + "\n");
        }
        writer.close();
    }


    /**
     * Using the achievements.csv file, read in the achievements of the player.
     * Split the line by the comma, and check that the line is valid.
     * If it is, create a new achievement and add it to the list.
     * Close the scanner.
     * Set the achievements field.
     *
     * @param is
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Serial
    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
        is.defaultReadObject(); // Read in all non-transient, non-static fields

        // Read in the achievements field from the achievements.csv file
        achievements = new ArrayList<>();
        String playerId = getId();
        Scanner scanner = new Scanner(new File("achievements.csv"));

        // Read in the achievements from the file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            // Check that the line is valid - assign the values to the achievement
            if (parts.length == 4 && parts[0].equals(playerId)) {
                String achievementName = parts[1];
                String description = parts[2];
                LocalDate dateOfAward = LocalDate.parse(parts[3]);
                Achievement achievement = new Achievement(achievementName, description, dateOfAward);
                achievements.add(achievement); // Add the achievement to the list
            }
        }
        scanner.close(); // Close the scanner

        // Set the achievements field
        setAchievements(achievements);
    }

    /**
     * Set the achievements of the player.
     * @param achievements
     */
    private void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    /**
     * Equals method for the Player class.
     * First checks if the objects are the same, then checks if the classes are the same.
     * Then checks if the id, username, achievements and join date are the same.
     * If all of these are the same, then the objects are equal.
     *
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Player otherPlayer = (Player) obj;

        if (!id.equals(otherPlayer.id)) {
            return false;
        }
        if (!username.equals(otherPlayer.username)) {
            return false;
        }
        if (!achievements.equals(otherPlayer.achievements)) {
            return false;
        }
        return joinDate.equals(otherPlayer.joinDate);
    }

}
