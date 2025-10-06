package aaronbearon.midterm;

import java.util.Scanner;

public class j1mp_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter each customer's hours parked.");
        System.out.println("You'll see the charge along with a running total of all charges.");
        System.out.println();
        int total = 0;
        for (int i = 1; true; i++) {
            double hours;
            int current = 0;
            System.out.print("Customer " + i + " hours: ");
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

            if (hours > 3) {
                current = ((int) (Math.ceil(hours - 3)) * 50) + 200;
            } else {
                current = 200;
            }

            if (current > 1000) {
                current = 1000;
            }

            System.out.printf("The charge is $%.2f.%n", current / 100.0);
            total += current;
            System.out.printf("The charge is $%.2f.%n", total / 100.0);
            System.out.println();
        }

        System.out.printf("%nThe total parking sales from yesterday were $%.2f.%n", total / 100.0);
    }
}
