package aaronbearon.chapter31.lesson31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 1
 * Description: Change the color and shape of the line using the features below.
 */
public class J208_31 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // Create a Path
        Path path = new Path();

        //* Changed the order of line types and most of their values.
        path.getElements().add(new MoveTo(50.0, 50.0));
        path.getElements().add(new LineTo(200.5, 180));
        path.getElements().add(new VLineTo(120));

        path.getElements().add(
                new CubicCurveTo(300, 100, 150, 275, 400, 45));
        path.getElements().add(new QuadCurveTo(130, 130, 250, 250));
        path.getElements().add(new HLineTo(150));
        ArcTo arcTo = new ArcTo(50, 50, 0, 120, 180,
                true, true);
        path.getElements().add(arcTo);
        //* Changed the path color to red
        path.setStroke(Color.RED);

        path.getElements().add(new ClosePath());

        pane.getChildren().add(path);
        path.setFill(null);

        //* New size is big enough to show the whole shape at load time.
        Scene scene = new Scene(pane, 450, 300);
        primaryStage.setTitle("PathDemo"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }

    // Launch the program from command-line
    public static void main(String[] args) {
        launch(args);
    }
}

/*

I kept the same line types to keep it similar to the example.
I scrambled the order of the line types and changed some values to make a unique shape.
The path is now red.

*/
