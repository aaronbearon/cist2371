package aaronbearon.chapter4.lesson4;

import java.util.Scanner;

public class J104_5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter two (x, y) points on a graph,"
                + " and I'll show you the distance between them.");
        System.out.print("x1: ");
        double x1 = input.nextDouble();
        System.out.print("y1: ");
        double y1 = input.nextDouble();
        System.out.print("x2: ");
        double x2 = input.nextDouble();
        System.out.print("y2: ");
        double y2 = input.nextDouble();
        double distance = Math.pow(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2), 0.5);
        System.out.printf("The distance between (" + x1 + ", " + y1 +
                ") and (" + x2 + ", " + y2 + ") is %.2f.\n", distance);
        System.out.printf("Integer part: (%d)\n", (int) distance);
        String category;
        if (distance < 5) {
            category = "Short";
        } else if (distance <= 15) {
            category = "Medium";
        } else {
            category = "Long";
        }
        System.out.printf("Category: %s Distance\n", category);
    }
}
