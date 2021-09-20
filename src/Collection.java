public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection
...
    private int find(Album album) {} //find the album index, or return NOT_FOUND
    private void grow() {} //increase the capacity of the array list by 4
    public boolean add(Album album) {}
    public boolean remove(Album album) {}
    public boolean lendingOut(Album album) {} //set to not available
    public boolean returnAlbum(Album album) {} //set to available
    public void print() {} //display the list without specifying the order
    public void printByReleaseDate() {}
    public void printByGenre() {}