package data;

/**
 *
 * @author catha
 */

public class Artist {

    private static int lastAssignedID = 0;
    
    private int artistID;
    
    private String surname;
    private String firstName;
    private String nationality;
    private int yearOfBirth;
    private int yearOfDeath;
    private String biography;
    private String artistPhoto;

    //artist constructor - no date of death 
    public Artist(String surname, String firstName, String nationality, int yearOfBirth, String biography, String artistPhoto) {
        this.artistID = ++lastAssignedID;
        this.surname = surname;
        this.firstName = firstName;
        this.nationality = nationality;
        this.yearOfBirth = yearOfBirth;
        this.biography = biography;
        this.artistPhoto = artistPhoto;
    }

    //artist constructor - with date of death
    public Artist(String surname, String firstName, String nationality, int yearOfBirth, int yearOfDeath, String biography, String artistPhoto) {
        this.artistID = ++lastAssignedID;
        this.surname = surname;
        this.firstName = firstName;
        this.nationality = nationality;
        this.yearOfBirth = yearOfBirth;
        this.yearOfDeath = yearOfDeath;
        this.biography = biography;
        this.artistPhoto = artistPhoto;
    }

    public String getSurname() {
        return surname;
    }

	public String getFirstName() {
        return firstName;
    }

    public String getNationality() {
        return nationality;
    }

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public int getYearOfDeath() {        
		return yearOfDeath;
	}

    public String getDetails() {
        return null;
    }

    public int getArtistID() {
        return artistID;
    }

    public String getBiography() {
        return biography;
    }

    public String getArtistPhoto() {
        return artistPhoto;
    }


}

    
