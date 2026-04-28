package aaronbearon.chapter31.lesson8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 9
 * Description: Refer to the controller file.
 */
public class J208_39 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(J208_39.class.getResource("J208_39.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 250);
        stage.setTitle("TabsDemo");
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

Refer to controller.

*/
