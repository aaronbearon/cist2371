package aaronbearon.chapter18.lesson9;

import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 9 part 5
 * Description: Write and test the palindrome checker with recursion, without copying the textbook.
 */
public class J209_5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a word and I'll tell you if it's a palindrome: ");
        if (isPalindrome(input.nextLine())) {
            System.out.println("It's a palindrome!");
        } else {
            System.out.println("Not a palindrome.");
        }
    }

    // Recursive helper method to define low and high
    public static boolean isPalindrome(String key) {
        if (key.isEmpty()) {
            return true;
        }

        //* Start at middle and go outward.
        int low = (key.length() - 1) / 2;
        int high = (key.length() - 1) - low;
        return isPalindrome(key, low, high);
    }

    /**
     * Check the string from the middle and gradually go outward to see if it's a palindrome.
     *
     * @param key  the string to check
     * @param low  the char at the lowest index of current string portion
     * @param high the char at the highest index of current string portion
     * @return false, with mismatched chars
     */
    public static boolean isPalindrome(String key, int low, int high) {
        if (low < 0) {
            return true;
        }

        if (key.charAt(low) == key.charAt(high)) {
            //* Switch the increase and decrease for going middle to ends.
            return isPalindrome(key, low - 1, high + 1);
        } else {
            return false;
        }
    }
}

/*

The textbook implementation starts at the ends of the string going inward.
Mine starts at the middle of the string and goes outward.

*/
