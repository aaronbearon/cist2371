package aaronbearon.chapter10.interview;

public class J110_11 {
    public static void main(String[] args) {
        Circle circle = new Circle();
        for (double radius = 1; radius <= 100; radius *= 10) {
            System.out.printf("Circle Radius: %.0f Area: %.4f%n", circle.getRadius(), circle.getArea());
            circle.setRadius(radius * 10);
        }
    }
}

class Circle {
    private double radius;

    Circle() {
        this(1);
    }

    Circle(double radius) {
        this.radius = radius;
    }

    void setRadius(double radius) {
        this.radius = radius;
    }

    double getRadius() {
        return radius;
    }

    double getArea() {
        return radius * radius * Math.PI;
    }
}
