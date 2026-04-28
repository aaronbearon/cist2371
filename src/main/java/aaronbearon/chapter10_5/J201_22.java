package aaronbearon.chapter10_5;

import java.util.Scanner;

/**
 * CIST 2372 Java 2, CRN 49043
 * Aaron Blum
 * Interview 1 Problem 2-2
 * Title: Input user info
 * Description:
 * Prompt the user for their name and age.
 * Print them.
 */
public class J201_22 {
    public static void main(String[] args) {
        // Allows user to input values.
        Scanner input = new Scanner(System.in);

        // Prompt followed by input for String
        System.out.print("Please enter your name: ");
        String name = input.nextLine();
        // Prompt followed by input for int
        System.out.print("Please enter your age: ");
        int age = input.nextInt();

        System.out.printf("%nName: %s%nAge: %d%n", name, age);
    }
}

/*
 * Declared and initialized with user input.
 * Put line spacing to break the statements into readable groups.
 * Printed both results with same print statement using printf.
 */
