import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class hw1Main {
    public static Scanner scanner = new Scanner(System.in);// the scanner class for all


    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        CitationList citationList = new CitationList();
        citationList.readCitationFile("citations.csv");
        int in = Menu.displayMenu();//displays the menu option
        // make the option happen as the program goes
        if(in == 0){
            System.exit(0);
        }else if(in == 1){
            System.out.println(citationList.toString());
        } else if (in == 2) {
            System.out.println("Please enter the type: (We only have Parking\", \"Speeding\", \"Jaywalking\", and \"Inebriated\")");
            String type = scanner.nextLine();
            citationList.displayCitationType(type);
        } else if (in == 3) {
            System.out.println("Please enter the number: ");
            int num = scanner.nextInt();
            citationList.displayCitation(num);
        } else if (in == 4) {
            System.out.println("Please enter the last name: ");
            String lname = scanner.nextLine();
            citationList.displayCitation(lname);
        }else {
            citationList.newCitation();
        }


    }
}