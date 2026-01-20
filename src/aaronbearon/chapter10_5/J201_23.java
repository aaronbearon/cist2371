package aaronbearon.chapter10_5;

import java.util.Scanner;

/**
 * Summary:
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
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter your name: ");
            String name = input.nextLine();
            if (name.equals("-1")) {
                break;
            }

            System.out.print("Please enter your age: ");
            int age = input.nextInt();
            if (age < 0) {
                break;
            }

            input.nextLine();
            System.out.printf("Name: %s%nAge: %d%n%n", name, age);
        }

        System.out.println("Thank you!");
    }
}

/*
 * Documentation:
 * Included everything in the while loop except Scanner.
 */
