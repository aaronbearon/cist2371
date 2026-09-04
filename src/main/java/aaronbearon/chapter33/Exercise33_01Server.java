package aaronbearon.chapter33;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Text area with a fixed minimum size
        TextArea taOutput = new TextArea();
        taOutput.setEditable(false);
        taOutput.setMinWidth(400);
        taOutput.setMaxWidth(400);

        // ScrollPane wrapping the text area underneath the top bar
        ScrollPane scrollPane = new ScrollPane(taOutput);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(scrollPane);

        // Window scene setup
        Scene scene = new Scene(mainPane, 300, 250);
        primaryStage.setTitle("Exercise33_02CServer");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
