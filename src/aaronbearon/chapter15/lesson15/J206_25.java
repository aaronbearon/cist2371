package aaronbearon.chapter15.lesson15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 5
 * Description:
 * Change the draggable text's content and color.
 */
public class J206_25 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane and set its properties
        Pane pane = new Pane();

        //* Changed the text and set color to a shade of blue.
        Text text = new Text(20, 20, "You can drag this text!");
        text.setFill(new Color(0, 0.5, 1, 1));
        pane.getChildren().addAll(text);
        text.setOnMouseDragged(e -> {
            text.setX(e.getX());
            text.setY(e.getY());
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 100);
        primaryStage.setTitle("MouseEventDemo"); // Set the stage title
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

Lines 25-28 allow the text to be dragged with the mouse.
I just changed the message, and made the text between cyan and blue.

*/
