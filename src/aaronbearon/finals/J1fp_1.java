package aaronbearon.finals;

public class J1fp_1 {
    /**
     * Hard-coded sample data for the RetailItem class
     */
    public static final RetailItem[] INVENTORY = {
            new RetailItem("Jacket", 12, 59.95),
            new RetailItem("Designer Jeans", 40, 34.95),
            new RetailItem("Shirt", 20, 24.95)
    };

    /**
     * Prints out the store inventory
     */
    public static void main(String[] args) {
        System.out.printf("Inventory:%n%n");
        for (RetailItem retailItem : INVENTORY) {
            System.out.printf("%s count: %d, $%.2f each.%n",
                    retailItem.getDescription(), retailItem.getUnitsOnHand(), retailItem.getPrice());
        }
    }
}
