package aaronbearon.practice;

import java.util.Scanner;

public class J103_12 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an even or odd number: ");
        int n = input.nextInt();
        switch (n % 2) {
            case 0:
                System.out.println("It's even.");
                break;
            case 1:
                System.out.println("It's odd.");
                break;
            default:
                System.out.println("Invalid number!");
        }
    }
}
