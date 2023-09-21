// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

//Bochen Wang
//AndrewID: bochenw
public class Main {
    public static void main(String[] args) {

        Room room1 = new Room(12.0, 15.0, "kitchen", 1);
        Device extinguisher = new Device("fire extinguisher", "kitchen", 1);
        room1.addDevice(extinguisher);
        for (int i = 0; i < 5; i++) {
            Sensor s = new Sensor(0.0, 120.0,
                    68.0, 1.0, "kitchen",
                    "temperature", i+1);
            room1.addSensor(s);
        }
        for (int i = 0; i < 3; i++) {
            Alarm a = new Alarm("Ding! Ding!", i+1);
            room1.addAlarm(a);
        }
        room1.display();

        Device chemicalFoamer = new Device("chemical foamer", "kitchen", 2);
        room1.addDevice(chemicalFoamer);
        room1.display();
    }
}