package aaronbearon.chapter5;

import java.util.Scanner;

public class J10555 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a number (1-6): ");
        switch (input.nextInt()) {
            case 1:
                System.out.println();
                forLoop();
                break;
            case 2:
                System.out.println();
                doWhile();
                break;
            case 3:
                System.out.println();
                whileStepThree();
                break;
            case 4:
                System.out.println();
                whileSum();
                break;
            case 5:
                System.out.println();
                loopPow();
                break;
            case 6:
                System.out.println();
                validateInput();
                break;
            default:
                System.out.println("Sorry, nothing here.");
                System.exit(0);
        }
    }

    public static void forLoop() {

        // Syntax of the for loop.
        for (int a = -5; a <= 5; a++) {
            if (a != -5) {
                System.out.print(", ");
            }
            System.out.print(a);
        }
    }

    public static void doWhile() {
        int a = 0;
        do {

            // Excludes the comma before the first value.
            if (a != 0) {
                System.out.print(", ");
            }
            System.out.print(a++);
        } while (a <= 5);
    }

    public static void whileStepThree() {
        int a = 0;
        while (a <= 3) {
            if (a != 0) {
                System.out.print(", ");
            }

            // Prints out values in step 3.
            System.out.print(3 * (a++));
        }
    }

    public static void whileSum() {
        int a = 1;
        int sum = 0;
        while (a <= 50) {
            if (a != 1) {
                System.out.print(" + ");
            }

            sum += a;

            // Sum of even numbers.
            System.out.print(2 * a);
            a++;
        }

        // Prints the sum correctly.
        System.out.print(" = " + (2 * sum));
    }

    public static void loopPow() {
        int a = 0;
        int r = 0;

        while (r <= 1000) {

            // Put a++ first to print it correctly in the end.
            a++;
            r = (int) (Math.pow(2, a));
        }

        System.out.print(a);
    }

    public static void validateInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a positive number and I'll tell you if it's valid: ");
        int n = input.nextInt();
        while (n != 5) {

            // User needs to know if the number is too low or too high.
            if (n < 5) {
                System.out.print("Too low, enter a higher number: ");
            }

            if (n > 5) {
                System.out.print("Too high, enter a lower number: ");
            }

            n = input.nextInt();
        }

        System.out.print("Correct! The number is " + n + ".");
    }
}
