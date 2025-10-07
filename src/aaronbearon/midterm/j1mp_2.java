package aaronbearon.midterm;

import java.util.Scanner;

public class j1mp_2 {
    public static void main(String[] args) {
        // Prompt the user with info.
        Scanner input = new Scanner(System.in);
        System.out.println("Please fill in the following fields:");
        System.out.println("customer #, account type, minimum balance, and current balance.");

        // Input all the data.
        System.out.print("Customer #: ");
        int n = input.nextInt();
        System.out.println("Account type -");
        System.out.print("type S for savings or C for checking: ");
        String type;
        while (true) {
            String in = input.next().toUpperCase();
            if (in.equals("S")) {
                type = "Savings";
                break;
            }
            if (in.equals("C")) {
                type = "Checking";
                break;
            }
            System.out.println("Error, invalid input, try again.");
        }

        System.out.print("Minimum balance: ");
        int minBalance = input.nextInt();
        System.out.print("Current balance: ");
        int currentBalance = input.nextInt();

        // Do the math to compute next balance.
        double newBalance;
        switch (type) {
            case "Savings":
                if (currentBalance >= minBalance) {
                    newBalance = currentBalance * 104 / 100.0;
                } else {
                    newBalance = currentBalance - 10;
                }
                break;
            case "Checking":
                if (currentBalance - 5000 > minBalance) {
                    newBalance = currentBalance * 105 / 100.0;
                } else if (currentBalance >= minBalance) {
                    newBalance = currentBalance * 103 / 100.0;
                } else {
                    newBalance = currentBalance - 25;
                }
                break;
            default:
                System.out.println("Unknown error!");
                System.exit(1);
                return;
        }

        // Pretty print nice results.
        final int padding = 20;
        System.out.println();
        System.out.println("Customer #          |Account type        |Current Balance     |New balance         ");
        System.out.println("--------------------+--------------------+--------------------+--------------------");
        printPadded(padding, String.valueOf(n));
        printPadded(padding, type);
        printPadded(padding, "$" + currentBalance);
        System.out.printf("$%.2f", newBalance);

        System.out.println();
    }

    // Print a cell of the grid padded out to the length pad.
    public static void printPadded(int pad, String buf) {
        String newBuf = buf;
        for (int i = 0; i < pad - buf.length(); i++) {
            newBuf += " ";
        }
        System.out.print(newBuf + "|");
    }
}
