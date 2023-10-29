package CitationPackage;

import CitationPackage.Citation;

import java.io.*;
import java.util.*;

public class CitationList {
    private ArrayList<Citation> listOfCitations;
    private String title, authority;

    //default constructor
    public CitationList(){
        this.listOfCitations = new ArrayList<Citation>();
        this.title = "Chief";
        this.authority = "Barrett";
    }
    //overloaded constructor
    public CitationList( String title, String authority){
        this.listOfCitations = new ArrayList<Citation>();
        this.title = title;
        this.authority = authority;
    }
    // set of getters and setters
    public ArrayList<Citation> getListOfCitations(){
        return this.listOfCitations;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthority(String authority){
        this.authority = authority;
    }
    public String getTitle(){
        return this.title;
    }
    public String getAuthority(){
        return this.authority;
    }

    // reading in from file
    public void readCitationFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    int number = Integer.parseInt(parts[0]);
                    String typeOfOffense = parts[1];
                    String description = parts[2];
                    String date = parts[3];
                    String first = parts[4];
                    String last = parts[5];
                    String address = parts[6];
                    String phone = parts[7  ];
                    int id = Integer.parseInt(parts[8]);
                    Person person = new Person(first, last, address, phone);
                    Citation citation = new Citation(number, typeOfOffense, description, date, person,id);
                    listOfCitations.add(citation);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
    }
    // write out to files
    public void writeCitationFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Citation citation : listOfCitations) {
                writer.println(citation.toCSV());
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filename);
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();// use the sb to wrap around the big string
        sb.append("CitationPackage.Citation List\n");
        sb.append("\n");
        sb.append(this.getTitle()).append(" ").append(this.getAuthority()).append("\n");
        sb.append("\n");
        for(Citation c : listOfCitations){
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }

//    public void displayCitationType(String typeOfOffense) {
//        for (CitationPackage.Citation citation : listOfCitations) {
//            if (citation.getTypeOfOffense().equalsIgnoreCase(typeOfOffense)) {
//                System.out.println(citation.toString());
//            }
//        }
//    }
    public String displayCitationType(String typeOfOffense) {
      StringBuilder result = new StringBuilder();
      for (Citation citation : listOfCitations) {
        if (citation.getTypeOfOffense().equalsIgnoreCase(typeOfOffense)) {
            result.append(citation.toString()).append("\n");
            }
        }
       return result.length() > 0 ? result.toString() : "None found";
    }

//    public void displayCitation(int number) {
//        for (CitationPackage.Citation citation : listOfCitations) {
//            if (citation.getNumber() == number) {
//                System.out.println(citation.toString());
//                return;//found it and break
//            }
//        }
//        System.out.println("CitationPackage.Citation with number " + number + " not found.");
//    }
    public String displayCitation(String lastName) {
        for (Citation citation : listOfCitations) {
            if (citation.getPerson().getLastName().equalsIgnoreCase(lastName)) {
                return citation.toString();//found it and break
            }
        }
        return "None found";
    }
    public String displayCitation(int number) {
        for (Citation citation : listOfCitations) {
            if (citation.getNumber() == number) {
                return citation.toString();//found it and break
            }
        }
        return "None found";
    }
//    public void newCitation(){
//        System.out.println("Enter you citation here:");
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Type of Offense: ");
//        String typeOfOffense = scanner.nextLine();
//        System.out.print("Description: ");
//        String description = scanner.nextLine();
//        System.out.print("Date: ");
//        String date = scanner.nextLine();
//        CitationPackage.Person person = new CitationPackage.Person();
//        System.out.print("First Name: ");
//        person.setFirstName(scanner.nextLine());
//        System.out.print("Last Name: ");
//        person.setLastName(scanner.nextLine());
//        System.out.print("Address: ");
//        person.setAddress(scanner.nextLine());
//        System.out.print("Phone Number: ");
//        person.setPhoneNumber(scanner.nextLine());
//        CitationPackage.Citation newCitation = new CitationPackage.Citation(this.citationCounter(), typeOfOffense, description, date, person);
//        listOfCitations.add(newCitation);
//        System.out.println("Done!");
//    }

    // a private helper function to determine the number of citations on the list right now and plus 1 to initialize it
    public int citationCounter(){
        return this.listOfCitations.size() + 1;
    }

    public String delete(int number) {

        for(Citation c : listOfCitations) {
            if (c.getNumber() == number) {
                listOfCitations.remove(c);
                return "Deleted";
            }
        }
        return "None found";
    }
    public void sortByNumber() {
        Collections.sort(listOfCitations);
    }
    public void sortByName() {
        Collections.sort(listOfCitations, new Comparator<Citation>() {
            @Override
            public int compare(Citation c1, Citation c2) {
                int lastComp = c1.getPerson().getLastName().compareToIgnoreCase(c2.getPerson().getLastName());
                if (lastComp != 0) return lastComp;
                return c1.getPerson().getFirstName().compareToIgnoreCase(c2.getPerson().getFirstName());
            }
        });
    }
    public void sortByType() {
        Collections.sort(listOfCitations, new TypeSorter());
    }
    public String findByUser(int userID) {
        StringBuilder result = new StringBuilder();
        for (Citation citation : listOfCitations) {
            if (citation.getUserID() == userID) {
                result.append(citation.toString()).append("\n");
            }
        }
        return result.length() > 0 ? result.toString() : "None found";
    }
    public void add(Citation c){
        listOfCitations.add(c);
    }

}
