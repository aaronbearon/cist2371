package aaronbearon.chapter12;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 3
 * Description:
 * Get two integers from the user for division.
 * Handle the division by 0 ArithmeticException in a catch block.
 */
public class J203_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter two integers for division.");
        while (true) {
            try {
                System.out.print("Dividend: ");
                int a = input.nextInt();
                System.out.print("Divisor: ");
                int b = input.nextInt();

                // Integer division here.
                // Floating point division doesn't raise an exception.
                System.out.println("Answer: " + (a / b));
                break;
            } catch (ArithmeticException e) {
                System.out.println("You can't divide by zero!");

                // Handle non-number inputs.
            } catch (InputMismatchException e) {
                System.out.println("They must be integers!");
            }

            // Use up the rest of the line so that next iteration doesn't read past values.
            input.nextLine();
        }
    }
}

/*
 * Used while loop to make user enter valid numbers without division by 0.
 * Ended the try block with a break statement.
 * Used another catch for malformed input.
 */
