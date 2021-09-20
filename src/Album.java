public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;
...
    @Override
    public boolean equals(Object obj) { }
...
    @Override
    public String toString() { }
}