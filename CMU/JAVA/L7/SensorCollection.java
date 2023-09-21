import java.util.ArrayList;

public class SensorCollection {
    private ArrayList<Sensor> sensors;

    public SensorCollection() {
        sensors = new ArrayList<>();
    }

    public void add(Sensor sensor) {
        sensors.add(sensor);
    }

    public void display() {
        for (Sensor sensor : sensors) {
            System.out.println(sensor.toString());
        }
    }
}
