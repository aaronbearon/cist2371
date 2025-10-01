package aaronbearon.chapter5.lesson5;

import java.util.Scanner;

public class J105_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int answer = (int) (Math.random() * 101);
        System.out.print("Enter a positive number in the range "
                + "of 0-100 and I'll tell you if it matches: ");
        while (true) {
            int n = input.nextInt();

            // User needs to know if the number is too low or too high.
            if (n < answer) {
                System.out.print("Too low, enter a higher number: ");
            } else if (n > answer) {
                System.out.print("Too high, enter a lower number: ");
            } else {
                System.out.println("Yes! It's " + n + ".");
                break;
            }
        }
    }
}
