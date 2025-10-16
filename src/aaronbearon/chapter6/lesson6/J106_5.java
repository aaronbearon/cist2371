package aaronbearon.chapter6.lesson6;

import java.util.Scanner;

/**
 * Print calendar for specific year and month.
 * Aaron Blum, Java Lab 6.
 */
public class J106_5 {
    public static void main(String[] args) {
        // Input a year since 1800 and a month 12.
        // No input validation is done.
        Scanner input = new Scanner(System.in);
        System.out.print("? ");
        int yy = input.nextInt();
        System.out.print("? ");
        int mm = input.nextInt();
        // Print the calendar for the inputs.
        printmm(yy, mm);
    }

    // Print a calendar layout for the month.
    public static void printmm(int yy, int mm) {
        printmmTitle(yy, mm);
        printmmBody(yy, mm);
    }

    // Print the top of the calendar.
    public static void printmmTitle(int yy, int mm) {
        System.out.println("         " + getmmName(mm) + " " + yy);
        System.out.println("-----------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }

    // Returns the name of the month, 1-12.
    public static String getmmName(int mm) {
        String mmName = "";
        switch (mm) {
            case 1:
                mmName = "January";
                break;
            case 2:
                mmName = "February";
                break;
            case 3:
                mmName = "March";
                break;
            case 4:
                mmName = "April";
                break;
            case 5:
                mmName = "May";
                break;
            case 6:
                mmName = "June";
                break;
            case 7:
                mmName = "July";
                break;
            case 8:
                mmName = "August";
                break;
            case 9:
                mmName = "September";
                break;
            case 10:
                mmName = "October";
                break;
            case 11:
                mmName = "November";
                break;
            case 12:
                mmName = "December";
        }
        return mmName;
    }

    // Print the body of the calendar as a grid.
    public static void printmmBody(int yy, int mm) {
        // Find out what day of the week the month starts on.
        int sDay = getSDay(yy, mm);
        // Find out how many days in the month.
        int numberOfDaysInmm = getNOfDInmm(yy, mm);
        // Print whitespace for days of week from the previous month to get to month start.
        for (int i = 0; i < sDay; i++) {
            System.out.print("    ");
        }
        // Print all the days of the month in order.
        for (int i = 1; i <= numberOfDaysInmm; i++) {
            System.out.printf("%4d", i);
            //* If the end of the week is reached, print a new line to start the next week.
            if ((i + sDay) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    // Get the START day of the week for this year and month.
    public static int getSDay(int yy, int mm) {
        // January 1, 1800 is a Wednesday.
        final int SD_4_J_1_1800 = 3;
        // Compute total number of days since Jan 1, 1808.
        int totalNofD = getTotalNofD(yy, mm);
        // Mod to get the actual start day of week for this month and year.
        return (totalNofD + SD_4_J_1_1800) % 7;
    }

    // Returns the total number of days to the start of this month, since Jan 1, 1800.
    // Does not work for dates prior to 1800.
    public static int getTotalNofD(int yy, int mm) {
        int total = 0;
        // Add all the days from years 1800 to this year.
        for (int i = 1800; i < yy; i++) {
            if (isLeapyy(i)) {
                total = total + 366;
            } else {
                total = total + 365;
            }
        }
        // Add all the days from January of this year to this month.
        for (int i = 1; i < mm; i++) {
            total = total + getNOfDInmm(yy, i);
        }
        return total;
    }

    public static int getNOfDInmm(int yy, int mm) {
        //* The months that have 31 days.
        if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) return 31;
        //* The months with 30 days.
        if (mm == 4 || mm == 6 || mm == 9 || mm == 11) return 30;
        // February is weird.
        if (mm == 2) return isLeapyy(yy) ? 29 : 28;
        // Should not get here.
        return 0;
    }

    public static boolean isLeapyy(int yy) {
        //* A leap year is a multiple of 4 without being a multiple of 100, unless also a multiple of 400.
        return yy % 400 == 0 || (yy % 4 == 0 && yy % 100 != 0);
    }
}