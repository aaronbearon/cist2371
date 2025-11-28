package aaronbearon.playground;

public class ReferenceTester {
    public static void main(String[] args) {
        Shape shape = new Shape("Hello");
        shape.setString("Bye");
        shape.setInteger(1);
        shape.setString("Hello!");
        shape.setInteger(2);
        System.out.println(shape.getString() + shape.getInteger());
        shape.createShape2("Hi!", 3);
        System.out.println(shape.getShape2String());
        shape.createShape2("Bye!", 4);
        System.out.println(shape.getShape2String());
        shape = new Shape("Hello");
        System.out.println(shape.getShape2String());
    }
}

class Shape {
    private String string;
    private int integer;
    private Shape shape2;

    Shape(String string) {
        this(string, 0);
    }

    Shape(String string, int integer) {
        this.string = string;
        this.integer = integer;
    }

    void createShape2(String string, int integer) {
        this.shape2 = new Shape(string, integer);
    }

    String getShape2String() {
        if (shape2 != null) {
            return shape2.getString() + shape2.getInteger();
        }
        return "Dependent Shape is null";
    }

    void setString(String string) {
        this.string = string;
    }

    void setInteger(int integer) {
        this.integer = integer;
    }

    String getString() {
        return string;
    }

    int getInteger() {
        return integer;
    }
}
