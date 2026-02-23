package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 8
 * Description:
 * Replace the text in strikethrough with:
 * your name, email, and last 4-digit phone number
 */
public class J205_28 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane to hold the texts
        Pane pane = new Pane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        Text text1 = new Text(20, 20, "Programming is fun");
        text1.setFont(Font.font("Courier", FontWeight.BOLD,
                FontPosture.ITALIC, 15));
        pane.getChildren().add(text1);

        Text text2 = new Text(60, 60, "Programming is fun\nDisplay text");
        pane.getChildren().add(text2);

        //* Changed the text to name, email, and last 4-digit phone number
        Text text3 = new Text(10, 100, "Aaron Blum\r\ngoogolpup@gmail.com\r\n1417");
        text3.setFill(Color.RED);
        //* There's no more strikethrough
        text3.setUnderline(true); // Underline for text3
        pane.getChildren().add(text3);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("ShowText"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
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

A header named "Programming is fun" is displayed in bold at width and height of 20.
Then the same text is displayed indented at width 60 and isn't bold.
I replaced the text in strikethrough with my name, email, and last 4-digit phone number.
That text has the same properties (including underlined and red) except that the strikethrough is gone.

*/
