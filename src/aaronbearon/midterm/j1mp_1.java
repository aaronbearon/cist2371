package aaronbearon.midterm;

import java.util.Scanner;

public class j1mp_1 {
    public static void main(String[] args) {
        // Give instructions to user.
        Scanner input = new Scanner(System.in);
        System.out.println("Below, you'll see a table with the product number and it's price.");
        System.out.printf("#1: $2.98%n#2: $4.50%n#3: $9.98%n#4: $4.49%n#5: $6.87%n");
        System.out.println("Please enter each product # (1-5) and the number of times it sold");
        System.out.println("Press 0 to quit.");

        // Loop until done, adding up goods sold.
        int totalPrice = 0;
        while (true) {
            System.out.print("Product number: ");

            // Retail product (Not multiplication).
            int product = inputProduct(input);
            if (product == 0) {
                break;
            }

            // Determine product price.
            int currentPrice;
            switch (product) {
                case 1:
                    currentPrice = 298;
                    break;
                case 2:
                    currentPrice = 450;
                    break;
                case 3:
                    currentPrice = 998;
                    break;
                case 4:
                    currentPrice = 449;
                    break;
                case 5:
                    currentPrice = 687;
                    break;
                default:
                    System.out.println("Unknown error!");
                    System.exit(1);
                    return;
            }

            // How many?
            System.out.print("Items sold: ");
            int count = inputCount(input);

            // Do the math.
            int itemPrice = currentPrice * count;
            System.out.printf("That's $%.2f each. That product sold for $%.2f!%n",
                    currentPrice / 100.0, itemPrice / 100.0);
            totalPrice += itemPrice;
        }

        System.out.printf("The total sales of all the items were $%.2f!%n", totalPrice / 100.0);
    }

    // Input and validate product number 1-5.
    private static int inputProduct(Scanner input) {
        while (true) {
            int num = input.nextInt();
            if (num >= 0 && num <= 5) {
                return num;
            }

            System.out.println("Error, invalid input, try again.");
        }
    }

    // Input and validate count, >= 0.
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
