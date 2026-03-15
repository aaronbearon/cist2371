package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 4
 * Description:
 * Add a third stage
 */
public class J205_24 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place a button in the scene
        Scene scene = new Scene(new Button("OK"), 200, 250);
        primaryStage.setTitle("MyJavaFX"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        Stage stage = new Stage(); // Create a new stage
        stage.setTitle("Second Stage"); // Set the stage title
        // Set a scene with a button in the stage
        stage.setScene(new Scene(new Button("New Stage"), 200, 250));
        stage.show(); // Display the stage

        //* Create a third stage.
        //* It has a unique title and button label.
        Stage stage3 = new Stage();
        stage3.setTitle("Final Stage");
        stage3.setScene(new Scene(new Button("Welcome to the final stage!"), 200, 250));
        stage3.show(); // Display the stage
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

A third stage is created basically like how the second stage is made.
The difference is that the title and button are customized.
Title: Final Stage
Button: Welcome to the final stage!

Note: the required PNG will have all three window next to each other instead of fully overlapping.

*/
