import java.util.Scanner;
import java.util.ArrayList;

public class Lab19Main {
    public static Scanner scanner = new Scanner(System.in);

    // Factory method pattern using CargoEnum
    public static Cargo createCargo(CargoEnum c) {
        Cargo cargo = null;
        switch (c) {
            case SMALL: cargo = new SmallCargo(c.getLength(), c.getWidth()); break;
            case MEDIUM: cargo = new MediumCargo(c.getLength(), c.getWidth()); break;
            case LARGE: cargo = new LargeCargo(c.getLength(), c.getWidth()); break;
        }
        return cargo;
    }

    public static void main(String[] args) {
        // Lots of cargo containers
        CargoContainer cargoContainer = new CargoContainer();

        // Show the available cargo sizes
        System.out.println("Parcel sizes:");
        for (CargoEnum cargoEnum: CargoEnum.values()) {
            System.out.print(cargoEnum + " ");
        }
        System.out.println();

        // While the user wants to create or delete cargo
        while (true) {
            System.out.println("1. Add\n2. Remove\n0. Quit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice  == 0 ) break;
            if (choice == 1) {
                System.out.print("Enter the size: ");
                String s = scanner.nextLine();
                CargoEnum cargoEnum = CargoEnum.valueOf(s.toUpperCase());
                cargoContainer.add(createCargo(cargoEnum));
            } else if (choice == 2) {
                System.out.print("Enter the size: ");
                String s = scanner.nextLine();
                CargoEnum cargoEnum = CargoEnum.valueOf(s.toUpperCase());
                Cargo c = cargoContainer.remove(cargoEnum.toString());
            } else {
                break;
            }
        }
        // Comment this out to do part 4
        cargoContainer.display();
        // Add the required code for part 4 here
        CargoFileOperations fileOps = new CargoFileOperations("cargo_data.dat");
        fileOps.writeList(cargoContainer.getCargoList());
        ArrayList<Cargo> readCargo = fileOps.readList();

        // Display the read cargo
        for (Cargo cargo : readCargo) {
            System.out.println(cargo);
        }


    }
}
