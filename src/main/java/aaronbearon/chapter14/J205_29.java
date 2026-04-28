package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 9
 * Description:
 * Make the two originally perpendicular lines parallel
 */
public class J205_29 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        Scene scene = new Scene(new LinePane(), 200, 200);
        primaryStage.setTitle("ShowLine"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}

class LinePane extends Pane {
    public LinePane() {
        Line line1 = new Line(10, 10, 10, 10);
        //* The endXProperty is removed to unbind line1 from the right side of the window.
        line1.endYProperty().bind(heightProperty().subtract(10));
        line1.setStrokeWidth(5);
        line1.setStroke(Color.GREEN);
        getChildren().add(line1);

        Line line2 = new Line(10, 10, 10, 10);
        line2.startXProperty().bind(widthProperty().subtract(10));
        //* The endXProperty is added to bind line2 to the right side of the window.
        line2.endXProperty().bind(widthProperty().subtract(10));
        line2.endYProperty().bind(heightProperty().subtract(10));
        line2.setStrokeWidth(5);
        line2.setStroke(Color.GREEN);
        getChildren().add(line2);
    }
}

/*

A LinePane is added to the scene.
In the LinePane class, lines from the Line class are created.
Insets are set to 10 and may be overridden later.
(x, y) (0, 0) starts at the top left.
X increases right. Y increases down.

Lines tend to start and end at 0, 0 (10, 10 in this case due to insets).

Line 1's ending y is bound to height property (bottom) making it go top left to bottom left.
The ending x (which was removed) made the ending point be bottom right.

Line 2's starting and ending x values are bound to width property (right).
This puts the whole line on the right.
Ending y puts the ending point on the bottom.
There was previously no ending x.
This made the line go from top right to bottom left until that was changed.

*/
