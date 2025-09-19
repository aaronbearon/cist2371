package aaronbearon.chapter3.practice;

import java.util.Scanner;

public class J103_11_test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user for an integer.
        System.out.print("Enter an even or odd integer: ");

        // Input the integer.
        int n = input.nextInt();

        // Use an if else statement. If mod is 0 it's even. If mod is 1 it's odd.
        if (n % 2 == 0) {
            System.out.println("Your number is even.");
        } else if (n % 2 == 1) {
            System.out.println("Your number is odd.");
        } else {
            System.out.println("Error, should never get here.");
            System.exit(1);
        }
    }
}
