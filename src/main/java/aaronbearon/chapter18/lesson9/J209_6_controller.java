package aaronbearon.chapter18.lesson9;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 9 Part 6
 * Description: Create a fractal
 */
public class J209_6_controller {
    @FXML
    private TextField txtIn;

    @FXML
    private Slider slider;

    @FXML
    private Pane pane;

    // Mutable state recomputed for drawing.
    private double rotate = 0;
    NumberBinding minDimension;
    NumberBinding xOffset;
    NumberBinding yOffset;

    /**
     * A class to group an x value and its y value into a single point
     */
    public static final class Point {
        public double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    @FXML
    public void makeShapes() {
        // Set the rotation.
        rotate = slider.getValue() / 100.0;
        // Fit the fractal nicely within the smaller dimension of the pane.
        minDimension = Bindings.min(pane.widthProperty(), pane.heightProperty()).multiply(0.9);
        xOffset = pane.widthProperty().subtract(minDimension).divide(2);
        yOffset = pane.heightProperty().subtract(minDimension).divide(2);
        pane.getChildren().clear();

        try {
            int count = Integer.parseInt(txtIn.getText());
            Point[] points = {new Point(0, 0), new Point(100, 0), new Point(100, 100), new Point(0, 100)};
            drawSquare(points, count);
        } catch (Exception e) {
            txtIn.setText("ERR");
        }
    }

    /**
     * This is a recursive method that starts at the big square and gets smaller and smaller.
     *
     * @param points The array of 4 points of the point class
     * @param count  The number of squares drawn
     */
    public void drawSquare(Point[] points, int count) {
        if (count >= 1) {
            // Print a smaller square, recursively.
            Point[] newPoints = new Point[points.length];
            drawLine(points, newPoints, newPoints.length - 1);
            drawSquare(newPoints, count - 1);
        }
    }

    /**
     * This version of drawLine is the recursive method.
     * It also computes the points for the next line.
     *
     * @param points    The original array of 4 points
     * @param newPoints The new array of 4 points to draw the next line
     * @param count     The number of lines to be drawn for this square (count is 3 down to 0 for array indexing).
     */
    public void drawLine(Point[] points, Point[] newPoints, int count) {
        if (count >= 0) {
            Point start = points[count];
            Point end = points[(count + 1) % points.length];
            // Draw a line connecting the two points
            drawLine(start, end);
            // Compute the ordinary midpoint (no rotation yet)
            Point mid = new Point((start.x + end.x) / 2, (start.y + end.y) / 2);
            // Now apply rotation
            mid.x -= (end.x - start.x) * rotate;
            mid.y -= (end.y - start.y) * rotate;
            newPoints[count] = mid;
            drawLine(points, newPoints, count - 1);
        }
    }

    /**
     * This version of drawLine actually draws the lines.
     * It performs binding with the class fields above and point values.
     *
     * @param start The starting point
     * @param end   The ending point
     */
    public void drawLine(Point start, Point end) {
        Line line = new Line();
        line.startXProperty().bind(getVal(xOffset, minDimension, start.x));
        line.startYProperty().bind(getVal(yOffset, minDimension, start.y));
        line.endXProperty().bind(getVal(xOffset, minDimension, end.x));
        line.endYProperty().bind(getVal(yOffset, minDimension, end.y));
        this.pane.getChildren().add(line);
    }

    /**
     * This method works with the core properties regarding the window and the dimensions.
     * It returns the appropriate number binding.
     *
     * @param offset       The x or y offset
     * @param minDimension The smaller dimension based on current window size
     * @param times        The point to divide by 100 and fit in the pane
     * @return A number binding
     */
    public NumberBinding getVal(NumberBinding offset, NumberBinding minDimension, double times) {
        return offset.add((minDimension).multiply(times / 100.0));
    }
}

/*

I created my own fractal.
It's basically a twisting square tunnel.

The makeShapes method is the one called from the actions.
That method sets the NumberBindings based on the window size.

It also sets the rotate value based on the slider and clears the pane before adding new nodes.

The user adjusts the slider rotation and enters a number in the text field for the square count.
When the textField onAction happens, or a key or mouse is released, the fractal is made.

I used nested recursion.
The outer recursive method handles making the squares.
The inner recursive method handles making the lines.

I also made a Point class to group an x and its matching y.

The contents of the text field are checked for valid input.

*/
