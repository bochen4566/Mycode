//Name: Bochen Wang
//AndrewId: bochenw

import java.util.Scanner;

public class Lab5 {
    private static Scanner scanner = new Scanner(System.in);;

    public static void main(String[] args) {
        Room room1 = new Room(12.0, 15.0, "kitchen", 1);


        Sensor temperature = new Sensor(0.0, 120.0,
                68.0, 1.0, "kitchen",
                "temperature", 1);
        Device extinguisher = new Device("fire extinguisher", "kitchen", 1);
        Room kitchen = new Room(12.0, 15.0, "kitchen", 1);
        Alarm bell = new Alarm("Ding! Ding!", 1);
        System.out.println(temperature.toString());
        System.out.println(extinguisher.toString());
        System.out.println(kitchen.toString());
        System.out.println(bell.toString());

        System.out.println("Hello! Welcome to this room.");
        System.out.println(temperature.toString());
        System.out.println(extinguisher.toString());
        System.out.println(kitchen.toString());
        System.out.println(bell.toString());

        String in = "Y";
        while(in.equals("Y")){
            System.out.print("Do you want to change the temp?(enter Y or N) :");
            in = scanner.nextLine();
            if(in.equals("N")) break;
            else{
                System.out.print("Enter here:");
                String temp = scanner.nextLine();
                temperature.setCurrentValue(Double.parseDouble(temp));
                if(temperature.trip()){
                    extinguisher.actuate();
                    bell.soundTheAlarm();
                    temperature.setCurrentValue(68.0);
                }
            }
        }



        // Question 8:
        // They can use the room object to interact
        // The room should have the funciton of addDevice() addSensor() addAlarm() to get the work done
        // The can use display to interact with each other with the attributes of calling to realize
        //each other's instances.

//        room1.addDevice(extinguisher);
//        for (int i = 0; i < 5; i++) {
//            Sensor s = new Sensor(0.0, 120.0,
//                    68.0, 1.0, "kitchen",
//                    "temperature", i+1);
//            room1.addSensor(s);
//        }
//        for (int i = 0; i < 3; i++) {
//            Alarm a = new Alarm("Ding! Ding!", i+1);
//            room1.addAlarm(a);
//        }
//        room1.display();
//
//        Device chemicalFoamer = new Device("chemical foamer", "kitchen", 2);
//        room1.addDevice(chemicalFoamer);
//        room1.display();
    }
}
