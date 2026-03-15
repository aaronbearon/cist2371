package aaronbearon.chapter16.lesson16;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class J207_33 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Text text = new Text(20, 20, "JavaFX Programming");

        ScrollBar sbHorizontal = new ScrollBar();
        ScrollBar sbVertical = new ScrollBar();
        sbVertical.setOrientation(Orientation.VERTICAL);

        //* Set minimums and maximums for the scroll bars.
        sbVertical.setMin(40);
        sbVertical.setMax(60);
        sbHorizontal.setMin(80);
        sbHorizontal.setMax(120);

        // Create a text in a pane
        Pane paneForText = new Pane();
        paneForText.getChildren().add(text);

        // Create a border pane to hold text and scroll bars
        BorderPane pane = new BorderPane();
        pane.setCenter(paneForText);
        pane.setBottom(sbHorizontal);
        pane.setRight(sbVertical);

        // Listener for horizontal scroll bar value change
        //* This makes it not necessarily go to the right of the screen.
        sbHorizontal.valueProperty().addListener(ov ->
                text.setX(sbHorizontal.getValue()));

        // Listener for vertical scroll bar value change
        //* This makes it not necessarily go to the bottom of the screen.
        sbVertical.valueProperty().addListener(ov ->
                text.setY(sbVertical.getValue()));

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ScrollBarDemo"); // Set the stage title
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
