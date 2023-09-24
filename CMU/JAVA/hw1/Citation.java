
import java.util.ArrayList;

public class Citation {
    private int number;
    private String typeOfOffense;
    private String description;
    private String date;
    private Person person;

    //constructors
    public Citation() {
        this.number = 0;
        this.typeOfOffense = "";
        this.description = "";
        this.date = "";
        this.person = new Person();
    }

    public Citation(int number, String typeOfOffense, String description, String date, Person person) {
        this.number = number;
        this.typeOfOffense = typeOfOffense;
        this.description = description;
        this.date = date;
        this.person = person;
    }

    //sets of getters and setters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTypeOfOffense() {
        return typeOfOffense;
    }

    public void setTypeOfOffense(String typeOfOffense) {
        this.typeOfOffense = typeOfOffense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

//    public String toString() {
//        return "Citation{" + "number=" + number + ", typeOfOffense='" + typeOfOffense + '\'' + ", description='" + description + '\'' + ", date='" + date + '\'' + ", person=" + person + '}';
//    }

    // updated toString method to get the format correctly as the homework expected
    public String toString() {


        return "Citation #" + number + "\n" +
                "Type Of Offense: " + typeOfOffense + "\n" +
                "Description: " + description + "\n" +
                "Date: " + date + "\n" +
                "Person: " + person.getFirstName() + " "+ person.getLastName() + " " + person.getAddress() + " " + person.getPhoneNumber() + "\n";
    }

    // a to csv method
    public String toCSV() {

        return number + "," + typeOfOffense + "," + description + "," + date + "," + person.toCSV();
    }
}

