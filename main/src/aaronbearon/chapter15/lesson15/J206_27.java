package aaronbearon.chapter15.lesson15;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 7
 * Description:
 * Change the speed and direction of the image.
 * The student should change the image to one of their own preference.
 */
public class J206_27 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane
        Pane pane = new Pane();

        //* I set the imageUrl Resource to an image in the working directory.
        URL imageUrl = getClass().getResource("J206_27.jpg");

        //* This is how you enable a path only in the working directory to be specified.
        assert imageUrl != null;
        ImageView imageView = new ImageView(new Image(imageUrl.toExternalForm()));

        pane.getChildren().add(imageView);

        // Create a path transition
        //* The image rises faster because the animation only happens for 2 seconds.
        //* The change in the starting and ending x coordinates makes it go North-east.
        PathTransition pt = new PathTransition(Duration.millis(2000),
                new Line(0, 200, 200, 0), imageView);
        pt.setCycleCount(5);
        pt.play(); // Start animation

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setTitle("FlagRisingAnimation"); // Set the stage title
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

The starting and ending coordinates along with the image are added to the PathTransition.
That's why the flag moves along the path that it does.

I used the URL class and the toExternalForm() to use an image in the current working directory.
This is the same thing I did for one of the parts in lesson 5.
This time, I choose a Falcons image because I love pro football and the Falcons are my favorite team.
I wanted the banner to "RISE UP" but go a different direction.
So, I changed the X coordinates to make it go from bottom left to top right.
The animation is sped up because it's 2 seconds instead of 10.

*/
