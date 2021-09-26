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
            System.out.println(album.toString() + " >> is already in the collection.");
            return false;
        }
        for(int i = 0; i < albums.length; i++){
            if(albums[i] == null){//empty position
                albums[i] = album;//save at that position
                System.out.println(albums[i].toString() + " >> added.");
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
        if(index == -1){
            return false;
        }
        return albums[index].rentOut();
    } //set to not available
    public boolean returnAlbum(Album album) {
        int index = find(album);
        if(index == -1){
            return false;
        }
        return albums[index].returnAlbum();
    } //set to available
    public void print() {
        System.out.println("*Start of Album List*");
        for(int i = 0; i < albums.length; i++){
            if(albums[i] == null){//if position is null then skip
                continue;
            }
            System.out.println(albums[i]);
        }
        System.out.println("*End of Album List*");
    } //display the list without specifying the order
    public void printByReleaseDate() {
       /*Alums[] copy = new Album[albums.length];
       for(int i = 0; i < copy.length ; i++){
          copy[i] = albums[i];
        }*/ // if we care about order after printing
        int n = albums.length;
        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
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
            case Classical:
                if(second == Genre.Country || second == Genre.Jazz || second == Genre.Pop || second == Genre.Unknown){
                    return false;
                } else {
                    return true;
                }
            case Country:
                if(second == Genre.Classical){
                    return true;
                } else {
                    return false;
                }
            case Jazz:
                if(second == Genre.Classical || second == Genre.Country){
                    return true;
                } else {
                    return false;
                }
            case Pop:
                if(second == Genre.Classical || second == Genre.Country || second == Genre.Jazz){
                    return true;
                } else {
                    return false;
                }
            case Unknown:
                if(second == Genre.Classical || second == Genre.Country || second == Genre.Jazz || second == Genre.Pop){
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
            for(int j = 0; j < n - i - 1; j++){
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