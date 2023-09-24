public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    // Default constructor
    public Person() {
        // Initialize fields to empty strings
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.phoneNumber = "";
    }

    // Overloaded constructor
    public Person(String firstName, String lastName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //getter and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //toString method
    public String toString() {
        return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", address='" + address + '\'' + ", phoneNumber='" + phoneNumber + '\'' + '}';
    }

    public String toCSV() {
        // Comma-separated values
        return firstName + "," + lastName + "," + address + "," + phoneNumber;
    }
}

