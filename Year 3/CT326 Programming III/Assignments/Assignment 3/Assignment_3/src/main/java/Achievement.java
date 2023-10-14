import java.io.Serializable;
import java.time.LocalDate;

/**
 * The Achievement class represents an achievement in the players' system.
 */
public class Achievement implements Serializable {
    private String achievementName;
    private String description;
    private LocalDate dateOfAward;


    /**
     * Constructor for the Achievement class.
     *
     * @param achievementName
     * @param description
     * @param dateOfAward
     */
    public Achievement(String achievementName, String description, LocalDate dateOfAward) {
        this.achievementName = achievementName;
        this.description = description;
        this.dateOfAward = dateOfAward;
    }

    /**
     * Get the name of the achievement.
     * @return Achievement name
     */
    public String getAchievementName() {
        return achievementName;
    }

    /**
     * Get the description of the achievement.
     * @return Achievement description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the date of the achievement.
     * @return Achievement date
     */
    public LocalDate getDateOfAward() {
        return dateOfAward;
    }

    /**
     * Override of the equals method, to compare two achievements.
     * If the achievement name, description and date of award are the same, return true.
     * Otherwise, return false.
     *
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // If the object is the same, return true
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) { // If the object is null or the classes are different, return false
            return false;
        }

        Achievement otherAchievement = (Achievement) obj; // Cast the object to an achievement

        // If the achievement names are different, return false
        if (!achievementName.equals(otherAchievement.achievementName)) {
            return false;
        } // If the descriptions are different, return false
        if (!description.equals(otherAchievement.description)) {
            return false;
        } // Return result of comparing the dates
        return dateOfAward.equals(otherAchievement.dateOfAward);
    }


}
