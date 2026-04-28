package aaronbearon.chapter3.practice;

import java.util.Scanner;

public class J103_12_test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user for an integer.
        System.out.print("Enter an even or odd integer: ");

        // Input the integer.
        int n = input.nextInt();

        // Use a switch statement. If mod is 0 it's even. If mod is 1 it's odd.
        switch (n % 2) {

            // Don't forget the break keyword in each case.
            case 0:
                System.out.println("Your number is even.");
                break;
            case 1:
                System.out.println("Your number is odd.");
                break;
            default:
                System.out.println("Error, should never get here.");
                System.exit(1);
        }
    }
}
