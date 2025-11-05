package aaronbearon.chapter9.lesson9;

/**
 * Aaron Blum
 * Java Lab Part 3
 * 2025-11-05
 */
public class J109_3 {
    public static void main(String[] args) {
        CircleWithPrivateDataFields c1 = new CircleWithPrivateDataFields(1);
        System.out.printf("Circle 1 Area: %.4f%n", c1.getArea());
        CircleWithPrivateDataFields c2 = new CircleWithPrivateDataFields(10);
        System.out.printf("Circle 2 Area: %.4f%n", c2.getArea());
        CircleWithPrivateDataFields c3 = new CircleWithPrivateDataFields(100);
        System.out.printf("Circle 3 Area: %.4f%n", c3.getArea());
    }
}

class CircleWithPrivateDataFields {
    private double radius;

    CircleWithPrivateDataFields() {
    }

    CircleWithPrivateDataFields(double r) {
        radius = r;
    }

    double getRadius() {
        return radius;
    }

    double getCircumference() {
        return 2 * Math.PI * radius;
    }

    double getArea() {
        return Math.PI * radius * radius;
    }
}
