package albums;

import sun.awt.windows.ThemeReader;

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
        this.isAvailable = true;
    }

    public Album() {
        //this.title = null;
        //this.artist = null;
        //this.genre = null;
        //this.releaseDate = null;
        //this.isAvailable = Boolean.parseBoolean(null);
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

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public boolean rentOut() {
        if (!isAvailable) { // if unavailable
            return false;
        }
        isAvailable = false;
        return true;
    }

    public boolean returnAlbum() {
        if (isAvailable) { // if available
            return false;
        }
        isAvailable = true;
        return true;
    }

    //...
    @Override
    public boolean equals(Object obj) {
        Album newAlbum = (Album) obj;
        if (newAlbum.title.equals(this.title) && newAlbum.artist.equals(this.artist))
            return true;
        else
            return false;
    }

    //...
    @Override
    public String toString() {
        String avail;
        String retString = title + "::" + artist;
        /*
        if (isAvailable)
            avail = "is available";
        else
            avail = "is not available";
         */
        String myGenre = "";
        String myRelDate = "";
        if (genre != null) {
            myGenre = genre.name();
            retString = retString + "::" + myGenre;
        }
        if (releaseDate != null) {
            myRelDate = releaseDate.toString();
            retString = retString + "::" + myRelDate;
        }
        return retString;
    }
}