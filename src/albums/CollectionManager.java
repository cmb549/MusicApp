package albums;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CollectionManager {
    public void run() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            StringTokenizer st = new StringTokenizer(input, ",");
            int count = 0;
            String command = "", title = "", artist = "", genre = "", releaseDate = "";
            while (st.hasMoreTokens()) {
                if (count == 0) {
                    command = st.nextToken();
                    if (!isCommandValid(command)) {
                        System.out.println("Invalid command!");
                        break;
                    }
                }
                if (count == 1)
                    title = st.nextToken();
                if (count == 2)
                    artist = st.nextToken();
                if (count == 3)
                    genre = st.nextToken();
                if (count == 4)
                    releaseDate = st.nextToken();
                count++;
            }
        }
    }

    public boolean isCommandValid (String command) {
        if (command.equals("A") || command.equals("D")  || command.equals("L")  || command.equals("R")  || command.equals("P")  || command.equals("PD")  || command.equals("PG")  || command.equals("Q"))
            return true;
        else
            return false;
    }
}
