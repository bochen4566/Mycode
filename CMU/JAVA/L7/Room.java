public class Room {
    private double length;
    private double width;
    private String name;
    private int id;

    private SensorCollection SC;
    private AlarmCollection AC;
    private Device D;


    public Room(double length, double width, String name, int id) {
        this.length = length;
        this.width = width;
        this.name = name;
        this.id = id;
        this.SC = new SensorCollection();
        this.AC = new AlarmCollection();
    }

    public void addSensor(Sensor s){
        this.SC.add(s);
    }
    public void addAlarm(Alarm a){
        this.AC.add(a);
    }
    public void addDevice(Device d){
        this.D = new Device(d);
    }

    public void display(){
        System.out.println(this.toString());
        System.out.println(this.D.toString());
        this.SC.display();
        this.AC.display();
    }
    public double getArea() {
        return length * width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Room{" +
                "length=" + length +
                ", width=" + width +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
