import java.io.Serializable;
import java.time.LocalDate;

public class Achievement implements Serializable {
    private String achievementName;
    private String description;
    private LocalDate dateOfAward;


    public Achievement(String achievementName, String description, LocalDate dateOfAward) {
        this.achievementName = achievementName;
        this.description = description;
        this.dateOfAward = dateOfAward;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateOfAward() {
        return dateOfAward;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Achievement otherAchievement = (Achievement) obj;

        if (!achievementName.equals(otherAchievement.achievementName)) {
            return false;
        }
        if (!description.equals(otherAchievement.description)) {
            return false;
        }
        return dateOfAward.equals(otherAchievement.dateOfAward);
    }


}
