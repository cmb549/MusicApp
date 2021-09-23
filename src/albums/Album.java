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

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    //...
    @Override
    public boolean equals(Object obj) {
        Album newAlbum = (Album)obj;
        if (newAlbum.title == this.title && newAlbum.artist == this.artist)
            return true;
        else
            return false;
    }
//...
    @Override
    public String toString() {
        String avail;
        if (isAvailable)
            avail = "is available";
        else
            avail = "is not available";
        return title + "::" + artist + "::" + genre.name() + "::" + releaseDate.toString() + avail;
    }
}