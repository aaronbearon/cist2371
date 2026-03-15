package aaronbearon.GuiTemplate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    @Override
    public void start(Stage stage) {

        //1. UI components -----------------------------
        Label lbl1 = new Label("Enter a number:");
        TextField txtIn = new TextField();
        Label lbl2 = new Label("Result:");
        TextField txtOut = new TextField();
        txtOut.setEditable(false);
        Button btnStart = new Button("Calculate");

        //2. Container --------------------------------
        VBox vbox = new VBox(10, txtIn, txtOut, btnStart);

        //3. Scene ------------------------------------
        Scene scene = new Scene(vbox, 300, 200);

        //4. Stage (Window) ----------------------------
        stage.setTitle("Multiply by 10");
        stage.setScene(scene);
        stage.show();

        //5. Event handler -----------------------------
        btnStart.setOnAction(e -> {
            int num = Integer.parseInt(txtIn.getText());
            int result = num * 10;
            txtOut.setText(String.valueOf(result));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
