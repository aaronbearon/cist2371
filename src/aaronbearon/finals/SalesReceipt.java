package aaronbearon.finals;

import java.io.OutputStream;
import java.io.PrintStream;

public class SalesReceipt {
    private final RetailItem item;
    private final int quantity;
    private final double subtotal;
    private final double tax;
    private final double total;

    /**
     * Sets the class fields for output
     *
     * @param item Item object
     * @param quantity Number of items in object
     * @param subtotal Quantity multiplied by item price
     * @param tax 6% of the subtotal
     * @param total subtotal + tax
     */
    public SalesReceipt(RetailItem item, int quantity, double subtotal, double tax, double total) {
        this.item = item;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
    }

    /**
     * Writes a sales receipt to the console or output file
     *
     * @param out Output destination (whether screen or file)
     */
    public void printTo(OutputStream out) {
        PrintStream ps = new PrintStream(out);
        ps.printf("SALES RECEIPT%n");
        ps.printf("Unit Price: $%.2f%n", item.getPrice());
        ps.printf("Quantity: %d%n", quantity);
        ps.printf("Subtotal: $%.2f%n", subtotal);
        ps.printf("Sales Tax: $%.2f%n", tax);
        ps.printf("Total: $%.2f%n", total);
        ps.flush();
    }
}
