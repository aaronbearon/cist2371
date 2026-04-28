package aaronbearon.chapter13.lesson13.problem5;

import java.math.BigInteger;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 4 Part 5
 * Description: Print out the product of all multiplies of 3
 * in range of 3 through 99.
 */
public class J204_5 {
    public static void main(String[] args) {
        BigInteger product = BigInteger.ONE;
        for (int i = 3; i <= 99; i += 3) {
            product = product.multiply(BigInteger.valueOf(i));
        }

        // Used to accurately place commas in large numbers.
        System.out.printf("%,d%n", product);
        System.out.println("That's more than 10^"
                + (((String.valueOf(product)).length() - 1))
                + "!");
    }
}

/*

Create a product variable of BigInteger type and assign it to BigInteger.ONE (or 1).
Use for loop to multiply 3x6x9...x99.
Print out the answer with commas placed appropriately.

*/
