package aaronbearon.chapter18.lesson9;

import java.util.Scanner;

public class J209_22 {
    // Use a recursion and an int array to store and display the Fibonacci numbers up to the user's number count.
    public static void main(String[] args) {
        int size = getNumsCount();
        int[] fibs = new int[Math.max(0, size + 1)];
        System.out.print("Here are the numbers: ");
        getFibs(fibs, true, size);

        System.out.println();
    }

    //* Unlike the textbook's algorithm, this one stores the numbers in an array.
    // The array allows us to cache already-computed values -- this makes the algorithm
    // linear instead of exponential.
    // If canPrint is true, print the series of numbers as we go.
    public static int getFibs(int[] fibs, boolean canPrint, int index) {
        // This method needs to terminate if a negative index is passed in.
        if (index < 0) {
            System.out.println("Sorry, Fibonacci doesn't go negative.");
            return -1;
        }

        if (fibs[index] == 0 && index > 0) {
            if (index == 1) {
                fibs[index] = 1 + getFibs(fibs, canPrint, 0);
            } else {
                fibs[index] = getFibs(fibs, canPrint, index - 1) + getFibs(fibs, false, index - 2);
            }
        }

        if (canPrint) {
            System.out.print(" " + fibs[index]);
        }
        return fibs[index];
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
