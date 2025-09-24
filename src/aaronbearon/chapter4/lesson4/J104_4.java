package aaronbearon.chapter4.lesson4;

import java.util.Scanner;

public class J104_4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an alphabetical letter: ");

        // Allows user to enter case-insensitive first character.
        char letter1 = Character.toUpperCase(input.next().charAt(0));
        if (!(letter1 >= 'A' && letter1 <= 'Z')) {
            System.out.println("Error, invalid character.");
            System.exit(1);
        }

        System.out.print("Please enter another alphabetical letter: ");

        // Allows user to enter case-insensitive second character.
        char letter2 = Character.toUpperCase(input.next().charAt(0));
        if (!(letter2 >= 'A' && letter2 <= 'Z')) {
            System.out.println("Error, invalid character.");
            System.exit(1);
        }

        String guess = String.valueOf(letter1).concat(String.valueOf(letter2));
        char temp = 0;
        String word = "";
        int i = 0;
        while (i < 5) {
            temp = (char) ((int) ('A') + (int) (Math.random() * 2));
            word = word.concat(String.valueOf(temp));
            i++;
        }

        if (guess.charAt(0) == word.charAt(0) &&
                guess.charAt(guess.length() - 1) == word.charAt(word.length() - 1)) {
            System.out.println("Both are correct.");
        } else if (guess.charAt(0) == word.charAt(0) &&
                !(guess.charAt(guess.length() - 1) == word.charAt(word.length() - 1))) {
            System.out.println("The first one is correct.");
        } else if (!(guess.charAt(0) == word.charAt(0)) &&
                guess.charAt(guess.length() - 1) == word.charAt(word.length() - 1)) {
            System.out.println("The second one is correct.");
        } else {
            System.out.println("Both are wrong.");
        }
        System.out.println("Generated sequence " + word);
    }
}
