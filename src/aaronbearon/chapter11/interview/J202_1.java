package aaronbearon.chapter11.interview;

public class J202_1 {
    private static final String name = "Aaron";
    private static final double width = 8, height = 7;

    public static void main(String[] args) {
        System.out.println("Width: " + width + "\r\nHeight: " + height);
        Rectangle r = new Rectangle(name, width, height);
        System.out.println("Area: " + r.area());
    }
}

class Shape {
    private String creator;

    public Shape(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return creator;
    }

    public double area() {
        System.out.println("Write code to calculate the area for each shape");
        return 0.0; // default implementation
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this("None", width, height);
    }

    public Rectangle(String creator, double width, double height) {
        super(creator);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}
