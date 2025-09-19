package aaronbearon.chapter3.interview;

import java.util.Scanner;

public class J103_22 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an even or odd integer: ");
        int n = input.nextInt();
        switch (n % 2) {
            case 0:
                System.out.println("Your number is even.");
                break;
            case 1:
                System.out.println("Your number is odd.");
                break;
            default:
                System.out.println("Error, cannot get here.");
        }
    }
}
