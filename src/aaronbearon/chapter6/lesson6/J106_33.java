package aaronbearon.chapter6.lesson6;

import java.util.Scanner;

public class J106_33 {
    public static void main(String[] args) {
        printInstructions();
        Scanner input = new Scanner(System.in);

        int total = processEachCustomer(input);

        // Total final results.
        System.out.printf("%nThe total parking sales from yesterday were $%.2f.%n", total / 100.0);
    }

    /** Instruct the user what to do. */
    private static void printInstructions() {
        System.out.println("Please enter each customer's hours parked.");
        System.out.println("Enter 0 to quit.");
        System.out.println("You'll see the charge along with a running total of all charges.");
        System.out.println();
    }

    /** Loop and enter each parking ticket. */
    public static int processEachCustomer(Scanner input) {
        int total = 0;

        for (int i = 1; true; i++) {
            double hours = inputCustomer(i, input);

            if (hours == 0) {
                return total;
            }

            // Compute the price of these hours.
            int current = computePrice(hours);

            total += current;

            // Display running results.
            printRunningResults(current, total);
        }
    }

    private static double inputCustomer(int i, Scanner input) {
        System.out.print("Customer " + i + " hours: ");
        while (true) {
            double hours = input.nextDouble();
            if (hours >= 0 && hours <= 24) {
                return hours;
            }

            System.out.print("Error, invalid input, try again: ");
        }
    }

    /** Compute the price of these hours. */
    private static int computePrice(double hours) {
        int intHours = (int) Math.ceil(hours);
        if (intHours < 3) {
            return 200;
        } else if (intHours > 19) {
            return 1000;
        } else {
            return 200 + 50 * (intHours - 3);
        }
    }

    /** Display running results. */
    private static void printRunningResults(int current, int total) {
        System.out.printf("The charge is $%.2f.%n", current / 100.0);
        System.out.printf("The running total is $%.2f.%n", total / 100.0);
        System.out.println();
    }
}
