package aaronbearon.midterm;

import java.util.Scanner;

public class j1mp_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please fill in the following fields:");
        System.out.println("customer #, account type, minimum balance, and current balance.");
        System.out.print("Customer #: ");
        int n = input.nextInt();
        System.out.println("Account type -");
        System.out.print("type S for savings or C for checking: ");
        String type;
        while (true) {
            type = input.next();
            if ((type.toUpperCase()).equals("S") || (type.toUpperCase()).equals("C")) {
                break;
            }

            System.out.println("Error, invalid input, try again.");
        }

        if ((type.toUpperCase()).equals("S")) {
            type = "Savings";
        }

        if ((type.toUpperCase()).equals("C")) {
            type = "Checking";
        }

        System.out.print("Minimum balance: ");
        int minBalance = input.nextInt();
        System.out.print("Current balance: ");
        int currentBalance = input.nextInt();
        int newBalance = 0;
        int percent = 0;
        switch (type) {
            case "Savings":
                if (currentBalance >= minBalance) {
                    percent = 4;
                } else {
                    newBalance = currentBalance - 10;
                }
                break;
            case "Checking":
                if (currentBalance - 5000 > minBalance) {
                    percent = 5;
                } else if (currentBalance >= minBalance) {
                    percent = 3;
                } else {
                    newBalance = currentBalance - 25;
                }
                break;
            default:
                System.out.println("Unknown error!");
        }

        System.out.println();
        System.out.println("Customer #          |Account type        |Current Balance     |New balance         ");
        System.out.println("--------------------+--------------------+--------------------+--------------------");
        format(n + "");
        format(type + "");
        format("$" + currentBalance + "");
        if (currentBalance >= minBalance) {
            System.out.printf("$%.2f", currentBalance * (100 + percent) / 100.0);
        } else {
            System.out.print("$" + newBalance);
        }

        System.out.println();
    }

    public static void format(String buf) {
        String newBuf = buf;
        for (int i = 0; i < 20 - buf.length(); i++) {
            newBuf += " ";
        }
        System.out.print(newBuf + "|");
    }
}
