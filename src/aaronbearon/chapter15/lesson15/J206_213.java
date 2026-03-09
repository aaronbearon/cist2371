package aaronbearon.chapter15.lesson15;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 13
 * Description:
 * Similar to part 6, turn the circle into a square.
 * The orange rectangle should now go along the square's edges.
 */
public class J206_213 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane
        Pane pane = new Pane();

        // Create a rectangle
        Rectangle rectangle = new Rectangle(0, 0, 25, 50);
        rectangle.setFill(Color.ORANGE);

        // Create a square
        //* Changed the Circle (Class) to Rectangle, and circle (variable) to square.
        //* Set four fields which put the square exactly in the center of the window.
        //* Usages are reflected everywhere
        Rectangle square = new Rectangle(75, 50, 100, 100);
        square.setFill(Color.WHITE);
        square.setStroke(Color.BLACK);

        // Add square and rectangle to the pane
        pane.getChildren().add(square);
        pane.getChildren().add(rectangle);

        // Create a path transition
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(4000));
        pt.setPath(square);
        pt.setNode(rectangle);
        pt.setOrientation(
                PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        pt.play(); // Start animation

        square.setOnMousePressed(e -> pt.pause());
        square.setOnMouseReleased(e -> pt.play());

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setTitle("PathTransitionDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

/*

The path of the PathTransition is the square (formerly circle).
This means the square's perimeter.
The orientation type in line 47 is why the orange rectangle rotates at the square's corners.
Every time the rectangle orbits the square's center exactly once, it reverses.
The properties I set make it a square and not just a rectangle.
It also puts it in the center of the load size window.

*/
