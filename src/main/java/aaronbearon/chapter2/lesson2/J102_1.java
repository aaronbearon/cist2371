package aaronbearon.chapter2.lesson2;

import java.util.Scanner;

public class J102_1 {
    public static void main(String[] args) {
        // Create an instance of the Scanner.
        Scanner input = new Scanner(System.in);

        // Prompt user and get input.
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter the last four digits of your phone number: ");
        String phone = input.nextLine();

        // Print the name and end of phone number.
        System.out.println("Your name is: " + name);
        System.out.println("The last four digits of your number are: " + phone);
    }
}
