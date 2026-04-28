package aaronbearon.chapter4.lesson4;

import java.util.Scanner;

/**
 * Lab 4, Part 4 - guessing letters
 * Aaron Blum
 */
public class J104_4 {
    public static void main(String[] args) {

        // Get the input from user.
        // Allow case-insensitive characters.
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter two letters: ");
        String n = input.nextLine();
        String guess = n.toUpperCase();

        // Validate the input.
        if (guess.length() != 2) {
            System.out.println("Invalid input");
            System.exit(1);
        }

        char letter1 = guess.charAt(0);
        char letter2 = guess.charAt(1);
        if (letter1 < 'A' || letter1 > 'Z') {
            System.out.println("Error, invalid first character.");
            System.exit(1);
        }

        if (letter2 < 'A' || letter2 > 'Z') {
            System.out.println("Error, invalid second character.");
            System.exit(1);
        }

        // Generate the secret word.
        String word = "";
        int i = 0;

        // Loops are discussed in chapter 5.
        while (i < 5) {
            int temp = 'A' + (int) (Math.random() * 26);
            word = word.concat(String.valueOf((char) temp));
            i++;
        }

        // Check to see if they won.
        boolean firstOk = letter1 == word.charAt(0);
        boolean secondOk = letter2 == word.charAt(word.length() - 1);
        if (firstOk && secondOk) {
            System.out.println("Both are correct.");
        } else if (firstOk) {
            System.out.println("The first one is correct.");
        } else if (secondOk) {
            System.out.println("The second one is correct.");
        } else {
            System.out.println("Both are wrong.");
        }

        // Show the answer.
        System.out.println("Generated sequence is " + word);
    }
}
