package aaronbearon.chapter16.lesson7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 7 Part 8
 * Description: Play a video off of YouTube.
 */
public class J207_38 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Unfortunately, the /embed/ URLs don't work anymore, they get a 153 error.
        WebView webView = new WebView();
        webView.getEngine().load("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        // Just size the player to trim it to only the video area.
        Pane pane = new Pane(webView);
        Scene scene = new Scene(pane, 822, 514);
        stage.setTitle("Rick"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
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
I tried very hard but could not get the embed feature to work from YouTube.
Instead, I just resized the window to a smart size to try to only show the video.
Changed the video to a funny meme video...
*/
