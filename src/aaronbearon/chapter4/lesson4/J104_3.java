package aaronbearon.chapter4.lesson4;

import java.util.Scanner;

public class J104_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // User knows in advance what their number is for.
        System.out.print("Please enter a positive integer for days or seconds: ");
        int n = input.nextInt();

        // User decides which time units to measure.
        System.out.print("Day(1) or Time(2)? ");
        int type = input.nextInt();
        switch (type % 2) {
            case 0:

                // Call a method. I found out how by looking in chapter 6.
                even(n);
                break;
            case 1:

                // Call a method. I found out how by looking in chapter 6.
                odd(n);
                break;
            default:
                System.out.println("Error, can't get here.");
                System.exit(1);
        }
    }

    // Method to get called in the main method.
    // Prints hours minutes and seconds.
    public static void even(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int secondsOfHour = totalSeconds % 3600;
        int minutes = secondsOfHour / 60;
        int seconds = secondsOfHour % 60;
        System.out.println("Your time is " + hours + " hours, " + minutes + " minutes, and " + seconds + " seconds.");
    }

    // Method to get called in the main method.
    // Prints years months and days.
    public static void odd(int totalDays) {
        int years = totalDays / 365;
        int daysOfYear = totalDays % 365;
        int months = daysOfYear / 30;
        int days = daysOfYear % 30;
        System.out.println("Your time is " + years + " years, " + months + " months, and " + days + " days.");
    }
}
