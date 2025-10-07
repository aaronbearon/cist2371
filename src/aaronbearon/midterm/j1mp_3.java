package aaronbearon.midterm;

import java.util.Scanner;

public class j1mp_3 {
    public static void main(String[] args) {
        // Instruct the user what to do.
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter each customer's hours parked.");
        System.out.println("Enter 0 to quit.");
        System.out.println("You'll see the charge along with a running total of all charges.");
        System.out.println();

        // Loop and enter each parking.
        int total = 0;
        for (int i = 1; true; i++) {
            System.out.print("Customer " + i + " hours: ");
            double hours;
            while (true) {
                hours = input.nextDouble();
                if (hours >= 0 && hours <= 24) {
                    break;
                }

                System.out.print("Error, invalid input, try again: ");
            }

            if (hours == 0) {
                break;
            }

            // Compute the price of these hours.
            int intHours = (int) Math.ceil(hours);
            int current;
            if (intHours < 3) {
                current = 200;
            } else if (intHours > 19) {
                current = 1000;
            } else {
                current = 200 + 50 * (intHours - 3);
            }

            // Display results.
            System.out.printf("The charge is $%.2f.%n", current / 100.0);
            total += current;
            System.out.printf("The running total is $%.2f.%n", total / 100.0);
            System.out.println();
        }

        // Total final results.
        System.out.printf("%nThe total parking sales from yesterday were $%.2f.%n", total / 100.0);
    }
}
