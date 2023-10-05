import java.util.ArrayList;
import java.util.Scanner;
import NotePackage.*;


public class MakeANote {

    // Keyboard scanner for user input
    private static Scanner keyboard = new Scanner(System.in);

    // String values for the main menu - title first
    private static final String[] mainMenu = {"Main Menu", "Create a new Note",
            "Display existing Note(s)", "Quit"};

    // String  values for the create sub-menu - title first
    private static final String[] createMenu = {"Note Creation", "Create a Memo",
            "Create a Timed Memo", "Create a Polite Memo", "Return to previous menu"};

    // String values for the display sub-menu - title first
    private static final String[] displayMenu = {"Display Options", "Display all Notes",
            "Display Note by Number", "Display Notes by Name","Sort by Name", "Sort by Number","Return to previous menu"};

    // getMenuChoice(String[] menu)
    //      Displays menu[]
    //      Prompts the user for a choice
    //      Returns choice without error checking
    private static int getMenuChoice(String[] menu) {
        int choice;
        System.out.println(menu[0]);
        // Display the menu, whatever its size is
        for (int i = 1; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
        System.out.print("Enter your choice: ");
        choice = Integer.parseInt(keyboard.nextLine());
        return choice;
    }
    private static void displayAllNotes(NoteCollection noteCollection) {
        System.out.println("\n--- All Notes ---");
        if (noteCollection.isEmpty()) {
            System.out.println("No notes found.");
        } else {
            int noteNumber = 1;
            for (Note note : noteCollection.getAllNotes()) {
                System.out.println("Note " + noteNumber + ":\n" + note.toString());
                noteNumber++;
            }
        }
    }
    private static void sortByName(NoteCollection noteCollection) {
        noteCollection.sortByName();
    }
    private static void sortByNumber(NoteCollection noteCollection) {
        noteCollection.sortByNumber();
    }
    private static void sortBySize(NoteCollection noteCollection) {
        noteCollection.sortBySize();
    }

    // Display a note by number
    private static void displayNoteByNumber(NoteCollection noteCollection) {
        keyboard.nextLine(); // Clearing the newline character
        System.out.print("Enter the note number: ");
        int noteNumber = keyboard.nextInt();
        keyboard.nextLine(); // Clearing the newline character

        Note note = noteCollection.getNoteByNumber(noteNumber);
        if (note != null) {
            System.out.println("\n--- Note " + noteNumber + " ---\n" + note.toString());
        } else {
            System.out.println("Note not found. Please enter a valid note number.");
        }
    }

    // Display notes by name
    private static void displayNotesByName(NoteCollection noteCollection) {
        keyboard.nextLine(); // Clearing the newline character
        System.out.print("Enter the note name: ");
        String noteName = keyboard.nextLine();

        ArrayList<Note> matchingNotes = noteCollection.getNoteByName(noteName);
        if (matchingNotes.isEmpty()) {
            System.out.println("No notes found with the given name.");
        } else {
            System.out.println("\n--- Notes with name \"" + noteName + "\" ---");
            int noteNumber = 1;
            for (Note note : matchingNotes) {
                System.out.println("Note " + noteNumber + ":\n" + note.toString());
                noteNumber++;
            }
        }
    }
    // main()
    //      Display the main menu, get a choice
    //      Create: get information for a new Memo
    //      Submenus: tbd
    public static void main(String[] args) {

        // Create an empty NoteCollection
        NoteCollection noteCollection = new NoteCollection();
        // Menu choice
        int choice;
        // Memo values
        String name = null, body = null, from = null, to = null;
        // Note object to be new-ed up below
        Note note = null;
        // Submenu choic
        int subchoice;

        // Loop until the user chooses quit
        do {

            // Get the main menu choice
            choice = getMenuChoice(mainMenu);

            // What did they choose?
            switch (choice) {

                // Create a new Note
                case 1:
                    
                    // Loop until the user chooses quit
                    do {

                        // Get the create submenu choice
                        subchoice = getMenuChoice(createMenu);

                        // For now, create only a Memo
                        if (subchoice == 1 || subchoice == 2 || subchoice == 3) {
                            System.out.print("Enter memo name: ");
                            name = keyboard.nextLine();
                            System.out.print("Enter memo body: ");
                            body = keyboard.nextLine();
                            System.out.print("Enter who this is from: ");
                            from = keyboard.nextLine();
                            System.out.print("Enter who this is to: ");
                            to = keyboard.nextLine();
                        }
                        switch (subchoice) {
                            // Create a Memo
                            case 1:
                                Memo memo = new Memo(name, body, from, to);
                                // Add the Memo object to the NoteCollection
                                noteCollection.add(memo);
                                System.out.println("Memo created and added to NoteCollection.");
                                break;



                            // Create a TimedMemo
                            // TBD in part 2
                            case 2:
                                public static void createTimedMemo(NoteCollection noteCollection) {
                                    keyboard.nextLine(); // Clearing the newline character
                                    System.out.print("Enter Memo name: ");
                                    name = keyboard.nextLine();
                                    System.out.print("Enter Memo body: ");
                                    body = keyboard.nextLine();
                                    System.out.print("Enter who this is from: ");
                                    from = keyboard.nextLine();
                                    System.out.print("Enter who this is to: ");
                                    to = keyboard.nextLine();
                                    note = new TimedMemo(name, body, from, to);
                                    noteCollection.add(note);
                                    System.out.println("\nTimed Memo created successfully:\n" + note.toString());
                            }
                            // Create a PoliteTimedMemo
                            // TBD in part 2
                            case 3:
                                public static void createPoliteMemo(NoteCollection noteCollection) {
                                    keyboard.nextLine(); // Clearing the newline character
                                    System.out.print("Enter Memo name: ");
                                    name = keyboard.nextLine();
                                    System.out.print("Enter Memo body: ");
                                    body = keyboard.nextLine();
                                    System.out.print("Enter who this is from: ");
                                    from = keyboard.nextLine();
                                    System.out.print("Enter who this is to: ");
                                    to = keyboard.nextLine();
                                    note = new PoliteTimedMemo(name, body, from, to);
                                    noteCollection.add(note);
                                    System.out.println("\nPolite Memo created successfully:\n" + note.toString());
                            }

                            // Quit
                            case 4:
                                break;
                        }
                    } while (subchoice != 4);

                // Display Notes
                case 2:

                    // Loop until the user chooses quit
                    do {

                        // Get the display submenu choice
                        subchoice = getMenuChoice(displayMenu);
                        switch (subchoice) {

                            // Display all notes
                            case 1:
                                displayAllNotes(noteCollection);

                            // Display a note by number
                            case 2:
                                displayNoteByNumber(noteCollection);
                                // TBD in part 2
                                break;

                            // Display a note by name
                            case 3:
                                displayNotesByName(noteCollection);
                                // TBD in part 2
                                break;
                            case 4:
                                sortByName(noteCollection);
                                System.out.println("Notes sorted by name. Choose display all notes to see the sorted list.");
                                break;
                            case 5:
                                sortByNumber(noteCollection);
                                break;
                            case 6:
                                sortBySize(noteCollection);
                                break;
                            case 7:
                                System.out.println("Returning to main menu");
                                break;
                        }
                    } while (subchoice != 4);
            }

        } while (choice != 3);
    }
}
