package aaronbearon.chapter12;

import java.util.InputMismatchException;
import java.util.Scanner;

public class J203_3 {
    public static void main(String[] args) {
        // Allows user to input values.
        Scanner input = new Scanner(System.in);

        // Outer program loop
        boolean again;
        do {
            again = collectUserData(input);
        } while (again);
        System.out.println("Thank you!");
    }

    public static boolean collectUserData(Scanner input) {
        String name = getName(input);
        if (name.equals("-1")) {
            return false;
        }

        int age = getAge(input);
        if (age < 0) {
            return false;
        }

        input.nextLine();
        System.out.printf("Name: %s%nAge: %d%n%n", name, age);
        return true;
    }

    public static String getName(Scanner input) {
        while (true) {
            try {
                System.out.print("Please enter your name: ");
                String name = input.nextLine();

                // Prevents names starting with a number
                if (name.isEmpty() || (name.charAt(0) >= '0' && name.charAt(0) <= '9')) {
                    // Required throw keyword
                    // Exception caught in catch block below
                    throw new InputMismatchException();
                }

                return name;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try it again.");
                System.out.println("First character of name can't be empty or a digit.");
            }
        }
    }

    public static int getAge(Scanner input) {
        while (true) {
            try {
                System.out.print("Please enter your age: ");
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try it again.");
                System.out.println("Age must be an integer.");
            }

            input.nextLine();
        }
    }
}

/*
 * Changed the String name validation from part 2 into a try-catch block.
 * This will (keyword) "throw" an exception handled in the catch block.
 */
