package albums;

public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;

    public Album(String title, String artist, Genre genre, Date releaseDate) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public Album() {
        this.title = null;
        this.artist = null;
        this.genre = null;
        this.releaseDate = null;
    }

    //...
    @Override
    public boolean equals(Object obj) {return true;}
//...
    @Override
    public String toString() {return "";}
}