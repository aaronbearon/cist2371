package aaronbearon.chapter7.lesson7;

import java.util.Scanner;

/**
 * Aaron Blum
 * Java Lab 7
 * 2025-10-22
 */
public class J107_4 {
    /**
     * A guessing game program like Hangman.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final int SIZE = 10;
        // The correct answer
        char[] key = {'c', 'o', 'n', 'v', 'e', 'r', 's', 'i', 'n', 'g'};
        // Contains any correct, revealed letters the user has guessed, in order.
        // Unguessed letters are underscores.
        char[] revealed = new char[SIZE];
        initializeEmpty(revealed);
        while (true) {
            int[] indices = new int[SIZE];
            System.out.print("Guess one character (lowercase) in my array: ");
            char ch = input.nextLine().charAt(0);
            if (ch == '-' || ch == '0') {
                break;
            }

            validateInput(ch);

            // Find the correct matches.
            int spots = search(ch, indices, revealed, key);
            // Inform the user of the result.
            printResults(ch, spots, indices, revealed);
        }

        System.out.println("Ok, done!");
    }

    /**
     * Initialize an array to contain only underscores.
     *
     * @param array the array to initialize
     */
    public static void initializeEmpty(char[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = '_';
        }
    }

    /**
     * Ensures the user's guess is a letter. Otherwise, an error occurs.
     *
     * @param c the user's guess
     */
    public static void validateInput(char c) {
        if (c < 'a' || c > 'z') {
            System.err.println("Invalid char");
            System.exit(1);
        }
    }

    /**
     * Compute the correct positions (if any) of the guess
     *
     * @param c        the guess
     * @param indices  output: updates this array with the position of any correct guesses
     * @param revealed output: updates this array with revealed letters that are correct
     * @param key      the answer to match
     * @return the count of correct matches for this guess
     */
    public static int search(char c, int[] indices, char[] revealed, char[] key) {
        int count = 0;
        for (int i = key.length - 1; i >= 0; i--) {
            if (key[i] == c) {
                revealed[i] = c;
                indices[count] = i + 1;
                count++;
            }
        }

        return count;
    }

    /**
     * Print the results of the current guess.
     *
     * @param c        the current guess
     * @param spots    the number of correct instances of this guess
     * @param indices  the positions of correct instances of this guess
     * @param revealed the total revealed letters that are correct
     */
    public static void printResults(char c, int spots, int[] indices, char[] revealed) {
        System.out.println("    1 2 3 4 5 6 7 8 9 0");
        System.out.print("My array: ");
        for (int i = 0; i < revealed.length; i++) {
            System.out.print(revealed[i]);
        }

        System.out.println();

        if (spots != 0) {
            System.out.print("Good: " + c + " is in ");
            for (int i = spots - 1; i >= 0; i--) {
                System.out.print(indices[i]);

                if (i != 0) {
                    System.out.print(" and ");
                }
            }
        } else {
            System.out.print("Nope");
        }

        System.out.println();
        System.out.println("----------------------------------------------------------------------");
    }
}
