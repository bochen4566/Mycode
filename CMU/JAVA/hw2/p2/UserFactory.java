package UserPackage;

public class UserFactory {
    public static User createUser(int id, String userType, String name, String other) {
        switch (userType) {
            case "Administrator":
                return new Administrator(id, name, other);
            case "Officer":
                return new Officer(id, name, other);
            case "CourtOfficial":
                return new CourtOfficial(id, name, other);
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}
