package UserPackage;

public class Administrator extends User {
    private String department;

    public Administrator(int id, String userName, String department) {
        super(id, userName);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return super.toString() + ", Department: " + department;
    }
}

