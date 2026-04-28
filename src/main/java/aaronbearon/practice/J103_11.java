package aaronbearon.practice;

import java.util.Scanner;

public class J103_11 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter an even or odd number: ");
        int n = input.nextInt();
        if (n % 2 == 0) {
            System.out.println("It's even.");
        } else {
            System.out.println("It's odd.");
        }
    }
}
