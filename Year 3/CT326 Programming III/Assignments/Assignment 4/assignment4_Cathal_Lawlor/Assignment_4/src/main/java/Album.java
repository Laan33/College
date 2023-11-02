// Cathal Lawlor - 21325456

/*
Details of the albums (Artist, album name, cover image file, track listing file) are contained in
‘music_library.txt’

The album tracks are contained in their own .txt files in a ‘library’ folder.
• The cover art images are contained in a ‘covers’ folder.
 */

/**
 * A class to represent a music library
 * <p>
 *     Details of the albums (Artist, album name, cover image file, track listing file) are contained in
 *     ‘music_library.txt’
 *     <p>
 *         The album tracks are contained in their own .txt files in a ‘library’ folder.
 *         • The cover art images are contained in a ‘covers’ folder.
 *         </p>
 *         </p>
 *         @version 1.0
 *         @author Cathal Lawlor
 *
 *
 */
public class Album {
    private final String artist;
    private final String albumName;
    private final String coverImageFile;
    private final String trackListingFile;

    public Album(String artist, String albumName, String coverImageFile, String trackListingFile) {
        this.artist = artist;
        this.albumName = albumName;
        this.coverImageFile = coverImageFile;
        this.trackListingFile = trackListingFile;
    }

    /**
     * Get the artist of the album
     * @return String - the artist of the album
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Get the album name
     * @return String - the album name
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * Get the cover image file
     * @return String - the cover image file
     */
    public String getCoverImageFile() {
        return coverImageFile;
    }

    /**
     * Get the track listing file
     * @return String - the track listing file
     */
    public String getTrackListingFile() {
        return trackListingFile;
    }

    /**
     * To string method for the album class.
     */
    @Override
    public String toString() {
        return "Album{" +
                "artist='" + artist + '\'' +
                ", albumName='" + albumName + '\'' +
                ", coverImageFile='" + coverImageFile + '\'' +
                ", trackListingFile='" + trackListingFile + '\'' +
                '}';
    }
}
