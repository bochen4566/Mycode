import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Lab17 {

    public static ArrayList<Customer> readCustomers(String filename)
            throws IOException, NumberFormatException {

        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filename);
        }
        Scanner fileScanner = new Scanner(file);
        ArrayList<Customer> list = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(","); // Assuming the delimiter is a comma

            double balance = Double.parseDouble(parts[3]);
            Customer customer = new Customer(parts[0], parts[1], Integer.parseInt(parts[2]), balance);
            list.add(customer);
        }
        fileScanner.close();
        return list;
    }

//    public static ArrayList<Customer> readCustomers(String filename)
//            throws IOException {
//
//        // Array list of Customer objects
//        ArrayList<Customer> list = new ArrayList<>();
//        // Create a new File object
//
//        // If the file does not exist, throw a FileNotFound exception
//
//        // Create a new Scanner on the file object
//
//        // While fileScanner has a next line
//            // Read the next line and split it
//            // Convert the rating to an int; throw NumberFormatException if bad
//            // Convert the balance to a double; throw NumberFormatException if bad
//            // Create a new customer object, add it to list
//        return list;
//    }

    public static void main(String[] args) {
        ArrayList<Customer> clist = null;
        // Problem 3
        // Call readCustomers with the data file as a parameter
        try {
            clist = readCustomers("customers.csv");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (IOException e) {
            e.printStackTrace(); // print the stack trace for any other IO exceptions
            return;
        }
        // Print the array list
        System.out.println("Original list");
        for (Customer c : clist) {
            System.out.println(c);
        }
        // Problem #4
        // Create PriorityQueue queue1
        PriorityQueue<Customer> queue1 = new PriorityQueue<>();
        try {
            queue1.addAll(clist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Problem #5
        // Create PriorityQueue queue2
        PriorityQueue<Customer> queue2 = new PriorityQueue<>(Comparator.comparingDouble(Customer::getBalance));
        try {
            queue2.addAll(clist);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Problem #6
        // Remove things one at a time from queue1 and print them
        System.out.println("Queue1 processing");
        try {
            while (true) {
                Customer customer = queue1.element();
                System.out.println(customer);
                queue1.remove();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Done");
        }

        // Problem #7
        // Remove things one at a time from queue2 and print them
        System.out.println("Queue2 processing");
        try {
            while (true) {
                Customer customer = queue2.element();
                System.out.println(customer);
                queue2.remove();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Done");
        }

        // Problem #8
        // Try this on your own

        // Problem #9
        problem9();
    }

    public static void problem9() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("testdata.txt"));
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: testdata.txt");
            return;
        }

        ArrayList<String> patterns = new ArrayList<>(Arrays.asList(
                ".*\\d.*", // lines containing any digit
                ".*[a-zA-Z].*", // lines containing any letter, either case
                "^-?\\d+$", // lines containing an integer
                "^a.*", // any line that begins with the letter "a"
                ".*s$", // any line that ends with the letter "s"
                ".*\\(.*", // any line that contains a left parenthesis
                ".*[ae].*[ae].*", // lines that contain an "a" and an "e" in either order
                ".*a.*e.*i.*o.*u.*"// lines containing the lower case vowels in order
                // ... add more patterns as required
        ));

        for (String pattern : patterns) {
            System.out.println("Pattern: " + pattern);
            for (String line : lines) {
                if (line.matches(pattern)) {
                    System.out.println("Matched: " + line);
                }
            }
        }
    }

}
