package albums;

public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection
    //...
    public Collection(){
        albums = new Album[4];
        numAlbums = 0;
    }
    private int find(Album album) {
        for(int i = 0; i < albums.length; i++){
            if(albums[i] == null){//if position is null then skip
                continue;
            }
            if(albums[i].equals(album)){ //Compare current position of array to album to find
                return i;
            }
        }
        return -1;
    } //find the album index, or return NOT_FOUND
    private void grow() {
        Album[] albumsToKeep = new Album[albums.length + 4]; //create new array of +4 length
        for(int i = 0; i < albums.length; i++){
            albumsToKeep[i] = albums[i];//copy both albums
        }
        albums = albumsToKeep; //changes pointer
    } //increase the capacity of the array list by 4
    public boolean add(Album album) {
        if(find(album) != -1) {
            return false;
        }
        for(int i = 0; i < albums.length; i++){
            if(albums[i] == null){//empty position
                albums[i] = album;//save at that position
                return true;
            }
        }
        //return false; possible if not to use the following
        grow();
        return add(album);
    }
    public boolean remove(Album album) {
        int index = find(album);
        if(index == -1){
            return false;
        }
        album = albums[index];
        albums[index] = null;
        //System.out.println(album.toString() + " >> deleted.");
        return true;
    }

    public boolean lendingOut(Album album) {
        int index = find(album);
        if(index == -1) {
            return false;
        }
        //boolean returnVal = albums[index].rentOut();
        //if (returnVal)
        //System.out.println(albums[index].toString() + " >> lending out and set to not available.");
        //return returnVal;
        return albums[index].rentOut();

    } //set to not available

    public boolean returnAlbum(Album album) {
        int index = find(album);
        if(index == -1) {
            return false; //System.out.println(album.toString() + " >> return cannot be completed.");
        }
        System.out.println(album.toString());
        return albums[index].returnAlbum();
    } //set to available

    public void print() {
        System.out.println("*List of albums in the collection.");
        for(int i = 0; i < albums.length; i++){
            if(albums[i] == null){//if position is null then skip
                continue;
            }
            String avail;
            if (albums[i].getIsAvailable())
                avail = "is available";
            else
                avail = "is not available";
            System.out.println(albums[i]+"::"+avail);
        }
        System.out.println("*End of list");
    } //display the list without specifying the order
    public void printByReleaseDate() {
       /*Alums[] copy = new Album[albums.length];
       for(int i = 0; i < copy.length ; i++){
          copy[i] = albums[i];
        }*/ // if we care about order after printing
        int n = albums.length;
        for(int i = 0; i < n - 1; i++){
            for(int j = 1; j < n - i - 1; j++){
                if(albums[j] == null){
                    Album temp = albums[j];
                    albums[j] = albums[j+1];
                    albums[j+1] = temp; //swap albums[j] and albums[j+1]
                }
                if(albums[j].getReleaseDate().compareTo(albums[j+1].getReleaseDate()) == -1){
                    //looking for albums[j] > albums[j+1]
                    //             date(j+1) > this (j) ==> return 1
                    //        date(j+1) < this (j) ==> return -1
                    Album temp = albums[j];
                    albums[j] = albums[j+1];
                    albums[j+1] = temp; //swap albums[j] and albums[j+1]
                }
            }
        }
        print();
    }

    private boolean sortGenre(Genre first, Genre second){//return true if first > second
        switch(first){
            case CLASSICAL:
                if(second == Genre.COUNTRY || second == Genre.JAZZ || second == Genre.POP || second == Genre.UNKNOWN){
                    return false;
                } else {
                    return true;
                }
            case COUNTRY:
                if(second == Genre.CLASSICAL){
                    return true;
                } else {
                    return false;
                }
            case JAZZ:
                if(second == Genre.CLASSICAL || second == Genre.COUNTRY){
                    return true;
                } else {
                    return false;
                }
            case POP:
                if(second == Genre.CLASSICAL || second == Genre.COUNTRY || second == Genre.JAZZ){
                    return true;
                } else {
                    return false;
                }
            case UNKNOWN:
                if(second == Genre.CLASSICAL || second == Genre.COUNTRY || second == Genre.JAZZ || second == Genre.POP){
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    public void printByGenre() {
        int n = albums.length;
        for(int i = 0; i < n - 1; i++){
            for(int j = 1; j < n - i - 1; j++){
                if(albums[j] == null){
                    Album temp = albums[j];
                    albums[j] = albums[j+1];
                    albums[j+1] = temp; //swap albums[j] and albums[j+1]
                }
                if(sortGenre(albums[j].getGenre(), albums[j+1].getGenre())){
                    //using private method above compares genres
                    Album temp = albums[j];
                    albums[j] = albums[j+1];
                    albums[j+1] = temp; //swap albums[j] and albums[j+1]
                }
            }
        }
        print();
    }
}
