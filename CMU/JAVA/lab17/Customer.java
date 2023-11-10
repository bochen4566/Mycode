public class Customer implements Comparable<Customer>{
    private String firstName, lastName;
    private int rating;
    private double balance;
    private RatingType ratingType;

    public Customer(String firstName, String lastName, int rating, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.balance = balance;
        // Set ratingType here:
        if (rating >= 0 && rating <= 3) {
            this.ratingType = RatingType.LOW;
        } else if (rating <= 7) {
            this.ratingType = RatingType.MEDIUM;
        } else if (rating <= 10) {
            this.ratingType = RatingType.HIGH;
        }

    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getRating() { return rating; }
    public double getBalance() { return balance; }
    // Your getter goes here:
    public String getRatingType() {
        return ratingType.toString();
    }

    @Override
    public String toString() {
        return "firstName= " + firstName + " "
                + "lastName= " + lastName + " "
                + "rating= " + rating + " "
                + "balance =" + balance
                + " ratingType= " + getRatingType();
                // Add ratingType here:
    }

    @Override
    public int compareTo(Customer other) {
        // Assume higher ratings should come first
        return Integer.compare(other.rating, this.rating);
    }
}
