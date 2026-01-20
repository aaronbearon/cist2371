package aaronbearon.finals1;

/**
 * Aaron Blum
 * Java Final Project RetailItem
 * 2025-11-27
 */
public class RetailItem {
    /**
     * Stores the name, count, and price of each item type
     */
    private final String description;
    private int unitsOnHand;
    private final double price;

    /**
     * Creates a new type of item with the fields below
     *
     * @param description Item name
     * @param unitsOnHand Non-negative item count
     * @param price       Non-negative price
     */
    public RetailItem(String description, int unitsOnHand, double price) {
        this.description = description;
        this.unitsOnHand = Math.max(unitsOnHand, 0);
        this.price = Math.max(price, 0);
    }

    /**
     * Assigns total number of items of one type
     *
     * @param unitsOnHand Non-negative item count from user
     */
    public void setUnitsOnHand(int unitsOnHand) {
        this.unitsOnHand = Math.max(unitsOnHand, 0);
    }

    /**
     * Returns the item name
     *
     * @return Item name
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the item count
     *
     * @return Item count
     */
    public int getUnitsOnHand() {
        return unitsOnHand;
    }

    /**
     * Returns the item price
     *
     * @return Item price
     */
    public double getPrice() {
        return price;
    }
}
