package aaronbearon.chapter31.lesson31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 3
 * Description: Put the circles all on one row and add a new one to a new pane.
 * Change their colors and sizes.
 */
public class J208_33 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        //* Changed position of starting shape.
        double x = 100;
        double y = 250;
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < 10; i++) {
            //* Changed rectangles to ellipses.
            //* Set the radius.
            Ellipse ellipse = new Ellipse(10, 10, 30, 20);
            ellipse.setFill(Color.WHITE);
            ellipse.setStroke(Color.color(random.nextDouble(),
                    random.nextDouble(), random.nextDouble()));
            //* Changed direction to go north (and slightly east).
            ellipse.setTranslateX(x += 5);
            ellipse.setTranslateY(y -= 20);
            pane.getChildren().add(ellipse);
        }

        //* Size is adjusted for the ellipse positions.
        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("TranslationDemo"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }

    // Launch the program from command-line
    public static void main(String[] args) {
        launch(args);
    }
}

/*

The values in x and y variables translate the shape from the starting position of 10, 10.
However, the shape is translated (moved) first before it's placed.
I changed the shape to ellipse (horizontal) and moved the shapes.
They now start in a different location and go a different direction.

*/
