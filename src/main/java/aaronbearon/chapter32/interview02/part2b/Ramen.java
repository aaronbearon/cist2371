package aaronbearon.chapter32.interview02.part2b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Ramen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Ramen.class.getResource("Ramen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ramen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*

Refer to controller.

*/
