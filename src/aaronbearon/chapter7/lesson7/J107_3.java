package aaronbearon.chapter7.lesson7;

import java.util.Scanner;

import static java.lang.Character.toUpperCase;

/**
 * Aaron Blum
 * Java Lab 7
 * 2025-10-22
 */
public class J107_3 {
    /**
     * A program to sort a user's input characters.
     */
    public static void main(String[] args) {
        System.out.println("Enter characters to sort. Press 0 to exit.");

        // Need sentinel
        while (true) {
            Scanner input = new Scanner(System.in);
            final int SIZE = 10;
            System.out.print(SIZE + " upper or lowercase characters? ");

            // Characters to store in array.
            String chars = input.nextLine();
            char[] array = chars.toCharArray();

            // Sentinel = 0
            if (array.length == 1 && array[0] == '0') {
                break;
            }

            validateInput(SIZE, array);

            boolean isAscending = inputAscending(input);

            System.out.println("=============");
            printUserArray("Your array", array);

            sort(isAscending, array);

            if (isAscending) {
                printUserArray("Ascending ", array);
            } else {
                printUserArray("Descending", array);
            }
            System.out.println("---------------------------------------------------------------");
        }

        System.out.println("Ok, done!");
    }

    /**
     * Validates that the array only contains letters and is the right size.
     *
     * @param SIZE  the expected array size
     * @param array the array to validate
     */
    public static void validateInput(int SIZE, char... array) {
        for (char c : array) {

            // Only allows case-insensitive letters.
            if (toUpperCase(c) < 'A' || toUpperCase(c) > 'Z') {
                System.err.println("Invalid char");
                System.exit(1);
            }
        }

        if (array.length != SIZE) {
            System.out.println("Should be " + SIZE + " chars");
        }
    }

    /**
     * Asks the user to choose a sort order.
     *
     * @param input lets the user input a value
     * @return true if ascending or false for descending
     */
    public static boolean inputAscending(Scanner input) {
        System.out.print("Ascending(1) or Descending(2) order? ");
        switch (input.nextInt()) {
            case 1:
                return true;
            case 2:
                return false;
            default:
                System.err.println("Wrong choice");
                System.exit(1);
                return false;
        }
    }

    /**
     * Prints an array of characters with spaces separating them.
     *
     * @param header prints a header in front of the array
     * @param array  the array to print
     */
    public static void printUserArray(String header, char... array) {
        System.out.print(header);
        System.out.print(" :");
        for (char c : array) {
            System.out.print(" " + c);
        }
        System.out.println();
    }

    /**
     * Sort an array in place.
     *
     * @param isAscending if true sort ascending otherwise descending.
     * @param array       the array to sort
     */
    public static void sort(boolean isAscending, char... array) {
        // Don't swap the lowest element with the correct position each time.
        // Compare each new element with previous elements and swap it in place.

        //* Start checking index 1 to compare to index 0.
        for (int i = 1; i < array.length; i++) {

            /*
             * Current index is checked.
             * Might be minimum: Swap down to the lowest index.
             */
            for (int j = i; j > 0; j--) {
                // Ascend or descend based on isAscending.
                if (needsSwap(isAscending, array[j - 1], array[j])) {
                    char temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    /**
     * Determine if the given characters are in order or not.
     *
     * @param isAscending if true, ascending otherwise descending
     * @param left        the left value
     * @param right       the right value
     * @return true if the order is wrong and needs to be swapped
     */
    public static boolean needsSwap(boolean isAscending, char left, char right) {
        // Avoids sorting by case.
        if (isAscending) {
            return toUpperCase(left) > toUpperCase(right);
        } else {
            return toUpperCase(left) < toUpperCase(right);
        }
    }
}
