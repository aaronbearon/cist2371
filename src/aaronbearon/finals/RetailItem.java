package aaronbearon.finals;

public class RetailItem {
    /**
     * Stores the name, count, and price of each item type
     */
    private String description;
    private int unitsOnHand;
    private double price;

    /**
     * Creates a new type of item with default name "N/A"
     */
    public RetailItem() {
        this("N/A");
    }

    /**
     * Creates a new type of item with default price 0
     *
     * @param description Item name
     */
    public RetailItem(String description) {
        this(description, 0);
    }

    /**
     * Creates a new type of item with default count 0
     *
     * @param description Item name
     * @param price       Non-negative price
     */
    public RetailItem(String description, double price) {
        this(description, 0, price);
    }

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
     * Assigns name of the item type
     *
     * @param description Item name from user
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Assigns price of each item in specific type
     *
     * @param price Non-negative price from user
     */
    public void setPrice(double price) {
        this.price = Math.max(price, 0);
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
