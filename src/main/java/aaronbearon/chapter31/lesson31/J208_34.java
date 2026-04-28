package aaronbearon.chapter31.lesson31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 4
 * Description: Increase the shape count.
 */
public class J208_34 extends Application {
    //* Copied this field everywhere where necessary.
    //* Changed it to 10.
    private static final int SHAPES = 10;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        java.util.Random random = new java.util.Random();
        // The radius of the circle for anchoring rectangles
        double radius = 90;
        double width = 20; // Width of the rectangle
        double height = 40; // Height of the rectangle
        for (int i = 0; i < SHAPES; i++) {
            // Center of a rectangle
            double x = 150 + radius * Math.cos(i * 2 * Math.PI / SHAPES);
            double y = 150 + radius * Math.sin(i * 2 * Math.PI / SHAPES);
            Rectangle rectangle = new Rectangle(
                    x - width / 2, y - height / 2, width, height);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.color(random.nextDouble(),
                    random.nextDouble(), random.nextDouble()));
            rectangle.setRotate(i * 360 / SHAPES); // Rotate the rectangle
            pane.getChildren().add(rectangle);
        }

        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("RotateDemo"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }

    // Launch the program from command-line
    public static void main(String[] args) {
        launch(args);
    }
}

/*

I looked for all the places where number 8 is used.
I added a private static final variable to copy everywhere number 8 appeared.
Since I set it to 10, there are now 10 of those rectangles.

*/
