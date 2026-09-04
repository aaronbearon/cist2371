package aaronbearon.chapter33;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Exercise33_01Client extends Application {
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    @Override
    public void start(Stage primaryStage) {
        final int SPACING = 5;
        GridPane gridPane = new GridPane();
        gridPane.setHgap(SPACING);
        gridPane.setVgap(SPACING);
        gridPane.setPadding(new Insets(SPACING));
        gridPane.setAlignment(Pos.CENTER_LEFT);

        Label lblWeight = new Label("Weight in pounds");
        Label lblHeight = new Label("Height in inches");

        TextField tfWeight = new TextField();
        TextField tfHeight = new TextField();
        tfWeight.setAlignment(Pos.BASELINE_RIGHT);
        tfHeight.setAlignment(Pos.BASELINE_RIGHT);
        tfWeight.setPrefColumnCount(SPACING);
        tfHeight.setPrefColumnCount(SPACING);

        Button btnSubmit = new Button("Submit");

        gridPane.add(lblWeight, 0, 0);
        gridPane.add(tfWeight, 1, 0);
        gridPane.add(lblHeight, 0, 1);
        gridPane.add(tfHeight, 1, 1);
        gridPane.add(btnSubmit, 2, 1);

        TextArea taOutput = new TextArea();
        taOutput.setEditable(false);
        taOutput.setMinSize(400, 300);
        taOutput.setMaxSize(400, 300);

        ScrollPane scrollPane = new ScrollPane(taOutput);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(gridPane);
        mainPane.setCenter(scrollPane);

        Scene scene = new Scene(mainPane, 300, 250);
        primaryStage.setTitle("Exercise33_02Client");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        btnSubmit.setOnAction(_ -> {
            try {
                double weight = Double.parseDouble(tfWeight.getText());
                double height = Double.parseDouble(tfHeight.getText());
                toServer.writeDouble(weight);
                toServer.writeDouble(height);
                double BMI = fromServer.readDouble();
                String status = fromServer.readUTF();
                taOutput.clear();
                taOutput.appendText("Weight: " + weight + "\r\n");
                taOutput.appendText("Height: " + height + "\r\n");
                taOutput.appendText("BMI: " + BMI + ". " + status + "\r\n");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        try (Socket socket = new Socket("localhost", 8888)) {
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            taOutput.appendText(ex + "\r\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
