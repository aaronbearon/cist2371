package aaronbearon.chapter16.lesson7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 7 Part 10
 * Description: Demo various text styles with user controls.
 */
public class J207_310 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(J207_310.class.getResource("style-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);
        stage.setTitle("StyleDemo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(J207_310.class, args);
    }
}

/*
I rewrote with scene builder.
*/
