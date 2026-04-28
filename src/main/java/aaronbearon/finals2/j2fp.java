package aaronbearon.finals2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aaron Blum, CIST 2372 Java 2, Final Project
 * Description: Refer to the controller file.
 */
public class j2fp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("j2fp_ExoticMoves.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Exotic Moves");
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
