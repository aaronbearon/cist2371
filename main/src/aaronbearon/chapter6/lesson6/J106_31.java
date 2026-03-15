package aaronbearon.chapter6.lesson6;

import java.util.Scanner;

/**
 * Compute sales totals.
 * Aaron Blum, Java Lesson 6.
 */
public class J106_31 {
    public static void main(String[] args) {
        printInstructions();
        Scanner input = new Scanner(System.in);
        int totalPrice = inputAndSumProducts(input);
        System.out.printf("The total sales of all the items were $%.2f!%n", totalPrice / 100.0);
    }

    /** Give instructions to user. */
    private static void printInstructions() {
        System.out.println("Below, you'll see a table with the product number and it's price.");
        System.out.printf("#1: $2.98%n#2: $4.50%n#3: $9.98%n#4: $4.49%n#5: $6.87%n");
        System.out.println("Please enter each product # (1-5) and the number of times it sold");
        System.out.println("Press 0 to quit.");
    }

    /** Input each product until the user quits. */
    private static int inputAndSumProducts(Scanner input) {
        // Loop until done, adding up goods sold.
        int totalPrice = 0;
        while (true) {
            int itemPrice = inputNextProduct(input);
            if (itemPrice < 0) {
                // sentinel exit value
                return totalPrice;
            }
            totalPrice += itemPrice;
        }
    }

    /** Input the next product and return the total price for this item and quantity. */
    private static int inputNextProduct(Scanner input) {
        System.out.print("Product number: ");

        // Retail product (Not multiplication).
        int product = inputProduct(input);
        if (product == 0) {
            // sentinel exit value
            return -1;
        }

        // How many?
        System.out.print("Items sold: ");
        int count = inputCount(input);

        // Do the math.
        int price = getCurrentPrice(product);
        int itemPrice = price * count;
        System.out.printf("That's $%.2f each. That product sold for $%.2f!%n",
                price / 100.0, itemPrice / 100.0);
        return itemPrice;
    }

    /** Lookup the price for the given item. */
    private static int getCurrentPrice(int item) {
        switch (item) {
            case 1:
                return 298;
            case 2:
                return 450;
            case 3:
                return 998;
            case 4:
                return 449;
            case 5:
                return 687;
            default:
                System.out.println("Unknown product!");
        }

        return 0;
    }

    /** Input and validate product number 1-5. */
    private static int inputProduct(Scanner input) {
        while (true) {
            int num = input.nextInt();
            if (num >= 0 && num <= 5) {
                return num;
            }

            System.out.println("Error, invalid input, try again.");
        }
    }

    /** Input and validate count, >= 0. */
    private static int inputCount(Scanner input) {
        while (true) {
            int num = input.nextInt();
            if (num >= 0) {
                return num;
            }

            System.out.println("Error, invalid input, try again.");
        }
    }
}
