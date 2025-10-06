package aaronbearon.midterm;

import java.util.Scanner;

public class j1mp_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Below, you'll see a table with the product number and it's price.");
        System.out.printf("#1: $2.98%n#2: $4.50%n#3: $9.98%n#4: $4.49%n#5: $6.87%n");
        System.out.println("Please enter each product # (1-5) and the number of times it sold");
        System.out.println("Press 0 to quit.");
        int totalPrice = 0;
        int count;
        int num;
        while (true) {
            int currentPrice = 0;
            System.out.print("Product number: ");
            while (true) {
                num = input.nextInt();
                if (num >= 0 && num <= 5) {
                    break;
                }

                System.out.println("Error, invalid input, try again.");
            }

            if (num == 0) {
                break;
            }

            System.out.print("Items sold: ");
            while (true) {
                count = input.nextInt();
                if (count >= 0) {
                    break;
                }

                System.out.println("Error, invalid input, try again.");
            }

            switch (num) {
                case 1:
                    currentPrice += 298;
                    break;
                case 2:
                    currentPrice += 450;
                    break;
                case 3:
                    currentPrice += 998;
                    break;
                case 4:
                    currentPrice += 449;
                    break;
                case 5:
                    currentPrice += 687;
                    break;
                default:
                    System.out.println("Unknown error!");
            }

            System.out.printf("That's $%.2f each. That item sold for $%.2f!%n",
                    currentPrice / 100.0, currentPrice * count / 100.0);
            totalPrice += currentPrice * count;
        }

        System.out.printf("The total sales of all the items were $%.2f!%n", totalPrice / 100.0);
    }
}
