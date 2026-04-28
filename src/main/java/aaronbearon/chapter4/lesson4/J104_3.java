package aaronbearon.chapter4.lesson4;

import java.util.Scanner;

/**
 * Lab 4, Part 3 - modulus time
 * Aaron Blum
 */
public class J104_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // User decides which time units to measure.
        System.out.print("Day(1) or Time(2)? ");
        int type = input.nextInt();
        switch (type) {
            case 1:
                System.out.print("Please enter a positive integer for days: ");

                // Call a method. I found out how by looking in chapter 6.
                // The user input can be passed directly with no variable needed.
                printDayComputation(input.nextInt());
                break;
            case 2:
                System.out.print("Please enter a positive integer for seconds: ");

                // Call a method. I found out how by looking in chapter 6.
                // The user input can be passed directly with no variable needed.
                printTimeComputation(input.nextInt());
                break;
            default:
                System.out.println("Error, invalid type.");
                System.exit(1);
        }
    }

    // Prints years months and days.
    public static void printDayComputation(int totalDays) {
        int years = totalDays / 365;
        int daysOfYear = totalDays % 365;
        int months = daysOfYear / 30;
        int days = daysOfYear % 30;
        System.out.println("Your days are " + years + " years, " + months + " months, and " + days + " days.");
    }

    // Prints hours minutes and seconds.
    public static void printTimeComputation(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int secondsOfHour = totalSeconds % 3600;
        int minutes = secondsOfHour / 60;
        int seconds = secondsOfHour % 60;
        System.out.println("Your time is " + hours + " hours, " + minutes + " minutes, and " + seconds + " seconds.");
    }
}
