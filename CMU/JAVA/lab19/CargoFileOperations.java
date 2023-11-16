import java.io.*;
import java.util.ArrayList;
import java.nio.file.*;

public class CargoFileOperations {
    private String filename;

    public CargoFileOperations(String filename) {
        this.filename = filename;
    }

    public void writeList(ArrayList<Cargo> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Cargo cargo : list) {
                oos.writeObject(cargo);
            }
        } catch (IOException e) {
            System.out.println("Output file failed to open: " + e.getMessage());
            System.exit(1);
        }
    }

    public ArrayList<Cargo> readList() {
        ArrayList<Cargo> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (ois.available() > 0) {
                Cargo cargo = (Cargo) ois.readObject();
                list.add(cargo);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
        return list;
    }
    public void display() {
        Path path = Paths.get(filename);
        System.out.println("Path: " + path.toString());
        System.out.println("Absolute Path: " + path.toAbsolutePath());
        System.out.println("Root: " + path.getRoot());

        File file = new File(filename);
        System.out.println("isDirectory: " + file.isDirectory());
        System.out.println("getAbsolutePath: " + file.getAbsolutePath());

        System.out.println("isExecutable: " + Files.isExecutable(path));
        System.out.println("isReadable: " + Files.isReadable(path));
        System.out.println("isWritable: " + Files.isWritable(path));
    }
}
