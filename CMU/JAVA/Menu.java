import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Menu {
    // the menu choices
    private static final String[] menuChoice = {"0. Quit.",
            "1. Display all the Citation data using CitationList.toString()",
            "2. Display all Citations by chosen typeOfOffense.",
            "3. Search for a Citation by number.",
            "4. Search for a Citation by Person last name.",
            "5. Add a new Citation."};

    // display and return the value
    public static int displayMenu() {
        System.out.println("Please select your option:");
        for (String s : menuChoice) {
            System.out.println(s);
        }

        Scanner scanner = new Scanner(System.in);
        try {

            int input = scanner.nextInt();
            if (input > -1 && input < 6) {
                return input;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
        return 0;

    }
}
