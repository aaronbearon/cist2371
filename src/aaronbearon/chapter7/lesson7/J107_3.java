package aaronbearon.chapter7.lesson7;

import java.util.Scanner;

import static java.lang.Character.toUpperCase;

public class J107_3 {
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
            String dir = setDir(input, array);
            printResults(dir, array);
        }

        System.out.println("Ok, done!");
    }

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

    public static String setDir(Scanner input, char... array) {
        System.out.print("Ascending(1) or Descending(2) order? ");
        switch (input.nextInt()) {
            case 1:
                printUserArray(array);

                // Ascending
                sort(true, array);
                return "Ascending :";
            case 2:
                printUserArray(array);

                // Descending
                sort(false, array);
                return "Descending:";
            default:
                System.err.println("Wrong choice");
                System.exit(1);
                return "";
        }
    }

    public static void printUserArray(char... array) {
        System.out.println("=============");

        System.out.print("Your array:");
        for (char c : array) {
            System.out.print(" " + c);
        }

        System.out.println();
    }

    /**
     * Don't swap the lowest element with the correct position each time.
     * Compare each new element with previous elements and swap it in place.
     */
    public static void sort(boolean bool, char... array) {

        /** No need to check index 0 */
        for (int i = 1; i < array.length; i++) {

            /**
             * Current index is checked.
             * Might be minimum: Swap down to the lowest index.
             */
            for (int j = i, k = j - 1; j > 0; j--, k--) {
                // Ascend or descend based on bool.
                if (checker(bool, j, k, array)) {
                    char temp = array[j];
                    array[j] = array[k];
                    array[k] = temp;
                }
            }
        }
    }

    public static boolean checker(boolean bool, int j, int k, char... array) {

        // Avoids sorting by case.
        if (bool) {
            return toUpperCase(array[j]) < toUpperCase(array[k]);
        } else {
            return toUpperCase(array[j]) > toUpperCase(array[k]);
        }
    }

    public static void printResults(String dir, char... array) {
        System.out.print(dir);
        for (char c : array) {
            System.out.print(" " + c);
        }

        System.out.println();
        System.out.println("---------------------------------------------------------------");
    }
}
