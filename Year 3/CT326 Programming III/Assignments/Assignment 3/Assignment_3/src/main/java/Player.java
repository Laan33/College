

/*
String id, String username, Country country, LocalDate joinDate,
List achievements
 */

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Player implements Serializable {
    private final String id;
    private String username;
    private LocalDate joinDate;
    transient List<Achievement> achievements;


    public Player(String id, String username, Country country, LocalDate joinDate, List<Achievement> achievements) {
        this.id = id;
        this.username = username;
        this.joinDate = joinDate;
        this.achievements = achievements;
    }

    public String getId() {
        return id;
    }

    @Serial
    private void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();

        FileWriter writer = new FileWriter("achievements.csv");
        for (Achievement achievement : achievements) {
            writer.write(id + "," + achievement.getAchievementName() + "," + achievement.getDescription() + "\n");
        }
        writer.close();
    }



    @Serial
    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
        is.defaultReadObject(); // Read in all non-transient, non-static fields

        // Read in the achievements field from the achievements.csv file
        achievements = new ArrayList<>();
        String playerId = getId();
        Scanner scanner = new Scanner(new File("achievements.csv"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 4 && parts[0].equals(playerId)) {
                String achievementName = parts[1];
                String description = parts[2];
                LocalDate dateOfAward = LocalDate.parse(parts[3]);
                Achievement achievement = new Achievement(achievementName, description, dateOfAward);
                achievements.add(achievement);
            }
        }
        scanner.close();
    }



}
