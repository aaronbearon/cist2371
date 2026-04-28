package aaronbearon.chapter18.interview;

import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 9 Part 1-2
 * Description: Print the sum of the user's amount of natural numbers starting with 1 with recursion.
 */
public class J209_12 {
    public static void main(String[] args) {
        printSum(getNumsCount());
    }

    // Helper method to print an = before the final answer.
    public static void printSum(int amount) {
        System.out.println(getSum(amount, " = "));
    }

    // The real method to get numbers and print properly.
    public static int getSum(int amount, String symbol) {
        int sumNext;
        if (amount > 1) {
            // Ensures a + instead of = is printed on subsequent calls.
            sumNext = amount + getSum(amount - 1, "+");
        } else {
            sumNext = amount;
        }

        System.out.print(amount + symbol);
        return sumNext;
    }

    public static int getNumsCount() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("How many natural numbers do you want to add up? ");
                int size = input.nextInt();
                if (size < 1) {
                    throw new Exception();
                }
                return size;
            } catch (Exception e) {
                System.out.println("Error, please enter a positive integer.");
            }
        }
    }
}

/*

Just like in part 1-1, I validated input and got the same output.
This time, I used recursion.

The helper method passes " = " meant to be printed at the end before the final sum.
Every recursive call passes "+" instead.
Once the base case is reached, output occurs.

*/
