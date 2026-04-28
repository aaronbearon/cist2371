package aaronbearon.chapter4.interview;

import java.util.Scanner;

public class J104_12letter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number (1-5) of random letters: ");
        int n = input.nextInt();
        if (n < 1 || n > 5) {
            System.out.println("Error, invalid input.");
            System.exit(1);
        }

        int i = 0;
        while (i < n) {

            // Use ascii chars 65-90 and 97-122.
            // Allows uppercase and lowercase letters.
            if (Math.random() < 0.5) {
                System.out.print((char) ('A' + (Math.random() * 26)));
            } else {
                System.out.print((char) ('a' + (Math.random() * 26)));
            }

            i++;
        }

        System.out.println();
    }
}
