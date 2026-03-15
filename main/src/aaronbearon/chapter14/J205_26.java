package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 6
 * Description:
 * Set the pane's rotation to 90 degrees clockwise
 */
public class J205_26 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place a button in the scene
        StackPane pane = new StackPane();
        Button btOK = new Button("OK");
        btOK.setStyle("-fx-border-color: blue;");
        pane.getChildren().add(btOK);

        pane.setRotate(90); //* Rotate pane 90 degrees instead of 45.
        pane.setStyle(
                "-fx-border-color: red; -fx-background-color: lightgray;");

        //* Width is increased to fit the whole title.
        Scene scene = new Scene(pane, 300, 250);
        primaryStage.setTitle("NodeStyleRotateDemo"); // Set the stage title
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

Width was changed from 200 to 300 because 200 wasn't wide enough for the title.
The other thing unique here is the pane rotation.
It's originally rotated 45 degrees clockwise.
I set it to 90 degrees.

*/
