package aaronbearon.chapter18.lesson9;

import java.util.Scanner;

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

    public static boolean isPalindrome(String key) {
        if (key.isEmpty()) {
            return true;
        }

        int low = (key.length() - 1) / 2;
        int high = (key.length() - 1) - ((key.length() - 1) / 2);
        return isPalindrome(key, low, high);
    }

    public static boolean isPalindrome(String key, int low, int high) {
        if (low < 0) {
            return true;
        }

        if (key.charAt(low) == key.charAt(high)) {
            return isPalindrome(key, low - 1, high + 1);
        } else {
            return false;
        }
    }
}

/*
TODO
*/
