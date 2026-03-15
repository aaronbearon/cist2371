package aaronbearon.chapter3.interview;

import java.util.Scanner;

public class J103_21 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an even or odd integer: ");
        int n = input.nextInt();
        if (n % 2 == 0) {
            System.out.println("Your number is even.");
        } else if (n % 2 == 1) {
            System.out.println("Your number is odd.");
        } else {
            System.out.println("Error, cannot get here.");
        }
    }
}
