

/*
String id, String username, Country country, LocalDate joinDate,
List achievements
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public class Player implements Serializable {
    String id;
    String username;
    Country country;
    LocalDate joinDate;
    transient List<Achievement> achievements;


    public Player(String id, String username, Country country, LocalDate joinDate, List<Achievement> achievements) {
        this.id = id;
        this.username = username;
        this.country = country;
        this.joinDate = joinDate;
        this.achievements = achievements;
    }

    public void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();


    }

    private void readObject(ObjectInputStream is) throws IOException {
        try {
            is.defaultReadObject();

            /*LocalDate regDate = (LocalDate) is.readObject();
            String reg = (String) is.readObject();

            this.setRegistration(new Registration(regDate, reg));*/

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
