package aaronbearon.playground;

import java.util.Scanner;

public class RoundingTester {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // System.out.print("Enter a balance: ");
        // int currentBalance = input.nextInt();
        // double newBalance = currentBalance * 1.05;
        // System.out.println("The new balance is: " + newBalance);
        double testing = (5.0 / 9) * (42.8 - 32);
        System.out.println(Math.round(testing / 4.0) * 4);
        System.out.printf("%.20f%n", testing);
        System.out.println((5.0 / 9) * (42.8 - 32));
        System.out.println(Math.round(testing * 10) / 10.0);
        System.out.printf("%.2f", 45.846 - 31.381);
    }
}
