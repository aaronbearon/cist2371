package aaronbearon.chapter9;

public class Test {
    public static void main(String[] args) {
        Circle c1 = new Circle(1);
        System.out.println();
        System.out.println("The radius of circle 1 is " + c1.getRadius());
        System.out.println("The circumference of circle 1 is " + c1.getCircumference());
        System.out.println("The area of circle 1 is " + c1.getArea());
        Circle c2 = new Circle(10);
        System.out.println();
        System.out.println("The radius of circle 2 is " + c2.getRadius());
        System.out.println("The circumference of circle 2 is " + c2.getCircumference());
        System.out.println("The area of circle 2 is " + c2.getArea());
        Circle c3 = new Circle(100);
        System.out.println();
        System.out.println("The radius of circle 3 is " + c3.getRadius());
        System.out.println("The circumference of circle 3 is " + c3.getCircumference());
        System.out.println("The area of circle 3 is " + c3.getArea());
    }
}

class Circle {
    private double radius;

    Circle() {
    }

    Circle(double r) {
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
