// Bochen Wang
// bochenw

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Lab20Main {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("Employee");
            Lab20Main.classFun(c);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    static void classFun(Class<?> c) {
        try {
            // Display the canonical name of the class
            System.out.println("Canonical Name: " + c.getCanonicalName());

            // Display all member data (fields)
            System.out.println("\nFields:");
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.toString());
            }

            // Display all local constructors
            System.out.println("\nDeclared Constructors:");
            Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();
            for (Constructor<?> constructor : declaredConstructors) {
                System.out.println(constructor.toString());
            }

            // Display all constructors
            System.out.println("\nConstructors:");
            Constructor<?>[] constructors = c.getConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor.toString());
            }

            // Display all local methods
            System.out.println("\nDeclared Methods:");
            Method[] declaredMethods = c.getDeclaredMethods();
            for (Method method : declaredMethods) {
                System.out.println(method.toString());
            }

            // Display all methods
            System.out.println("\nMethods:");
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                System.out.println(method.toString());
            }

            // Construct an instance of Employee using its default constructor
            Constructor<?> defaultConstructor = c.getDeclaredConstructor();
            Object employee = defaultConstructor.newInstance();

            // Print details about the instance
            System.out.println("\nEmployee instance details:");
            System.out.println("Is enum: " + employee.getClass().isEnum());
            System.out.println("Is interface: " + employee.getClass().isInterface());
            System.out.println("toString: " + employee.toString());

            // Set and get salary using reflection
            Method setSalary = find(methods, "setSalary");
            setSalary.invoke(employee, 1000.0);

            Method getSalary = find(methods, "getSalary");
            System.out.println("Salary: " + getSalary.invoke(employee));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Method find(Method[] methods, String what) {
        for (Method m : methods) {
            if (m.toString().contains(what)) {
                return m;
            }
        }
        return null;
    }
}