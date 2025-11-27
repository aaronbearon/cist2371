package aaronbearon.finals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class J1fp {
    /**
     * Combines the three parts into one program
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.printf("Inventory:%n%n");
        for (RetailItem retailItem : J1fp_1.INVENTORY) {
            System.out.printf("%s count: %d, $%.2f each.%n",
                    retailItem.getDescription(), retailItem.getUnitsOnHand(), retailItem.getPrice());
        }

        System.out.printf("%nWhich item do you want to buy? ");
        int choice = input.nextInt();
        if (choice < 1 || choice >= J1fp_1.INVENTORY.length) {
            System.out.println("Error, invalid choice.");
            System.exit(1);
        }

        RetailItem item = J1fp_1.INVENTORY[choice - 1]; // Jacket, Designer Jeans, or Shirt
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
