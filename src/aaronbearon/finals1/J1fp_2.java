package aaronbearon.finals1;

import java.util.Scanner;

/**
 * Aaron Blum
 * Java Final Project Part 2
 * 2025-11-27
 */
public class J1fp_2 {
    /**
     * Program to buy a certain amount of a specific item.
     */
    public static void main(String[] args) {
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

        // Prints results
        System.out.printf("SALES RECEIPT%n");
        System.out.printf("Unit Price: $%.2f%n", item.getPrice());
        System.out.printf("Quantity: %d%n", register.getQuantity());
        System.out.printf("Subtotal: $%.2f%n", register.getSubtotal());
        System.out.printf("Sales Tax: $%.2f%n", register.getTax());
        System.out.printf("Total: $%.2f%n", register.getTotal());
    }
}
