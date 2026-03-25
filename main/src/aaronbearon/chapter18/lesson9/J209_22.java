package aaronbearon.chapter18.lesson9;

import java.util.Scanner;

public class J209_22 {
    // Use a recursion and an int array to store and display the Fibonacci numbers up to the user's number count.
    public static void main(String[] args) {
        int size = getNumsCount();
        int[] fibs = new int[size + 1];
        System.out.print("Here are the numbers: ");
        getFibs(fibs, true, size);

        System.out.println();
    }

    //* Unlike the textbook's algorithm, this one stores the numbers in an array.
    // It allows all the numbers in the user's range to be printed.
    public static int getFibs(int[] fibs, boolean canPrint, int index) {
        if (index < 2) {
            fibs[index] = index;
        } else if (canPrint && index == 2) {
            fibs[index] = getFibs(fibs, true, 0) + getFibs(fibs, true, 1);
        } else if (canPrint) {
            // Only print the number through the first recursive branch.
            // After that, a false boolean is passed to the next call to prevent printing.
            fibs[index] = getFibs(fibs, true, index - 1) + getFibs(fibs, false, index - 2);
        } else {
            fibs[index] = getFibs(fibs, false, index - 1) + getFibs(fibs, false, index - 2);
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
