package aaronbearon.chapter11.lesson11;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 2
 * Description: Use inheritance to link the classes correctly.
 * Build the classes with the corresponding data and methods.
 * Implement them to ensure that the test data works.
 */
public class J202_2 {
    public static void main(String[] args) {
        String creator = "Aaron";
        Circle circle = new Circle(creator, 10);
        System.out.println(circle.toString());
        System.out.println("===================================");
        Rectangle rectangle = new Rectangle(creator, 5, 2);
        System.out.println(rectangle.toString());
        System.out.println("===================================");
        Square square = new Square(creator, 5);
        System.out.println(square.toString());
        System.out.println("===================================");
    }
}

/** Blueprint for all Shape objects including subclass objects */
class Shape {
    private final String creator;

    /** All shapes (including sub-shapes) are created here. */
    public Shape(String creator) {
        this.creator = creator;
    }

    /** Return the name of this shape and can't be overridden. */
    public final String getName() {
        // getClass() will be the actual class of the object, like Circle not Shape.
        return String.format("%s created by %s", getClass().getSimpleName(), creator);
    }

    /** Return the area of this shape. */
    public double getArea() {
        return 0; // subclasses should override this
    }
}

/** Circle subclass based on Shape superclass */
class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this("None", radius);
    }

    /** Overloaded Circle constructor */
    public Circle(String creator, double radius) {
        super(creator);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    /** Specific Circle area calculation */
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /** New implementation of Circle Object's built-in toString method. */
    @Override
    public String toString() {
        return String.format("%s%nRadius: %.1f cm%nArea  : %.2f cm^2",
                getName(), getRadius(), getArea());
    }
}

/** Rectangle subclass based on Shape superclass */
class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this("None", width, height);
    }

    /** Overloaded Rectangle constructor */
    public Rectangle(String creator, double width, double height) {
        super(creator);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /** Specific Rectangle area calculation */
    @Override
    public double getArea() {
        return width * height;
    }

    /** New implementation of Rectangle Object's built-in toString method. */
    @Override
    public String toString() {
        return String.format("%s%nWidth : %.1f cm%nHeight: %.1f cm%nArea  : %.2f cm^2",
                getName(), getWidth(), getHeight(), getArea());
    }
}

/** Square subclass based on Rectangle superclass */
class Square extends Rectangle {
    private final double side;

    public Square(double side) {
        super("None", side, side);
        this.side = side;
    }

    /** Overloaded Square constructor */
    public Square(String creator, double side) {
        super(creator, side, side);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    /** Specific Square area calculation */
    @Override
    public double getArea() {
        return side * side;
    }

    /** New implementation of Square Object's built-in toString method. */
    @Override
    public String toString() {
        return String.format("%s%nSide: %.1f cm%nArea: %.2f cm^2",
                getName(), getSide(), getArea());
    }
}

/*
 * Implemented Shape's getName() method to print out the simple name of the class.
 * For example, print out the literal "Rectangle" when calling Shape's getName()
 *  method from the Rectangle's tpString() method.
 */
