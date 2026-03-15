package aaronbearon.chapter4.interview;

import java.util.Scanner;

public class J104_11char {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number (1-5) of random characters: ");
        int n = input.nextInt();
        if (n < 1 || n > 5) {
            System.out.println("Error, invalid number.");
            System.exit(1);
        }

        int i = 0;
        while (i < n) {

            // Use ascii chars 33-126 inclusively.
            // So, all printable characters except space & delete.
            System.out.print((char) (33 + (Math.random() * 94)));
            i++;
        }

        System.out.println();
    }
}
