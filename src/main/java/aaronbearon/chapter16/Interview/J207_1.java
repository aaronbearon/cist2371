package aaronbearon.chapter16.Interview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class J207_1 extends Application {
    @Override
    public void start(Stage stage) {

    VBox vbox = new VBox(10);

        // Radio Button
        RadioButton rbInt = new RadioButton("int");
        RadioButton rbFloat = new RadioButton("float");

        ToggleGroup group = new ToggleGroup();
        rbInt.setToggleGroup(group);
        rbFloat.setToggleGroup(group);

        TextField txtField = new TextField();
        Button btnSubmit = new Button("Generate");

        btnSubmit.setOnAction(e -> {
            if (rbInt.isSelected()) {
                txtField.setText(String.valueOf((int) (Math.random() * 100)));
            }

            if (rbFloat.isSelected()) {
                txtField.setText(String.valueOf(Math.random()));
            }
        });

        vbox.getChildren().addAll(rbInt, rbFloat, txtField, btnSubmit);

    //3. Scene ------------------------------------
    Scene scene = new Scene(vbox, 300, 200);

    //4. Stage (Window) ----------------------------
        stage.setTitle("Random numbers");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
