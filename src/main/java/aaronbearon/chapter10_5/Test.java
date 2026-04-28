package aaronbearon.chapter10_5;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int sum = 0;
        System.out.print("Please enter the highest number: ");
        int highest = input.nextInt();
        for (int i = 1; i <= highest; i++) {
            sum += i;
        }

        System.out.println("Sum: " + sum);
    }
}

/*
Scanner input = new Scanner(System.in);
        System.out.print("Please enter the highest number: ");
        int sum = input.nextInt();
        for (int i = 1; i <= sum; i++) {
            sum += i;
        }

        System.out.println("Sum: " + sum);
 */
