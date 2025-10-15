package aaronbearon.chapter6.lesson6;

/**
 * Compute number of months to double principal.
 * Aaron Blum, Java Lesson 6.
 */
public class J106_2 {
    /** starting money */
    static final double principal = 100000.0;
    /** monthly interest rate */
    static final double interest = 0.03;

    public static void main(String[] args) {
        System.out.println("Your principle is $" + principal);
        printResults(getMonthsPrintAmounts(principal));
    }

    /** Compute how many months it will take to double the principle. */
    private static int getMonthsPrintAmounts(double principal) {
        double current = principal;
        for (int months = 1; true; months++) {
            double next = nextMonthBalance(current);
            printProgress(months, current, next);
            current = next;
            if (current >= principal * 2) {
                return months;
            }
        }
    }

    /** Compute next month's balance from current balance. */
    private static double nextMonthBalance(double current) {
        // Real banks force balance to the nearest penny each month.
        double nextPennies = Math.round(100 * current * (1 + interest));
        return nextPennies / 100;
    }

    /** Print running total so far. */
    private static void printProgress(int months, double previous, double next) {
        System.out.printf("After %d months, your total will be %d%% more than $%.2f... = $%.2f%n",
                months, (int) (interest * 100), previous, next);
    }

    /** Print the final results. */
    private static void printResults(int months) {
        System.out.println("=============================================");
        System.out.println("Your principal will be doubled in " + months + " months.");
    }
}
