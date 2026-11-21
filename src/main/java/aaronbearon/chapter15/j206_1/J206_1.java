package aaronbearon.chapter15.j206_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 6
 * Description:
 * Take an input number and determine whether it's even or odd.
 */
public class J206_1 extends Application {
    // The title and .fxml filenames are now different
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(J206_1.class.getResource("even-odd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Even or Odd");
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

The nodes are in the fxml file.
The code is in the controller file.

*/
