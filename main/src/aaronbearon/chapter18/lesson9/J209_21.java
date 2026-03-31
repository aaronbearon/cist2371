package aaronbearon.chapter18.lesson9;

import java.util.Scanner;

public class J209_21 {
    // Use a for loop and an int array to store and display the Fibonacci numbers up to the user's number count.
    public static void main(String[] args) {
        int size = getNumsCount();
        int[] fibs = new int[size + 1];
        System.out.print("Here are the numbers: ");
        for (int i = 0; i <= size; i++) {
            if (i < 2) {
                fibs[i] = i;
            } else {
                fibs[i] = fibs[i - 1] + fibs[i - 2];
            }

            System.out.print(" " + fibs[i]);
        }

        System.out.println();
    }

    // Return the number of non-zero fibs and properly handle exceptions.
    public static int getNumsCount() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("How many Fibonacci numbers do you want (in addition to 0)? ");
                int size = input.nextInt();
                if (size < 0) {
                    throw new Exception();
                }
                return size;
            } catch (Exception e) {
                System.out.println("Error, please enter a non-negative integer.");
            }
        }
    }
}

/*
TODO
*/
