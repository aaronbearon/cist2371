package aaronbearon.chapter15.lesson15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 4
 * Description:
 * Four new buttons with appropriate names should be included for the diagonal directions.
 * The step size should be different (possibly bigger).
 */
public class J206_24 extends Application {
    //* Without this, the numbers below would need to be changed at the same time manually.
    //* I created this and copied this to all the 5's in the anonymous inner classes.
    //* I changed the step size to 20
    private static final int STEP = 20;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Text text = new Text(40, 40, "Programming is fun");
        Pane pane = new Pane(text);

        // Hold four buttons in an HBox
        //* Now eight
        Button btUp = new Button("Up");
        Button btDown = new Button("Down");
        Button btLeft = new Button("Left");
        Button btRight = new Button("Right");

        //* Proper button names and texts
        Button btNW = new Button("Up-left");
        Button btSW = new Button("Down-left");
        Button btSE = new Button("Down-right");
        Button btNE = new Button("Up-right");

        //* Added the 4 new buttons at the end
        HBox hBox = new HBox(btUp, btDown, btLeft, btRight, btNW, btSW, btSE, btNE);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane(pane);
        borderPane.setBottom(hBox);

        // Create and register the handler
        //* These now contain the STEP field as mentioned above.
        btUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                text.setY(text.getY() > 10 ? text.getY() - STEP : 10);
            }
        });

        btDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                text.setY(text.getY() < pane.getHeight() ?
                        text.getY() + STEP : pane.getHeight());
            }
        });

        btLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                text.setX(text.getX() > 0 ? text.getX() - STEP : 0);
            }
        });

        btRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                text.setX(text.getX() < pane.getWidth() - 100 ?
                        text.getX() + STEP : pane.getWidth() - 100);
            }
        });

        //* Anonymous inner classes for the new buttons
        //* Note: Statements are copied from above and placed in the correct handlers.
        btNW.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                text.setY(text.getY() > 10 ? text.getY() - STEP : 10);
                text.setX(text.getX() > 0 ? text.getX() - STEP : 0);
            }
        });

        btSW.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                text.setY(text.getY() < pane.getHeight() ?
                        text.getY() + STEP : pane.getHeight());
                text.setX(text.getX() > 0 ? text.getX() - STEP : 0);
            }
        });

        btSE.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                text.setY(text.getY() < pane.getHeight() ?
                        text.getY() + STEP : pane.getHeight());
                text.setX(text.getX() < pane.getWidth() - 100 ?
                        text.getX() + STEP : pane.getWidth() - 100);
            }
        });

        btNE.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                text.setY(text.getY() > 10 ? text.getY() - STEP : 10);
                text.setX(text.getX() < pane.getWidth() - 100 ?
                        text.getX() + STEP : pane.getWidth() - 100);
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 400, 350);
        primaryStage.setTitle("AnonymousHandlerDemo"); // Set title
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

Originally, there are 4 buttons.
Each one has its own: variable name, display name, and anonymous inner class.

When I saw that each button's handler moved the text by 5 in a single direction,
    I created a private static final int STEP as a member of the main class, and copied the field over.

After that, I made 4 new buttons, with unique but understandable variable and display names.
The buttons are added to the hBox. There's a new handler for each new button.

Each handler has the correct X statement and Y statement copied from the originals.
I tested the 4 new buttons, and they work.

*/
