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
        //if (genre.   contains(albumGenre.toLowerCase())) {
        //    match = true;
        Album myAlbum = new Album();
        if (command.equals("A")) {
            Date date = new Date(releaseDate);
            Genre genre;
            if (date.isValid()) {
                //if ((albumGenre.equals(Genre.Classical)) || (albumGenre.equals(Genre.Country)) || (albumGenre.equals(Genre.Jazz)) || (albumGenre.equals(Genre.Pop))) {
                if (albumGenre.equalsIgnoreCase(String.valueOf(Genre.Classical)))
                    genre = Genre.Classical;
                else if (albumGenre.equalsIgnoreCase(String.valueOf(Genre.Country)))
                    genre = Genre.Country;
                else if (albumGenre.equalsIgnoreCase(String.valueOf(Genre.Jazz)))
                    genre = Genre.Jazz;
                else if (albumGenre.equalsIgnoreCase(String.valueOf(Genre.Pop)))
                    genre = Genre.Pop;
                    //genre = Genre.valueOf(albumGenre);
                //}
                else
                    genre = Genre.Unknown;
                myAlbum.setTitle(title);
                myAlbum.setArtist(artist);
                myAlbum.setGenre(genre);
                myAlbum.setReleaseDate(date);
                myAlbum.setIsAvailable(true);
                boolean returnVal = myCollection.add(myAlbum);
                if (returnVal)
                    System.out.println(myAlbum.toString() + "::is available >> added.");
                else
                    System.out.println(myAlbum.toString() + "::is available >> is already in the collection.");
            }
            else
                System.out.println("Invalid Date!");
        }
        else if (command.equals("D")) {
            myAlbum.setTitle(title);
            myAlbum.setArtist(artist);
            boolean returnVal = myCollection.remove(myAlbum);
            if (returnVal)
                System.out.println(myAlbum.toString() + " >> deleted.");
            else
                System.out.println(myAlbum.toString() + " >> is not in the collection.");
        }
        else if (command.equals("L")) {
            myAlbum.setTitle(title);
            myAlbum.setArtist(artist);
            //if (myAlbum.getIsAvailable())
            boolean returnVal = myCollection.lendingOut(myAlbum);
            if (returnVal)
                System.out.println(myAlbum.toString() + " >> lending out and set to not available.");
            else
                System.out.println(myAlbum.toString() + " >> is not available");
        }
        else if (command.equals("R")) {
            myAlbum.setTitle(title);
            myAlbum.setArtist(artist);
            //if (!myAlbum.getIsAvailable())
            boolean returnVal = myCollection.returnAlbum(myAlbum);
            if (returnVal)
                System.out.println(myAlbum.toString() + " >> returning and set to available.");
            else
                System.out.println(myAlbum.toString() + " >> return cannot be completed.");
            //myAlbum.toString();
        }
        else if (command.equals("P")) {
            System.out.println("*List of albums in the collection.");
            myCollection.print();
            System.out.println("*End of list");
        }
        else if (command.equals("PD")) {
            System.out.println("*Album collection by the release dates.");
            myCollection.printByReleaseDate();
            System.out.println("*End of list");
        }
        else if (command.equals("PG")) {
            System.out.println("*Album collection by genre.");
            myCollection.printByGenre();
            System.out.println("*End of list");
        }
    }
}