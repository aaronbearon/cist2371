package aaronbearon.finals;

import java.util.Scanner;

public class J1fp_2 {
    /**
     * Checks out a certain amount of a specific item and prints results
     *
     * @param args N/A
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        RetailItem item = new RetailItem("Shirt", 20, 24.95);
        CashRegister register = new CashRegister(item);
        System.out.printf("Each %s costs $%.2f. How many do you want? ", item.getDescription(), item.getPrice());
        register.setQuantity(input.nextInt());

        // Prevents overbuying items that don't exist
        if (register.getQuantity() > item.getUnitsOnHand()) {
            System.out.printf("We only have %d of those. At least you can buy what's left.%n", item.getUnitsOnHand());
            register.setQuantity(item.getUnitsOnHand());
        }

        // Prints results
        System.out.printf("%nSALES RECEIPT%n");
        System.out.printf("Unit Price: $%.2f%n", item.getPrice());
        System.out.printf("Quantity: %d%n", register.getQuantity());
        System.out.printf("Subtotal: $%.2f%n", register.getSubtotal());
        System.out.printf("Sales Tax: $%.2f%n", register.getTax());
        System.out.printf("Total: $%.2f%n", register.getTotal());
    }
}
