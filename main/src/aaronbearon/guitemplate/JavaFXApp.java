package aaronbearon.guitemplate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();

        GridPane newPane = new GridPane();
        newPane.setAlignment(Pos.CENTER); // Set center alignment
        newPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        newPane.setHgap(5.5);
        newPane.setVgap(5.5); // Set vGap to 5.5px

        // Place nodes in the newPane
        newPane.add(new Label("First Name:"), 0, 0);
        newPane.add(new TextField(), 1, 0);
        newPane.add(new Label("MI:"), 0, 1);
        newPane.add(new TextField(), 1, 1);
        newPane.add(new Label("Last Name:"), 0, 2);
        newPane.add(new TextField(), 1, 2);
        Button btAdd = new Button("Add Name");
        newPane.add(btAdd, 1, 3);
        GridPane.setHalignment(btAdd, HPos.RIGHT);

        // Radio Button
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbGreen = new RadioButton("Green");
        RadioButton rbBlue = new RadioButton("Blue");
        newPane.getChildren().addAll(rbRed, rbGreen, rbBlue);

        ToggleGroup group = new ToggleGroup();
        rbRed.setToggleGroup(group);
        rbGreen.setToggleGroup(group);
        rbBlue.setToggleGroup(group);

        rbRed.setOnAction(e -> {
            if (rbRed.isSelected()) {
                // text.setFill(Color.RED);
            }
        });

        rbGreen.setOnAction(e -> {
            if (rbGreen.isSelected()) {
                // text.setFill(Color.GREEN);
            }
        });

        rbBlue.setOnAction(e -> {
            if (rbBlue.isSelected()) {
                // text.setFill(Color.BLUE);
            }
        });

        // Check box
        Font fontBoldItalic = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.ITALIC, 20);
        Font fontBold = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.REGULAR, 20);
        Font fontItalic = Font.font("Times New Roman",
                FontWeight.NORMAL, FontPosture.ITALIC, 20);
        Font fontNormal = Font.font("Times New Roman",
                FontWeight.NORMAL, FontPosture.REGULAR, 20);

        // text.setFont(fontNormal);

        VBox paneForCheckBoxes = new VBox(20);
        paneForCheckBoxes.setPadding(new Insets(5, 5, 5, 5));
        paneForCheckBoxes.setStyle("-fx-border-color: green");
        CheckBox chkBold = new CheckBox("Bold");
        CheckBox chkItalic = new CheckBox("Italic");
        paneForCheckBoxes.getChildren().addAll(chkBold, chkItalic);
        // newPane.setRight(paneForCheckBoxes);

        EventHandler<ActionEvent> handler = e -> {
            if (chkBold.isSelected() && chkItalic.isSelected()) {
                // text.setFont(fontBoldItalic); // Both check boxes checked
            } else if (chkBold.isSelected()) {
                // text.setFont(fontBold); // The Bold check box checked
            } else if (chkItalic.isSelected()) {
                // text.setFont(fontItalic); // The Italic check box checked
            } else {
                // text.setFont(fontNormal); // Both check boxes unchecked
            }
        };

        chkBold.setOnAction(handler);
        chkItalic.setOnAction(handler);

        //1. UI components -----------------------------
        // Label lbl1 = new Label("Enter a number:");
        TextField txtIn = new TextField();
        // Label lbl2 = new Label("Enter a number:");
        TextField txtIn2 = new TextField();
        // Label lbl3 = new Label("Result:");
        TextField txtOut = new TextField();
        // txtOut.setEditable(false);
        Button btnStart = new Button("Calculate");

        //2. Container --------------------------------
        VBox vbox = new VBox(10, txtIn, txtIn2, txtOut, btnStart);

        //3. Scene ------------------------------------
        Scene scene = new Scene(vbox, 300, 200);

        //4. Stage (Window) ----------------------------
        stage.setTitle("Multiply by 10");
        stage.setScene(scene);
        stage.show();

        //5. Event handler -----------------------------
        btnStart.setOnAction(e -> {
            int num = Integer.parseInt(txtIn.getText());
            int num2 = Integer.parseInt(txtIn2.getText());
            int result = num * num2;
            txtOut.setText(String.valueOf(result));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
