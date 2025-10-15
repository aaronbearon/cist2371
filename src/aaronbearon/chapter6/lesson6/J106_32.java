package aaronbearon.chapter6.lesson6;

import java.util.Scanner;

/**
 * Compute checking/savings account balance.
 * Aaron Blum, Java Lesson 6.
 */
public class J106_32 {
    public static void main(String[] args) {
        printInstructions();

        // Input all the data.
        Scanner input = new Scanner(System.in);
        System.out.print("Account #: ");
        int accountNumber = input.nextInt();
        String accountType = inputAccount(input);
        int minBalance = inputBalance("Minimum", input);
        int currentBalance = inputBalance("Current", input);

        // Do the math to compute next balance.
        double newBalance = getNewBalance(accountType, currentBalance, minBalance);

        printResults(accountNumber, accountType, currentBalance, newBalance);
    }

    /** Input and validate account, "S" or "C" */
    private static String inputAccount(Scanner input) {
        System.out.println("Account type -");

        while (true) {
            System.out.print("type S for savings or C for checking: ");

            String in = input.next().toUpperCase();
            if (in.equals("S")) {
                return "Savings";
            }

            if (in.equals("C")) {
                return "Checking";
            }

            System.out.println("Error, invalid input, try again.");
        }
    }

    /** Input and validate balance, >= 0. */
    private static int inputBalance(String type, Scanner input) {
        while (true) {
            System.out.printf("%s balance: ", type);
            int num = input.nextInt();
            if (num >= 0) {
                return num;
            }

            System.out.println("Error, please enter non-negative integer.");
        }
    }

    /** Prompt the user with info. */
    private static void printInstructions() {
        System.out.println("Please fill in the following fields:");
        System.out.println("account #, account type, minimum balance, and current balance.");
    }

    /** Do the math to compute next balance. */
    private static double getNewBalance(String accountType, int currentBalance, int minBalance) {
        switch (accountType) {
            case "Savings":
                if (currentBalance >= minBalance) {
                    return currentBalance * 104 / 100.0;
                } else {
                    return currentBalance - 10;
                }

            case "Checking":
                if (currentBalance - 5000 > minBalance) {
                    return currentBalance * 105 / 100.0;
                } else if (currentBalance >= minBalance) {
                    return currentBalance * 103 / 100.0;
                } else {
                    return currentBalance - 25;
                }

            default:
                System.out.println("Unknown error!");
                System.exit(1);
                return 0;
        }
    }

    private static void printResults(int accountNumber, String accountType, int currentBalance, double newBalance) {
        // Pretty print nice results.
        System.out.println();
        System.out.println("Account #           |Account type        |Current Balance     |New balance         ");
        System.out.println("--------------------+--------------------+--------------------+--------------------");
        printPadded(String.valueOf(accountNumber));
        printPadded(accountType);
        printPadded("$" + currentBalance);
        System.out.printf("$%.2f", newBalance);

        System.out.println();
    }

    /** Print a cell of the grid padded out to the length pad. */
    private static void printPadded(String buf) {
        final int PAD = 20;
        String newBuf = buf;
        for (int i = 0; i < PAD - buf.length(); i++) {
            newBuf += " ";
        }
        System.out.print(newBuf + "|");
    }
}
