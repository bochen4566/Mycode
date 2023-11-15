import java.io.*;
import java.util.Scanner;

public class Lab18Main {

    public static void main(String[] args) {
        String fileName = "test.txt";
        int smallTest = 10000;
        int largeTest = 1000000;

        // Small Test
        System.out.printf("printWriterTest: %15d\n", printWriterTest(fileName, smallTest));
        System.out.printf("bufferWriterTest: %15d\n", bufferWriterTest(fileName, smallTest));
        System.out.printf("fileWriterTest: %15d\n", fileWriterTest(fileName, smallTest));
        System.out.printf("scannerTest: %15d\n", scannerTest(fileName, smallTest));
        System.out.printf("bufferedReaderTest: %15d\n", bufferedReaderTest(fileName, smallTest));
        System.out.printf("fileReaderTest: %15d\n", fileReaderTest(fileName, smallTest));

        // Large Test
        System.out.printf("printWriterTest: %15d\n", printWriterTest(fileName, largeTest));
        System.out.printf("bufferWriterTest: %15d\n", bufferWriterTest(fileName, largeTest));
        System.out.printf("fileWriterTest: %15d\n", fileWriterTest(fileName, largeTest));
        System.out.printf("scannerTest: %15d\n", scannerTest(fileName, largeTest));
        System.out.printf("bufferedReaderTest: %15d\n", bufferedReaderTest(fileName, largeTest));
        System.out.printf("fileReaderTest: %15d\n", fileReaderTest(fileName, largeTest));
    }
    public static long printWriterTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (int i = 0; i < n; i++) {
                out.print('A');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public static long bufferWriterTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < n; i++) {
                out.write('A');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public static long fileWriterTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (FileWriter out = new FileWriter(fileName)) {
            for (int i = 0; i < n; i++) {
                out.write('A');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public static long scannerTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            scanner.useDelimiter("");
            for (int i = 0; i < n; i++) {
                if (scanner.hasNext()) {
                    scanner.next().charAt(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public static long bufferedReaderTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < n; i++) {
                if (in.ready()) {
                    in.read();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public static long fileReaderTest(String fileName, int n) {
        long startTime = System.nanoTime();
        try (FileReader in = new FileReader(fileName)) {
            for (int i = 0; i < n; i++) {
                in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

}


