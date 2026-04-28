package aaronbearon.chapter9.lesson9;

/**
 * Aaron Blum
 * Java Lab Part 3
 * 2025-11-05
 */
public class J109_3 {
    public static void main(String[] args) {
        // Circle 1
        CircleWithPrivateDataFields c1 = new CircleWithPrivateDataFields(1);
        System.out.printf("Circle 1 Area: %.4f%n", c1.getArea());
        // Circle 2
        CircleWithPrivateDataFields c2 = new CircleWithPrivateDataFields(10);
        System.out.printf("Circle 2 Area: %.4f%n", c2.getArea());
        // Circle 3
        CircleWithPrivateDataFields c3 = new CircleWithPrivateDataFields(100);
        System.out.printf("Circle 3 Area: %.4f%n", c3.getArea());
    }
}

class CircleWithPrivateDataFields {
    private double radius;

    // Default constructor
    CircleWithPrivateDataFields() {
    }

    // Constructor for non-default radius
    CircleWithPrivateDataFields(double r) {
        radius = r;
    }

    double getRadius() {
        return radius;
    }

    double getCircumference() {
        return 2 * Math.PI * radius;
    }

    // Getter method for area
    double getArea() {
        return Math.PI * radius * radius;
    }
}
