//Bochen Wang
//bochenw

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class YearPop {
    private int year;
    private double pop;

    public YearPop(int year, double pop) {
        this.year = year;
        this.pop = pop;
    }

    public int getYear() {
        return year;
    }

    public double getPop() {
        return pop;
    }
}

public class Lab6Main {
    private File myfile = null;
    private Scanner fileScanner = null;
    private ArrayList<YearPop> data = null;

    public boolean openFile(String filename) {
        myfile = new File(filename);
        try {
            fileScanner = new Scanner(myfile);
            return true;
        } catch (IOException e) {
            System.out.println("Error opening the file: " + e.getMessage());
            return false;
        }
    }

    public YearPop makeData(String line) {
        String[] parts = line.split(",");
        int year = Integer.parseInt(parts[0]);
        double pop = Double.parseDouble(parts[1]);
        return new YearPop(year, pop);
    }

    public void createList() {
        data = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            YearPop yearPop = makeData(line);
            data.add(yearPop);
        }
    }

    public double findYear(int year) {
        for (YearPop yearPop : data) {
            if (yearPop.getYear() == year) {
                return yearPop.getPop();
            }
        }
        return -1.0;
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Lab6Main lab = new Lab6Main();

        System.out.print("Enter the filename: ");
        String filename = keyboard.nextLine();

        if (lab.openFile(filename)) {
            lab.createList();

            char choice;
            do {
                System.out.print("Enter a year to look up: ");
                int year = keyboard.nextInt();
                double density = lab.findYear(year);

                System.out.println("Year: " + year);
                if (density != -1.0) {
                    System.out.println("Population density: " + density);
                } else {
                    System.out.println("Year not found.");
                }

                System.out.print("Do you want to look up another? (Y/N): ");
                choice = keyboard.next().charAt(0);
            } while (choice == 'Y' || choice == 'y');
        }

        keyboard.close();
    }
    public static int computeFibonacci(int first, int last, int n){
        if(n == -1) return first;
        else if (n == 0) {return last;}
        else{
            return computeFibonacci(last, first + last, n -1);

        }
    }
}
