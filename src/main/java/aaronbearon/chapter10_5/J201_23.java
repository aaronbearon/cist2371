package aaronbearon.chapter10_5;

import java.util.Scanner;

/**
 * CIST 2372 Java 2, CRN 49043
 * Aaron Blum
 * Interview 1 Problem 2-3
 * Title: Repeat input info user
 * Description:
 * Prompt the user for their name and age,
 * print them, and do this until user enter -1 as a sentinel.
 */
public class J201_23 {
    public static void main(String[] args) {
        // Allows user to input values.
        Scanner input = new Scanner(System.in);
        // Sentinel loop
        while (true) {
            // Prompt followed by input for String
            System.out.print("Please enter your name: ");
            String name = input.nextLine();
            if (name.equals("-1")) {
                // Checking empty string would make more sense here, but
                // the program instructions specifically require checking -1.
                break;
            }

            // Prompt followed by input for int
            System.out.print("Please enter your age: ");
            int age = input.nextInt();
            if (age < 0) {
                // -1 is required, but we can break on any invalid age
                break;
            }

            // Tricky! We read an int from the input, but this does
            // not use up the rest of the line. We have to throw away
            // the rest of the line (like the enter key) before we loop.
            input.nextLine();
            System.out.printf("Name: %s%nAge: %d%n%n", name, age);
        }

        System.out.println("Thank you!");
    }
}

/*
 * Included everything in the while loop except Scanner and final print.
 * Used break keyword which executes when name is -1.
 * Used age < 0 for efficiency.
 * Used line spacing to visibly separate the processes for the two fields.
 * Combined the printing fields into one printf statement.
 * Concluded with a "Thank you"!
 */
