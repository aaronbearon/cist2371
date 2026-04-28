package aaronbearon.chapter14;

//* Reformatting the file expands out the imports, eliminating the * at the end.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 3
 * Description:
 * Set a different font.
 * Make it not bold and not italic.
 * Change the circle into something else.
 */
public class J205_23 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane to hold the //* rectangle
        Pane pane = new StackPane();

        //* Create a rectangle and set its properties
        //* Width is 150 and height is 130.
        Rectangle rectangle = new Rectangle(150, 130, 150, 130);

        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(new Color(0.5, 0.5, 0.5, 0.1));
        pane.getChildren().add(rectangle); // Add //* rectangle to the pane

        // Create a label and set its properties
        Label label = new Label("JavaFX");

        //* Font is Arial, no longer bold, no longer italic, and larger.
        label.setFont(Font.font("Arial",
                FontWeight.NORMAL, FontPosture.REGULAR, 40));
        pane.getChildren().add(label);

        // Create a scene and place it in the stage
        //* Included width and height where there were none.
        //* The pop-up window was too small.
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("FontDemo"); // Set the stage title
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

A rectangle is created with x, y, width, and height at 150, 130, 150, 130 respectively.
X and Y are where the rectangle is placed.
Width and Height are the rectangle dimensions.
The circle needed to be changed, so I replaced it with a rectangle.

A label is created and put in the center of the pane.
The text says "Shape in GUI".
The properties are font: "Arial", with normal font weight and regular (not italic) font posture.
The font size is 40

The window load size is now 200 by 200.

*/
