//Bochen Wang
//bochenw
import java.util.Scanner;
public class Lab12Driver {
    public static Scanner s = new Scanner(System.in);
    public static void main(String[] args) throws BadWeekday {
        System.out.println("Problem 1");
        problem1();
        System.out.println("\n");
        System.out.println("Problem 2");
        try{
            problem2();
        }catch (BadWeekday b){
            System.out.println(b.getMessage());
        }
        System.out.println("\n");
        System.out.println("Problem 3");
        try{
            problem3();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
        System.out.println("Problem 4");
        try{
            problem4();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
        System.out.println("Problem 5");
        try{
            problem5();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
        System.out.println("Problem 1");
        problem6();
        System.out.println("\n");
    }
    public static void problem1(){
        System.out.println("Please input a day:");
        String entry = s.nextLine();
        if(!BadWeekday.WEEKDAYS.contains(entry)){
            System.out.println("Bad day entered");
        }else{
            System.out.println(entry);
            System.out.println("Nice Job!");
        }
    }
    public static void problem2() throws BadWeekday{
        System.out.println("Please input a day:");
        String entry = s.nextLine();
        if(!BadWeekday.WEEKDAYS.contains(entry)){
            throw new BadWeekday("Bad day entered");
        }else{
            System.out.println(entry);
            System.out.println("Nice Job!");
        }
    }
    public static void problem3() {
        try {
            problem2();
        } catch (BadWeekday e) {
            throw new RuntimeException(e);
        }
    }
    public static void problem4() throws BadWeekday {
        problem2();
    }
    public static void problem5() throws BadWeekday {
        try {
            problem2();
        }catch (Exception e){
            System.out.println("Caught" + e.getMessage());
            throw(e);
        }
    }
    public static void problem6() throws BadWeekday {
        problem2();
    }//basically the same
}
