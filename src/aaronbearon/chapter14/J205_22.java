package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 2
 * Description:
 * Customize the circle color and set a new radius
 */
public class J205_22 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane to hold the circle
        Pane pane = new Pane();

        // Create a circle and set its properties
        Circle circle = new Circle();
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        //* Now, the circle's diameter is basically the window size.
        circle.setRadius(100);
        circle.setStroke(Color.BLACK);
        //* The circle's color is pure blue.
        circle.setFill(new Color(0, 0, 1, 1));
        pane.getChildren().add(circle); // Add circle to the pane

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("ShowCircleCentered"); // Set the stage title
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

A pane from the Pane class is created.
pane.widthProperty() and pane.heightProperty() are divided by 2.
This puts the target object in the middle of the pane.
The circle is the target object.
Its centerXProperty() and centerYproperty()
    are bound to their respective pane width and height properties.
The circle is now RED at full opacity.

Like before:
The pane is placed on the scene.
The scene is put in the stage.
The stage is displayed.

*/
