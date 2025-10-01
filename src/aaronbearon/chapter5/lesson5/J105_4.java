package aaronbearon.chapter5.lesson5;

public class J105_4 {
    public static void main(String[] args) {
        double startAmount = 100000d;
        double currentAmount = 100000d;
        System.out.println("Your principle is " + startAmount);
        int months = 0;

        // The math in each iteration goes directly from the initial Amount and only rounds the displayed answer.
        while (currentAmount >= 50000) {
            months++;
            double prevAmount = currentAmount;
            currentAmount = startAmount * Math.pow(0.95, months);
            System.out.printf("After %d months, your total will be $%.2f = $%.2f (1 - 5%%)",
                    months, currentAmount, prevAmount);
            System.out.println();
        }

        System.out.println("=============================================");
        System.out.println("Your principal will be halved in " + months + " months.");
    }
}
