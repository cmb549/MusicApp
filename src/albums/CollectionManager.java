package albums;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CollectionManager {
    Collection myCollection = new Collection();
    public void run() {
        System.out.println("Collection Manager starts running.");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(input, ",");
            int count = 0;
            String command = "", title = "", artist = "", albumGenre = "", releaseDate = "";
            boolean commandValid = true;
            while (st.hasMoreTokens()) {
                if (count == 0) {
                    command = st.nextToken();
                    if (!isCommandValid(command)) {
                        System.out.println("Invalid command!");
                        commandValid = false;
                        break;
                    }
                    else if (command.equals("Q")) {
                        System.out.println("Collection Manager terminated.");
                        return;
                    }
                }
                if (count == 1)
                    title = st.nextToken();
                if (count == 2)
                    artist = st.nextToken();
                if (count == 3)
                    albumGenre = st.nextToken();
                if (count == 4)
                    releaseDate = st.nextToken();
                count++;
            }
            if (commandValid)
                processCommand(command, title, artist, albumGenre, releaseDate);
        }
    }

    private boolean isCommandValid (String command) {
        if (command.equals("A") || command.equals("D")  || command.equals("L")  || command.equals("R")  || command.equals("P")  || command.equals("PD")  || command.equals("PG")  || command.equals("Q"))
            return true;
        else
            return false;
    }

    private void processCommand(String command, String title, String artist, String albumGenre, String releaseDate) {
        Genre genre = Genre.valueOf(albumGenre);
        //if (genre.   contains(albumGenre.toLowerCase())) {
        //    match = true;
        Album myAlbum = new Album();
        if (command.equals("A")) {
            Date date = new Date(releaseDate);
            myAlbum.setTitle(title);
            myAlbum.setArtist(artist);
            myAlbum.setGenre(genre);
            myAlbum.setReleaseDate(date);
            myAlbum.setIsAvailable(true);
            myCollection.add(myAlbum);
        }
        if (command.equals("D")) {
            myAlbum.setTitle(title);
            myAlbum.setArtist(artist);
            myCollection.remove(myAlbum);
        }
        if (command.equals("L")) {
            myAlbum.setTitle(title);
            myAlbum.setArtist(artist);
            if (myAlbum.getIsAvailable())
                myCollection.lendingOut(myAlbum);
            myAlbum.toString();
        }
        if (command.equals("R")) {
            myAlbum.setTitle(title);
            myAlbum.setArtist(artist);
            if (!myAlbum.getIsAvailable())
                myCollection.returnAlbum(myAlbum);
            myAlbum.toString();
        }
        if (command.equals("P"))
            myCollection.print();
        if (command.equals("PD"))
            myCollection.printByReleaseDate();
        if (command.equals("PG"))
            myCollection.printByGenre();
        }
}