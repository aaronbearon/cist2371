package aaronbearon.chapter10;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Rectangle rectangle;
            boolean open;
            if (Math.random() < 0.25) {
                rectangle = new Rectangle();
                open = true;
            } else if (Math.random() < 0.5) {
                rectangle = new Rectangle();
                open = false;
            } else if (Math.random() < 0.75) {
                rectangle = new Rectangle(false);
                open = true;
            } else {
                rectangle = new Rectangle(false);
                open = false;
            }

            rectangle.setFields((int) ((Math.random() * 100) + 1), (int) ((Math.random() * 100) + 1), open);
            System.out.println("Width: " + rectangle.getWidth() + ", Height: " + rectangle.getHeight() +
                    ", Perimeter: " + rectangle.isOpen() + ", Result: " + rectangle.getMeasure());
        }
    }
}

class Rectangle {
    private int width;
    private int height;
    private boolean open;

    Rectangle() {
        this(1);
    }

    Rectangle(boolean open) {
        this(1, open);
    }

    Rectangle(int width) {
        this(width, 1);
    }

    Rectangle(int width, boolean open) {
        this(width, 1, open);
    }

    Rectangle(int width, int height) {
        this(width, height, true);
    }

    Rectangle(int width, int height, boolean open) {
        this.width = width;
        this.height = height;
        this.open = open;
    }

    void setFields(int width, int height, boolean open) {
        this.width = width;
        this.height = height;
        this.open = open;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    boolean isOpen() {
        return open;
    }

    int getMeasure() {
        if (open) {
            return 2 * (width + height);
        } else {
            return width * height;
        }
    }
}
