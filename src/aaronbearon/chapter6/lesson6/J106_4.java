package aaronbearon.chapter6.lesson6;

import java.util.Scanner;

/**
 * Check whether input is palindrome.
 * Aaron Blum, Java Lab 6.
 */
public class J106_4 {
    public static void main(String[] args) {
        System.out.print("Enter a word, is it a palindrome? ");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        s = delPunctuation(s);
        String r = reverse(s);

        System.out.println(s);
        System.out.println(r);
        s = delSpaces(s);
        r = reverse(s);

        System.out.println(s);
        System.out.println(r);

        // Display whether it's a palindrome.
        boolean isPalindrome = equalsCaseInsensitive(s, r);
        if (isPalindrome) {
            System.out.println("Yes!");
        } else {
            System.out.println("No.");
        }
    }

    /** Filter the input to only include spaces, uppercase, and lowercase letters. */
    private static String delPunctuation(String s) {
        String filter = "";
        for (int ch = 0; ch < s.length(); ch++) {
            if ((s.toLowerCase().charAt(ch) >= 'a' && s.toLowerCase().charAt(ch) <= 'z') || (s.charAt(ch) == ' ')) {
                filter += s.charAt(ch);
            }
        }

        return filter;
    }

    /** Filter the input to remove spaces */
    private static String delSpaces(String s) {
        String filter = "";
        for (int ch = 0; ch < s.length(); ch++) {
            if (s.charAt(ch) != ' ') {
                filter += s.charAt(ch);
            }
        }

        return filter;
    }

    /** Returns the reversed string */
    private static String reverse(String s) {
        String filter = "";
        for (int ch = 1; ch <= s.length(); ch++) {
            filter += s.charAt(s.length() - ch);
        }

        return filter;
    }

    /** Returns true if the strings are equal by case insenstive rules. */
    private static boolean equalsCaseInsensitive(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        if (a.length() != b.length()) {
            return false;
        }

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
