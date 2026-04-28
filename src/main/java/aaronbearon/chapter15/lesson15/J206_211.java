package aaronbearon.chapter15.lesson15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 11
 * Description:
 * Make special keys appear when pressing them.
 * Shift, Ctrl, and Alt for Windows.
 * The regular keys should continue to work.
 */
public class J206_211 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane and set its properties
        Pane pane = new Pane();
        Text text = new Text(20, 20, "A");

        pane.getChildren().add(text);
        //* Reformatted to separate to different lines
        text.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DOWN:
                    text.setY(text.getY() + 10);
                    break;
                case UP:
                    text.setY(text.getY() - 10);
                    break;
                case LEFT:
                    text.setX(text.getX() - 10);
                    break;
                case RIGHT:
                    text.setX(text.getX() + 10);
                    break;
                //* Added these three keys with text
                case SHIFT:
                    text.setText("Shift");
                    break;
                case CONTROL:
                    text.setText("Ctrl");
                    break;
                case ALT:
                    text.setText("Alt");
                    break;
                default:
                    if (!e.getText().isEmpty())
                        text.setText(e.getText());
            }
        });

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("KeyEventDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        text.requestFocus(); // text is focused to receive key input
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

Without the focus request now in line 61, the A in the window wouldn't be able to change at all.
I added Shift, Ctrl, and Alt to the switch statement.
I specifically did those because I use Windows.

*/
