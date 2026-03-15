package aaronbearon.chapter3.interview;

import java.util.Scanner;

public class J103_11 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt user for input.
        System.out.print("Enter an even or odd integer: ");

        // Get the input.
        int n = input.nextInt();

        // If n % 2 is 0 n is even. // If N % 2 is 1 n is odd.
        if (n % 2 == 0) {
            System.out.println("Your number is even.");
        } else if (n % 2 == 1) {
            System.out.println("Your number is odd.");
        } else {
            System.out.println("Error, cannot get here.");
            System.exit(1);
        }
    }
}
