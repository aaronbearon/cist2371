package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 1
 * Description:
 * Customize the title and text for button label
 */
public class J205_21 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place a button in the scene
        StackPane pane = new StackPane();

        // getChildren() returns a 'list' with all the child nodes in the class container.
        //* Set my own button text
        pane.getChildren().add(new Button("New Button by Aaron Blum"));

        //* Customized the load size
        Scene scene = new Scene(pane, 300, 100);

        //* Set my own title
        primaryStage.setTitle("Part 1 With New Button"); // Set the stage title
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

A pane from the StackPane class is created.
A new button named "New Button by Aaron Blum" is added to pane.getChildren().
The window title now says "Part 1 With New Button".

The pane is placed on the scene with load width 300 and height 100.
I did that to make the whole title visible upon loading the stage.

The scene is put in the stage.
The stage is displayed.

Note: Assuming the problems are in order 1-13,
    this whole documentation section won't repeat everything that's the same in each.

The stage and the window are basically the same thing.

*/
