package aaronbearon.chapter16.lesson16;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 7 Part 9
 * Description: Demo various text styles with user controls.
 */
public class J207_39 extends RadioButtonDemo {
    @Override // Override the getPane() method in the super class
    protected BorderPane getPane() {
        BorderPane pane = super.getPane();

        BorderPane paneForTextField = new BorderPane();
        paneForTextField.setPadding(new Insets(5, 5, 5, 5));
        paneForTextField.setStyle("-fx-border-color: green");
        paneForTextField.setLeft(new Label("Enter a new message: "));

        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField.setCenter(tf);
        pane.setTop(paneForTextField);

        tf.setOnAction(e -> text.setText(tf.getText()));

        return pane;
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

class RadioButtonDemo extends CheckBoxDemo {
    @Override // Override the getPane() method in the super class
    protected BorderPane getPane() {
        BorderPane pane = super.getPane();

        VBox paneForRadioButtons = new VBox(20);
        paneForRadioButtons.setPadding(new Insets(5, 5, 5, 5));
        paneForRadioButtons.setStyle
                ("-fx-border-width: 2px; -fx-border-color: green");

        //* I added yellow and purple buttons.
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbYellow = new RadioButton("Yellow");
        RadioButton rbGreen = new RadioButton("Green");
        RadioButton rbBlue = new RadioButton("Blue");
        RadioButton rbPurple = new RadioButton("Purple");
        paneForRadioButtons.getChildren().addAll(rbRed, rbYellow, rbGreen, rbBlue, rbPurple);
        pane.setLeft(paneForRadioButtons);

        ToggleGroup group = new ToggleGroup();
        //* Added the buttons to the group.
        rbRed.setToggleGroup(group);
        rbYellow.setToggleGroup(group);
        rbGreen.setToggleGroup(group);
        rbBlue.setToggleGroup(group);
        rbPurple.setToggleGroup(group);

        //* Added actions for yellow and purple buttons.
        EventHandler<ActionEvent> handler = e -> {
            if (rbRed.isSelected()) {
                text.setFill(Color.RED);
            } else if (rbYellow.isSelected()) {
                text.setFill(Color.YELLOW);
            } else if (rbGreen.isSelected()) {
                text.setFill(Color.GREEN);
            } else if (rbBlue.isSelected()) {
                text.setFill(Color.BLUE);
            } else if (rbPurple.isSelected()) {
                text.setFill(Color.PURPLE);
            } else {
                text.setFill(Color.BLACK);
            }
        };
        rbRed.setOnAction(handler);
        rbYellow.setOnAction(handler);
        rbGreen.setOnAction(handler);
        rbBlue.setOnAction(handler);
        rbPurple.setOnAction(handler);

        return pane;
    }
}

class CheckBoxDemo extends ButtonDemo {
    @Override // Override the getPane() method in the super class
    protected BorderPane getPane() {
        BorderPane pane = super.getPane();

        Font fontBoldItalic = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.ITALIC, 20);
        Font fontBold = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.REGULAR, 20);
        Font fontItalic = Font.font("Times New Roman",
                FontWeight.NORMAL, FontPosture.ITALIC, 20);
        Font fontNormal = Font.font("Times New Roman",
                FontWeight.NORMAL, FontPosture.REGULAR, 20);

        text.setFont(fontNormal);

        //* I added underline and strikethrough check boxes
        VBox paneForCheckBoxes = new VBox(20);
        paneForCheckBoxes.setPadding(new Insets(5, 5, 5, 5));
        paneForCheckBoxes.setStyle("-fx-border-color: green");
        CheckBox chkBold = new CheckBox("Bold");
        CheckBox chkItalic = new CheckBox("Italic");
        CheckBox chkUnderline = new CheckBox("Underline");
        CheckBox chkStrikethrough = new CheckBox("Strike-through");
        paneForCheckBoxes.getChildren().addAll(chkBold, chkItalic, chkUnderline, chkStrikethrough);
        pane.setRight(paneForCheckBoxes);

        EventHandler<ActionEvent> handler = e -> {
            if (chkBold.isSelected() && chkItalic.isSelected()) {
                text.setFont(fontBoldItalic); // Both check boxes checked
            } else if (chkBold.isSelected()) {
                text.setFont(fontBold); // The Bold check box checked
            } else if (chkItalic.isSelected()) {
                text.setFont(fontItalic); // The Italic check box checked
            } else {
                text.setFont(fontNormal); // Both check boxes unchecked
            }

            //* I Added these to set if they're checked.
            text.setUnderline(chkUnderline.isSelected());
            text.setStrikethrough(chkStrikethrough.isSelected());
        };

        chkBold.setOnAction(handler);
        chkItalic.setOnAction(handler);
        //* These are the new handlers for underline and strikethrough.
        chkUnderline.setOnAction(handler);
        chkStrikethrough.setOnAction(handler);

        return pane; // Return a new pane
    }
}

class ButtonDemo extends Application {
    protected Text text = new Text(50, 50, "JavaFX Programming");

    protected BorderPane getPane() {
        HBox paneForButtons = new HBox(20);
        Button btLeft = new Button("Left",
                new ImageView("https://liveexample.pearsoncmg.com/html/image/left.gif"));
        Button btRight = new Button("Right",
                new ImageView("https://liveexample.pearsoncmg.com/html/image/right.gif"));
        paneForButtons.getChildren().addAll(btLeft, btRight);
        paneForButtons.setAlignment(Pos.CENTER);
        paneForButtons.setStyle("-fx-border-color: green");

        BorderPane pane = new BorderPane();
        pane.setBottom(paneForButtons);

        Pane paneForText = new Pane();
        paneForText.getChildren().add(text);
        pane.setCenter(paneForText);

        btLeft.setOnAction(e -> text.setX(text.getX() - 10));
        btRight.setOnAction(e -> text.setX(text.getX() + 10));

        return pane;
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        //* Customized load size
        Scene scene = new Scene(getPane(), 600, 300);
        //* New title
        primaryStage.setTitle("StyleDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}

/*
I carefully combined and ordered all the example classes.
I made two new colors and two new text styles.
*/
