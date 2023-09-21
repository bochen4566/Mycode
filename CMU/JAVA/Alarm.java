public class Alarm {
    private String message;
    private int id;


    public Alarm(String message, int id) {
        this.message = message;
        this.id = id;
    }


    public void soundTheAlarm() {
        System.out.println("Emergency Message: " + message);
        simulate911Call();
    }

    private void simulate911Call() {
        System.out.println("Simulating a 911 call...");
        // You can add code here to perform further actions for a 911 call if needed.
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Alarm{" +
                "message='" + message + '\'' +
                ", id=" + id +
                '}';
    }

}

