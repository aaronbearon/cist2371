package aaronbearon.chapter3.interview;

import java.util.Scanner;

public class J103_12 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt user for input.
        System.out.print("Enter an even or odd integer: ");

        // Get the input.
        int n = input.nextInt();

        // If n % 2 is 0 n is even. // If N % 2 is 1 n is odd.
        switch (n % 2) {
            case 0:
                System.out.println("Your number is even.");
                break;
            case 1:
                System.out.println("Your number is odd.");
                break;
            default:
                System.out.println("Error, cannot get here.");
                System.exit(1);
        }
    }
}
