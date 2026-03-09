package aaronbearon.chapter15.lesson15;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 9
 * Description:
 * These are the things that need to changed regarding the text.
 * Color, font, message content, and display rate.
 */
public class J206_29 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();
        //* Text content is different
        Text text = new Text(20, 50, "GUIs are interesting");
        //* Text is now bright green
        text.setFill(new Color(0, 1, 0, 1));
        //* The font is Times New Roman with size 20
        text.setFont(new Font("Times New Roman", 20));
        pane.getChildren().add(text); // Place text into the stack pane

        // Create a handler for changing text
        EventHandler<ActionEvent> eventHandler = e -> {
            if (text.getText().length() != 0) {
                text.setText("");
            } else {
                //* Text content needs to be updated here.
                text.setText("GUIs are interesting");
            }
        };

        // Create an animation for alternating text
        Timeline animation = new Timeline(
                //* The increased millisecond count slows the flashing.
                new KeyFrame(Duration.millis(800), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

        // Pause and resume animation
        text.setOnMouseClicked(e -> {
            if (animation.getStatus() == Animation.Status.PAUSED) {
                animation.play();
            } else {
                animation.pause();
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 50);
        primaryStage.setTitle("TimelineDemo"); // Set the stage title
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

The text flashing animation only stops when the mouse is clicked on the visible text.
When playing, it more accurately becomes and empty string instead of going invisible when disappearing.

I changed the color to bright lime green, and I changed the display message.
When I set the font to Times New Roman, I specified a bigger size than the original.

I slowed the display rate by increasing the milliseconds to 800.

*/
