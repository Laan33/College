

/*
Details of the albums (Artist, album name, cover image file, track listing file) are contained in
‘music_library.txt’

The album tracks are contained in their own .txt files in a ‘library’ folder.
• The cover art images are contained in a ‘covers’ folder.
 */
public class Album {
    private String artist;
    private String albumName;
    private String coverImageFile;
    private String trackListingFile;

    public Album(String artist, String albumName, String coverImageFile, String trackListingFile) {
        this.artist = artist;
        this.albumName = albumName;
        this.coverImageFile = coverImageFile;
        this.trackListingFile = trackListingFile;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getCoverImageFile() {
        return coverImageFile;
    }

    public String getTrackListingFile() {
        return trackListingFile;
    }

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
