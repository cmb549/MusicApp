package albums;

/**
 This is the container class that defines the array list data structure to hold the album collection and provide
 the operations to manage the collection.
 Each album includes a title, artist, genre, release date, and a flag identifying the availability.
 @author Ria Shah, Christian Bermudez
 */
public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection
    //...
    public Collection() {
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
        albums[index] = null;
        return true;
    }

    public boolean lendingOut(Album album) {
        int index = find(album);
        if(index == -1) {
            return false;
        }
        return albums[index].rentOut();

    } //set to not available

    public boolean returnAlbum(Album album) {
        int index = find(album);
        if(index == -1) {
            return false;
        }
        return albums[index].returnAlbum();
    } //set to available

    public void print() {
        for(int i = 0; i < albums.length; i++){
            if(albums[i] == null) //if position is null then skip
                continue;
            String avail;
            if (albums[i].getIsAvailable())
                avail = "is available";
            else
                avail = "is not available";
            System.out.println(albums[i] + "::" + avail);
        }
    } //display the list without specifying the order

    public void printByReleaseDate() {
        int n = albums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((albums[j] == null)) {
                    continue;
                }
                if ((albums[i] == null)) {
                    break;
                }
                if (albums[i].getReleaseDate().compareTo(albums[j].getReleaseDate()) < 0) {
                    Album temp = albums[i];
                    albums[i] = albums[j];
                    albums[j] = temp; //swap albums[j] and albums[j+1]
                }
            }
        }
        print();
    }

    private boolean sortGenre(Genre first, Genre second) {//return true if first > second
        switch(first) {
            case Classical:
                if (second == Genre.Country || second == Genre.Jazz || second == Genre.Pop || second == Genre.Unknown) {
                    return false;
                } else {
                    return true;
                }
            case Country:
                if (second == Genre.Classical) {
                    return true;
                } else {
                    return false;
                }
            case Jazz:
                if (second == Genre.Classical || second == Genre.Country) {
                    return true;
                } else {
                    return false;
                }
            case Pop:
                if (second == Genre.Classical || second == Genre.Country || second == Genre.Jazz) {
                    return true;
                } else {
                    return false;
                }
            case Unknown:
                if (second == Genre.Classical || second == Genre.Country || second == Genre.Jazz || second == Genre.Pop) {
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
        for (int i = 0; i < n; i++) {
            if ((albums[i] == null)) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if ((albums[j] == null)) {
                    continue;
                }
                if (albums[i].getGenre().compareTo(albums[j].getGenre()) > 0) {
                    Album temp = albums[i];
                    albums[i] = albums[j];
                    albums[j] = temp; //swap albums[j] and albums[j+1]
                }
            }
        }
        print();
    }
}