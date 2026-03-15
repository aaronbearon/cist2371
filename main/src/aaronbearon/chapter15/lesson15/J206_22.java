package aaronbearon.chapter15.lesson15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 2
 * Description:
 * The GUI should show a welcome message when OK is clicked.
 * The message should be gone when cancel is clicked.
 */
public class J206_22 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane and set its properties
        HBox pane = new HBox(10);
        pane.setAlignment(Pos.CENTER);

        //* Add a label for show/hide
        Label label = new Label("Welcome to event-driven programming!");
        label.setVisible(false);
        Button btOK = new Button("OK");
        Button btCancel = new Button("Cancel");

        //* Constructors now take a label
        OKHandlerClass handler1 = new OKHandlerClass(label);
        btOK.setOnAction(handler1);
        CancelHandlerClass handler2 = new CancelHandlerClass(label);
        btCancel.setOnAction(handler2);
        //* Label is added to the pane
        pane.getChildren().addAll(btOK, btCancel, label);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("HandleEvent"); // Set the stage title
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

//* A private Label and a constructor that takes a label are added.
//* This removes the no-arg constructor.
//* The handler makes the label visible.
class OKHandlerClass implements EventHandler<ActionEvent> {
    private Label label;

    public OKHandlerClass(Label label) {
        this.label = label;
    }

    @Override
    public void handle(ActionEvent e) {
        label.setVisible(true);
    }
}

//* Same changes compared with OK class except that the handler makes the label invisible.
class CancelHandlerClass implements EventHandler<ActionEvent> {
    private Label label;

    public CancelHandlerClass(Label label) {
        this.label = label;
    }

    @Override
    public void handle(ActionEvent e) {
        label.setVisible(false);
    }
}

/*

My approach was to move all the output to the GUI.
I created a label with a welcome text above, and turned off the visibility.
I changed the classes to accept a label in each constructor, and store it as a private field.
Now, OK's handler turns on the label visibility, but Cancel's handler turns it off.
The buttons and label are all shown on the same line with the message on the right.

*/
