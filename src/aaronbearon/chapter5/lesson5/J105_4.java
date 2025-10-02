package aaronbearon.chapter5.lesson5;

/**
 Lab 5, Part 4 - Bank account
 Aaron Blum
 */
public class J105_4 {
    public static void main(String[] args) {
        // Starting money and current money.
        double startAmount = 100000d;
        double currentAmount = 100000d;
        System.out.println("Your principle is $" + startAmount);
        int months = 0; // need to count the months

        // Each iteration computes from the initial amount to avoid rounding errors over time.
        while (currentAmount >= 50000) {
            months++;
            double prevAmount = currentAmount;
            currentAmount = startAmount * Math.pow(0.95, months);
            // Show progress.
            System.out.printf("After %d months, your total will be $%.2f = $%.2f (1 - 5%%)%n",
                    months, currentAmount, prevAmount);
        }

        // Final answer.
        System.out.println("=============================================");
        System.out.println("Your principal will be halved in " + months + " months.");
    }
}
