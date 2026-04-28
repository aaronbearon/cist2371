package aaronbearon.chapter6.Interview;

import java.util.Scanner;

public class J106_13 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 2 numbers to multiply.");
        int a = userInput(input);
        int b = userInput(input);
        printout(a, b, calculation(a, b));
    }

    public static int userInput(Scanner input) {
        System.out.print("? ");
        return input.nextInt();
    }

    public static int calculation(int l, int r) {
        return l * r;
    }

    public static void printout(int a, int b, int c) {
        System.out.println("------------------");
        System.out.printf("%d x %d = %d%n", a, b, c);
    }
}
