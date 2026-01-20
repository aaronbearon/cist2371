package aaronbearon.finals1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Aaron Blum
 * Java Final Project Part 3
 * 2025-11-27
 */
public class J1fp_3 {
    /**
     * Program to buy a certain amount of a specific item.
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        RetailItem item = J1fp_1.INVENTORY[2]; // Shirts
        CashRegister register = new CashRegister(item, 0);
        System.out.printf("Each %s costs $%.2f. How many do you want? ", item.getDescription(), item.getPrice());
        register.setQuantity(input.nextInt());

        // Prevents overbuying items that don't exist
        if (register.getQuantity() > item.getUnitsOnHand()) {
            System.out.printf("We only have %d of those. At least you can buy what's left.%n", item.getUnitsOnHand());
            register.setQuantity(item.getUnitsOnHand());
        }

        item.setUnitsOnHand(item.getUnitsOnHand() - register.getQuantity());

        SalesReceipt receipt = register.getReceipt();

        // Prints receipt to a file
        // Auto-closes the file
        try (FileOutputStream fileOutputStream = new FileOutputStream("salesReceipt.txt")) {
            System.out.println("Printing receipt...");
            receipt.printTo(fileOutputStream);
            System.out.println("Done!");
        }

        // Also prints to stdout.
        receipt.printTo(System.out);
    }
}
