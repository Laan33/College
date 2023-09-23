package data;

import java.util.ArrayList;

public class NewArtistsCreation {

    public NewArtistsCreation() {    
    }
    
    public ArrayList<Artist> getArtistsList() {
        ArrayList<Artist> artistsInfo = new ArrayList<>();

        Artist artist = new Artist(
      "Jagger","Mick","British",1943,"English singer, songwriter and musician. He is the frontman and one of the founder members of the rock band the Rolling Stones." ,"https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/RStonesHydePark030722_%2849_of_125%29_%2852193656268%29_%28cropped%29.jpg/800px-RStonesHydePark030722_%2849_of_125%29_%2852193656268%29_%28cropped%29.jpg");
        artistsInfo.add(artist);

        artist = new Artist(
      "Richards", "Keith", "British",1943, " \"Keith Richard\", is an English musician and songwriter who has achieved international fame as the co-founder, guitarist, secondary vocalist, and co-principal songwriter of the Rolling Stones.", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Keith_Richards_2018.jpg/800px-Keith_Richards_2018.jpg");
        artistsInfo.add(artist);

        artist = new Artist(
      "Plant", "Robert", "British", 1948, "He was the lead singer and lyricist of the rock band Led Zeppelin from its founding in 1968 until their breakup in 1980", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Robert_Plant_at_the_Palace_Theatre%2C_Manchester.jpg/800px-Robert_Plant_at_the_Palace_Theatre%2C_Manchester.jpg");
        artistsInfo.add(artist);

        artist = new Artist(
      "Page", "Jimmy", "British", 1944, "English musician who achieved international success as the guitarist and founder of the rock band Led Zeppelin.", "https://upload.wikimedia.org/wikipedia/commons/9/9f/Jimmy_Page_at_the_Echo_music_award_2013.jpg");
        artistsInfo.add(artist);

        artist = new Artist(
      "Hendrix", "Jimi", "American", 1942, 1970, "James Marshall \"Jimi\" Hendrix (born Johnny Allen Hendrix) was an American guitarist, songwriter and singer. Although his mainstream career spanned only four years, he is widely regarded as one of the most influential electric guitarists in the history of popular music, and one of the most celebrated musicians of the 20th century.", "https://upload.wikimedia.org/wikipedia/commons/a/ae/Jimi_Hendrix_1967.png");
        artistsInfo.add(artist);    


        return artistsInfo;
    }
}
