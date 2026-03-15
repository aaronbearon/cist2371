package aaronbearon.chapter11.interview;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 2
 * Description:
 * Build the Rectangle class with appropriate data and methods.
 * Test it with fields and code in the public class.
 */
public class J202_1 {
    // Test data for Rectangle r
    private static final String name = "Aaron";
    private static final double width = 8, height = 7;

    public static void main(String[] args) {
        // Combines two printing lines into one statement.
        System.out.println("Width: " + width + "\r\nHeight: " + height);

        // Creates the rectangle with the public class data.
        Rectangle r = new Rectangle(name, width, height);
        System.out.println("Area: " + r.area());
    }
}

/** Default implementation of shape class from instructions */
class Shape {
    private final String creator;

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

/** Rectangle subclass based on Shape superclass */
class Rectangle extends Shape {
    private final double width;
    private final double height;

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

/*
 * Set the name, width, and height as private final fields above the main method.
 * Combined two printing fields with one line each into the same println statement.
 */
