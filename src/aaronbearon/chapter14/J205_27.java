package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 7
 * Description:
 * Add a 4th textbox with the text "Phone Number" and a text field
 */
public class J205_27 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane and set its properties
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER); // Set center alignment
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5); // Set vGap to 5.5px

        // Place nodes in the pane
        pane.add(new Label("First Name:"), 0, 0);
        pane.add(new TextField(), 1, 0);
        pane.add(new Label("MI:"), 0, 1);
        pane.add(new TextField(), 1, 1);
        pane.add(new Label("Last Name:"), 0, 2);
        pane.add(new TextField(), 1, 2);

        //* Added a phone number label and field.
        pane.add(new Label("Phone number:"), 0, 3);
        pane.add(new TextField(), 1, 3);
        Button btAdd = new Button("Add Name");

        //* Moved the button down a row.
        pane.add(btAdd, 1, 4);
        GridPane.setHalignment(btAdd, HPos.RIGHT);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("ShowGridPane"); // Set the stage title
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

A grid pane is used.
It enables rows and columns to be specified.
For instance: 1, 2 is second column and third row.
Like before, I added a label in the first column (0) and text field in the second column (1).
This time, I set the rows to 4 and set the button row to 5.
Like before, all these nodes are added to the pane.

*/
