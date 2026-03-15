package aaronbearon.practice;

import java.util.Scanner;

public class J102_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        String name = input.nextLine();
        System.out.print("Please enter the last 4 digits of your phone number: ");
        String phone = input.nextLine();

        System.out.println("Your name is " + name + ".");
        System.out.println("The last 4 digits of your phone number are " + phone + ".");
    }
}
