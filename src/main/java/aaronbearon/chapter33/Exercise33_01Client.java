package aaronbearon.chapter33;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Aaron Blum, CIST 2373 Java 3, Lab 3, Client.
 */
public class Exercise33_01Client extends Application {
    Socket socket = null;
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    TextArea taOutput;
    TextField tfWeight;
    TextField tfHeight;

    @Override
    public void start(Stage primaryStage) throws IOException {
        final int SPACING = 5;
        GridPane gridPane = new GridPane();
        gridPane.setHgap(SPACING);
        gridPane.setVgap(SPACING);
        gridPane.setPadding(new Insets(SPACING));
        gridPane.setAlignment(Pos.CENTER_LEFT);

        Label lblWeight = new Label("Weight in pounds");
        Label lblHeight = new Label("Height in inches");

        tfWeight = new TextField();
        tfHeight = new TextField();
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

        taOutput = new TextArea();
        taOutput.setEditable(false);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(gridPane);
        mainPane.setCenter(taOutput);

        Scene scene = new Scene(mainPane, 600, 400);
        primaryStage.setTitle("Exercise33_01Client");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        btnSubmit.setOnAction(_ -> invokeBmi());

        try {
            socket = new Socket("localhost", 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            // If we can't connect to the server, just die immediately.
            // Nothing will work. Maybe in another lab we can write a loop
            // to retry connecting to the server if it fails or breaks.
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void stop() throws Exception {
        if (socket != null) {
            socket.close();
        }
    }

    /**
     * Send weight and height to server.
     * Receive BMI from server.
     */
    private void invokeBmi() {
        try {
            double weight = Double.parseDouble(tfWeight.getText());
            double height = Double.parseDouble(tfHeight.getText());
            toServer.writeDouble(weight);
            toServer.writeDouble(height);
            toServer.flush();
            double bmi = fromServer.readDouble();
            String status = fromServer.readUTF();
            taOutput.clear();
            taOutput.appendText(String.format("Weight is %.2f%n", weight));
            taOutput.appendText(String.format("Height is %.2f%n", height));
            taOutput.appendText(String.format("BMI is %.2f. %s%n", bmi, status));
        } catch (Exception ex) {
            System.out.println("DEBUG exception in client button");
            taOutput.appendText(ex + String.format("%n"));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
