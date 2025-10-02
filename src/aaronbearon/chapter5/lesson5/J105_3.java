package aaronbearon.chapter5.lesson5;

import java.util.Scanner;

/**
 Lab 5, Part 3 - Guess a number
 Aaron Blum
 */
public class J105_3 {
    public static void main(String[] args) {
        // Pick a secret answer.
        final int answer = (int) (Math.random() * 101);

        // Setup the input and give instructions.
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a positive number in the range of 0-100 and I'll tell you if it matches: ");
        do {
            int n = input.nextInt();

            // User needs to know if the number is too low or too high.
            if (n < answer) {
                System.out.print("Too low, enter a higher number: ");
            } else if (n > answer) {
                System.out.print("Too high, enter a lower number: ");
            } else {
                // Win!
                System.out.println("Yes! It's " + n + ".");
                break;
            }
        } while (true);
    }
}
