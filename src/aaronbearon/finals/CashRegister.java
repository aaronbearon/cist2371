package aaronbearon.finals;

public class CashRegister {
    /**
     * Stores the item type and the quantity
     */
    private final RetailItem item;
    private int quantity;

    /**
     * Creates a register with a certain item type and quantity
     *
     * @param item     Item object
     * @param quantity Non-negative number of items in object sold
     */
    public CashRegister(RetailItem item, int quantity) {
        this.item = item;
        this.quantity = Math.max(quantity, 0);
    }

    /**
     * Assigns number of items in object sold
     *
     * @param quantity Non-negative number of items in object sold
     */
    void setQuantity(int quantity) {
        this.quantity = Math.max(quantity, 0);
    }

    /**
     * Returns the item count
     *
     * @return Item count
     */
    int getQuantity() {
        return quantity;
    }

    /**
     * Returns the combined cost of all the items of the same type
     *
     * @return base price of the items
     */
    public double getSubtotal() {
        return item.getPrice() * quantity;
    }

    /**
     * Returns 6% of the subtotal
     *
     * @return 6% of the base price of the items
     */
    public double getTax() {
        return this.getSubtotal() * 0.06;
    }

    /**
     * Returns the subtotal plus the tax
     *
     * @return subtotal + tax
     */
    public double getTotal() {
        return this.getSubtotal() + this.getTax();
    }

    public SalesReceipt getReceipt() {
        return new SalesReceipt(item, getQuantity(), getSubtotal(), getTax(), getTotal());
    }
}
