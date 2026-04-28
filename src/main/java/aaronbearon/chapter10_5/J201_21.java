package aaronbearon.chapter10_5;

/**
 * CIST 2372 Java 2, CRN 49043
 * Aaron Blum
 * Interview 1 Problem 2-1
 * Title: Sum of even series
 * Description:
 * Add up all the even numbers
 * inclusively between 10 and 100.
 */
public class J201_21 {
    public static void main(String[] args) {
        // Total sum starts at 0
        int sum = 0;

        // Includes only evens between 10 and 100 inclusively
        for (int i = 10; i <= 100; i += 2) {
            sum += i;
        }

        // Specifies number's purpose
        System.out.println("Sum: " + sum);
    }
}

/*
 * Used for loop to handle the series appropriately.
 * Declared the sum outside the loop to print later.
 * Specified starting and ending number in loop header.
 * Specified number increase in loop header.
 */
