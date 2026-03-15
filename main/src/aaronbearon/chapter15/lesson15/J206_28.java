package aaronbearon.chapter15.lesson15;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 8
 * Description:
 * Make the animation "play" and "pause" with the buttons instead of the mouse.
 */
public class J206_28 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Place an ellipse to the pane
        Pane pane = new Pane();
        Ellipse ellipse = new Ellipse(10, 10, 100, 50);
        ellipse.setFill(Color.RED);
        ellipse.setStroke(Color.BLACK);
        ellipse.centerXProperty().bind(pane.widthProperty().divide(2));
        ellipse.centerYProperty().bind(pane.heightProperty().divide(2));
        ellipse.radiusXProperty().bind(
                pane.widthProperty().multiply(0.4));
        ellipse.radiusYProperty().bind(
                pane.heightProperty().multiply(0.4));
        pane.getChildren().add(ellipse);

        //* Tricky!
        //* An hBox is needed to provide a space for the buttons.
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        Button btPlay = new Button("play");
        Button btPause = new Button("pause");
        //* Buttons are placed in the hBox.
        hBox.getChildren().addAll(btPlay, btPause);
        //* A borderPane is needed to put the original pane in the center and the hBox on bottom.
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);

        // Apply a fade transition to ellipse
        FadeTransition ft =
                new FadeTransition(Duration.millis(3000), ellipse);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play(); // Start animation

        // Control animation
        //* The animation state is now controlled differently.
        btPlay.setOnAction(e -> ft.play());
        btPause.setOnAction(e -> ft.pause());

        // Create a scene and place it in the stage
        //* Everything else was added to the borderPane.
        //* So it's actually the borderPane being added to the scene.
        Scene scene = new Scene(borderPane, 200, 150);
        primaryStage.setTitle("FadeTransitionDemo"); // Set the stage title
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

The fading in and out is because of the auto reverse feature.

I added an hBox to hold a play and a pause button.
I added a borderPane to hold the original pane (center) and the hBox (bottom).
That basically makes the layout the same as two prior problems.

Based on my changes in the lambda statement, the mouse no longer controls play or pause.
It's now the buttons which do it.

*/
