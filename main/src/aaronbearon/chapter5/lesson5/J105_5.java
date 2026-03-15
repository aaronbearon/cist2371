package aaronbearon.chapter5.lesson5;

import java.util.Scanner;

/**
 Lab 5, Part 5 - Palindrome Checker
 Aaron Blum
 */
public class J105_5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a word, is it a palindrome? Press 0 to exit.");
        while (true) {

            // Get input and check for sentinel value.
            System.out.print("palindrome? ");
            String s = input.nextLine();
            if (s.equals("0")) {
                break;
            }

            // Filter the input to only include uppercase and lowercase letters.
            String filter = "";
            for (int ch = 0; ch < s.length(); ch++) {
                if ((s.charAt(ch) >= 'A' && s.charAt(ch) <= 'Z') || (s.charAt(ch) >= 'a' && s.charAt(ch) <= 'z')) {
                    filter += s.charAt(ch);
                }
            }
            s = filter.toLowerCase();

            // Loop through to check whether it's a palindrome.

            // Examples:
            // taco cat / Step on no pets. / Was it a cat I saw?
            int low = 0;
            int high = s.length() - 1;
            boolean isPalindrome = true;
            while (low < high) {
                if (s.charAt(low) != s.charAt(high)) {
                    isPalindrome = false;
                    break;
                }
                low++;
                high--;
            }

            // Display whether it's a palindrome.
            if (isPalindrome) {
                System.out.println("Yes!");
            } else {
                System.out.println("No.");
            }

            System.out.println("=======================================================");
        }

        System.out.println("Thank you!");
    }
}
