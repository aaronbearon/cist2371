package aaronbearon.chapter18.interview;

import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 9 Part 1-1
 * Description: Print the sum of the user's amount of natural numbers starting with 1 with a for loop.
 */
public class J209_11 {
    public static void main(String[] args) {
        int highest = getNumsCount();
        int sum = 0;
        for (int i = 1; i <= highest; i++) {
            System.out.print(i);
            if (i < highest) {
                System.out.print("+");
            } else {
                System.out.print(" = ");
            }

            sum += i;
        }

        System.out.println(sum);
    }

    /**
     * Ensure user enters positive number.
     *
     * @return non-zero int
     */
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

I validated the user's input.

I also printed appropriate symbols like the sample output. For example:
User entering 4 results in:
1+2+3+4 = 10

For this part, I used a for loop.

*/
