package aaronbearon.chapter4.lesson4;

import java.util.Scanner;

/**
 * Lab 4, Part 5 - distance of points
 * Aaron Blum
 */
public class J104_5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter two (x, y) points on a graph,"
                + " and I'll show you the distance between them.");

        // Get the two pairs of (x, y) values from the user.
        System.out.print("x1: ");
        double x1 = input.nextDouble();
        System.out.print("y1: ");
        double y1 = input.nextDouble();
        System.out.print("x2: ");
        double x2 = input.nextDouble();
        System.out.print("y2: ");
        double y2 = input.nextDouble();

        System.out.println("=======================================");

        // Store the x distance and y distance between points in variables.
        // They will also (x2, y2) relative to (x1, y1).
        double dx = x2 - x1;
        double dy = y2 - y1;

        // Use the distance formula. Format the output including the integer part.
        double distance = Math.sqrt(Math.pow((dx), 2) + Math.pow((dy), 2));
        System.out.printf("The distance between (%.2f, %.2f) and (%.2f, %.2f) is %.2f.%n", x1, y1, x2, y2, distance);
        System.out.printf("Integer part: (%d)%n", (int) distance);

        // Find out the category.
        String category;
        if (distance < 5) {
            category = "Short";
        } else if (distance <= 15) {
            category = "Medium";
        } else {
            category = "Long";
        }
        System.out.printf("Category: %s Distance.%n", category);

        // Get the quadrant (or part of the axis if necessary).
        System.out.println("Relative to point (x1, y1):");
        if (dx != 0 && dy != 0) {
            // The point is in one of the four quadrants.
            System.out.printf("Point (x2, y2) is in Quadrant %s.%n", getQuadrant(dx, dy));
        } else if (dx != 0 || dy != 0) {
            // The point is on an axis so we need one of the four directions from the origin.
            System.out.printf("Point (x2, y2) is on the %s axis.%n", getDirAndAxis(dx, dy));
        } else {
            // Origin.
            System.out.println("Point (x2, y2) is the origin.");
        }
    }

    // Returns the point's quadrant.
    public static String getQuadrant(double x, double y) {
        if (x > 0 && y > 0) {
            return "I";
        } else if (x > 0) {
            return "IV";
        } else if (y > 0) {
            return "II";
        } else {
            return "III";
        }
    }

    // Returns the point's axis and direction if there's no quadrant.
    public static String getDirAndAxis(double x, double y) {
        if (x > 0) {
            return "positive x";
        } else if (x < 0) {
            return "negative x";
        } else if (y > 0) {
            return "positive y";
        } else if (y < 0) {
            return "negative y";
        } else {
            return "error: should not get here";
        }
    }
}
