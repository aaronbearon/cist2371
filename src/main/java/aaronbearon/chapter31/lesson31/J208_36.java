package aaronbearon.chapter31.lesson31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.*;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 6
 * Description: Add more shapes and lines.
 * Make their colors and widths different.
 */
public class J208_36 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Rectangle rectangle1 = new Rectangle(20, 20, 70, 120);
        rectangle1.setFill(Color.WHITE);
        rectangle1.setStrokeWidth(15);
        rectangle1.setStroke(Color.ORANGE);

        Rectangle rectangle2 = new Rectangle(20, 20, 70, 120);
        rectangle2.setFill(Color.WHITE);
        rectangle2.setStrokeWidth(15);
        rectangle2.setStroke(Color.ORANGE);
        rectangle2.setTranslateX(100);
        rectangle2.setStrokeLineJoin(StrokeLineJoin.BEVEL);

        Rectangle rectangle3 = new Rectangle(20, 20, 70, 120);
        rectangle3.setFill(Color.WHITE);
        rectangle3.setStrokeWidth(15);
        rectangle3.setStroke(Color.ORANGE);
        rectangle3.setTranslateX(200);
        rectangle3.setStrokeLineJoin(StrokeLineJoin.ROUND);

        Line line1 = new Line(320, 20, 420, 20);
        line1.setStrokeLineCap(StrokeLineCap.BUTT);
        line1.setStrokeWidth(20);

        Line line2 = new Line(320, 70, 420, 70);
        line2.setStrokeLineCap(StrokeLineCap.ROUND);
        line2.setStrokeWidth(20);

        Line line3 = new Line(320, 120, 420, 120);
        line3.setStrokeLineCap(StrokeLineCap.SQUARE);
        line3.setStrokeWidth(20);

        Line line4 = new Line(460, 20, 560, 120);
        line4.getStrokeDashArray().addAll(10.0, 20.0, 30.0, 40.0);

        //* Blue right triangle with bright green round border.
        Polygon triangle1 = new Polygon(20.0, 20.0, 20.0, 90.0, 90.0, 90.0);
        triangle1.setFill(Color.BLUE);
        triangle1.setStrokeWidth(5);
        triangle1.setStroke(Color.LIME);
        triangle1.setTranslateY(170);
        triangle1.setStrokeLineJoin(StrokeLineJoin.ROUND);

        //* Bright green right triangle with red normal border.
        Polygon triangle2 = new Polygon(90.0, 20.0, 20.0, 90.0, 90.0, 90.0);
        triangle2.setFill(Color.LIME);
        triangle2.setStrokeWidth(5);
        triangle2.setStroke(Color.RED);
        triangle2.setTranslateX(100);
        triangle2.setTranslateY(170);

        //* Red right triangle with blue border with missing corners.
        Polygon triangle3 = new Polygon(20.0, 20.0, 20.0, 90.0, 90.0, 90.0);
        triangle3.setFill(Color.RED);
        triangle3.setStrokeWidth(10);
        triangle3.setStroke(Color.BLUE);
        triangle3.setTranslateX(200);
        triangle3.setTranslateY(170);
        triangle3.setStrokeLineJoin(StrokeLineJoin.BEVEL);

        //* Lines 5-7 are the same types as above, but they're vertical.
        //* They're magenta, cyan, and yellow with different widths.
        Line line5 = new Line(320, 190, 320, 290);
        line5.setStrokeLineCap(StrokeLineCap.BUTT);
        line5.setStrokeWidth(10);
        line5.setStroke(Color.MAGENTA);

        Line line6 = new Line(370, 190, 370, 290);
        line6.setStrokeLineCap(StrokeLineCap.ROUND);
        line6.setStrokeWidth(15);
        line6.setStroke(Color.CYAN);

        Line line7 = new Line(420, 190, 420, 290);
        line7.setStrokeLineCap(StrokeLineCap.SQUARE);
        line7.setStrokeWidth(25);
        line7.setStroke(Color.YELLOW);

        //* Added a red dashed line like above, but perpendicular.
        Line line8 = new Line(460, 290, 560, 190);
        line8.getStrokeDashArray().addAll(18.0, 12.0, 7.0, 6.0);
        line8.setStroke(Color.RED);

        //* Made necessary additions.
        Pane pane = new Pane();
        pane.getChildren().addAll(rectangle1, rectangle2, rectangle3,
                line1, line2, line3, line4, line5, line6, line7, line8,
                triangle1, triangle2, triangle3);

        //* Increased height
        Scene scene = new Scene(pane, 610, 360);
        primaryStage.setTitle("StrokeDemo"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }

    // Launch the program from command-line
    public static void main(String[] args) {
        launch(args);
    }
}

/*

My new shapes are below the originals.
I made right triangles and used the stroke join types.
They include red, bright green (lime) and blue, with smaller borders than above.
My three line caps are vertical, colored, and different widths.
I made a red dashed line perpendicular to the black one above.
The dash fields alternate line and non-line.

*/
