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
            while (st.hasMoreTokens()) {
                if (count == 0) {
                    command = st.nextToken();
                    if (!isCommandValid(command)) {
                        System.out.println("Invalid command!");
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
        Album myAlbum = new Album();
        Date myDate = null;
        if (command.equals("A") || command.equals("D")  || command.equals("L")  || command.equals("R")) {
            if (command.equals("A"))
                if (releaseDate != null)
                    myDate = new Date(releaseDate);
            Genre myGenre = Genre.valueOf(albumGenre);
            myAlbum = new Album(title, artist, myGenre, myDate);
        }
        if (command.equals("A")) {
            myDate = new Date(releaseDate);
            Genre myGenre = Genre.valueOf(albumGenre);
            myAlbum = new Album(title, artist, myGenre, myDate);
            myCollection.add(myAlbum);
        }
        else if (command.equals("D"))
            myCollection.remove(myAlbum);
        else if (command.equals("L"))
            myCollection.lendingOut(myAlbum);
        else if (command.equals("R"))
            myCollection.returnAlbum(myAlbum);
        else if (command.equals("P"))
            myCollection.print();
        else if (command.equals("PD"))
            myCollection.printByReleaseDate();
        else if (command.equals("PG"))
            myCollection.printByGenre();
    }
}