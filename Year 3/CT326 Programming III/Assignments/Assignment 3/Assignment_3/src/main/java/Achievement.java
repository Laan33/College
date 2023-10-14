import java.io.Serializable;
import java.time.LocalDate;

public class Achievement implements Serializable {
    String achievementName;
    String description;
    LocalDate dateOfAward;


    public Achievement(String achievementName, String description, LocalDate dateOfAward) {
        this.achievementName = achievementName;
        this.description = description;
        this.dateOfAward = dateOfAward;

    }
}
