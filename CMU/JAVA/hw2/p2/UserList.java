package UserPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    private ArrayList<User> listOfUsers = new ArrayList<>();

    public void readUserFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String userType = parts[1].trim();
                    String userName = parts[2].trim();
                    String other = parts[3].trim();
                    User user = UserFactory.createUser(id, userType, userName, other);
                    listOfUsers.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : listOfUsers) {
            stringBuilder.append(user).append("\n");
        }
        return stringBuilder.toString();
    }

    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }
}
