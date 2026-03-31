package aaronbearon.chapter18.lesson9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 9 Part 6
 * Description: Refer to the controller file.
 */
public class J209_6 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("J209_6.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Square Fractal");
        stage.setScene(scene);
        stage.show();
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

Refer to the controller file.

*/
