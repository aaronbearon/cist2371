package aaronbearon.finals;

public class J1fp_1 {
    /**
     * Hard-coded sample data for the RetailItem class
     */
    private static final String[] NAMES = {"Jacket", "Designer Jeans", "Shirt"};
    private static final int[] COUNTS = {12, 40, 20};
    private static final double[] PRICES = {59.95, 34.95, 24.95};

    /**
     * Stores the data in the item objects and print them
     *
     * @param args N/A
     */
    public static void main(String[] args) {
        System.out.printf("Inventory:%n%n");
        RetailItem[] items = new RetailItem[3];
        for (int i = 0; i < 3; i++) {
            items[i] = new RetailItem();
            items[i].setDescription(NAMES[i]);
            items[i].setUnitsOnHand(COUNTS[i]);
            items[i].setPrice(PRICES[i]);
            System.out.printf("%s count: %d, $%.2f each.%n",
                    items[i].getDescription(), items[i].getUnitsOnHand(), items[i].getPrice());
        }
    }
}
