package aaronbearon.chapter6.lesson6;

import java.util.Scanner;

public class J106_5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("? ");
        int yy = input.nextInt();
        System.out.print("? ");
        int mm = input.nextInt();
        printmm(yy, mm);
    }

    public static void printmm(int yy, int mm) {
        printmmTitle(yy, mm);
        printmmBody(yy, mm);
    }

    public static void printmmTitle(int yy, int mm) {
        System.out.println(" " + getmmName(mm) + " " + yy);
        System.out.println("-----------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }

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

    public static void printmmBody(int yy, int mm) {
        int sDay = getSDay(yy, mm);
        int numberOfDaysInmm = getNOfDInmm(yy, mm);
        for (int i = 0; i < sDay; i++) {
            System.out.print("    ");
        }
        for (int i = 1; i <= numberOfDaysInmm; i++) {
            System.out.printf("%4d", i);
            if ((i + sDay) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public static int getSDay(int yy, int mm) {
        final int SD_4_J_1_1800 = 3;
        int totalNofD = getTotalNofD(yy, mm);
        return (totalNofD + SD_4_J_1_1800) % 7;
    }

    public static int getTotalNofD(int yy, int mm) {
        int total = 0;
        for (int i = 1800; i < yy; i++) {
            if (isLeapyy(i)) {
                total = total + 366;
            } else {
                total = total + 365;
            }
        }
        for (int i = 1; i < mm; i++) {
            total = total + getNOfDInmm(yy, i);
        }
        return total;
    }

    public static int getNOfDInmm(int yy, int mm) {
        if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) return 31;
        if (mm == 4 || mm == 6 || mm == 9 || mm == 11) return 30;
        if (mm == 2) return isLeapyy(yy) ? 29 : 28;
        return 0;
    }

    public static boolean isLeapyy(int yy) {
        return yy % 400 == 0 || (yy % 4 == 0 && yy % 100 != 0);
    }
}