package aaronbearon.chapter15.lesson15;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 6
 * Description:
 * Update necessary members and their usages to make the circle be a square instead.
 */
//* The circle with mouse and key required both class files.
//* Everything is combined into 1 file.
//* Every instance of circle is replaced with rectangle, with necessary properties changed.
//* Except for the title which says ControlSquare
public class J206_26 extends Application {
    private RectanglePane rectanglePane = new RectanglePane();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Hold two buttons in an HBox
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Button btEnlarge = new Button("Enlarge");
        Button btShrink = new Button("Shrink");
        hBox.getChildren().add(btEnlarge);
        hBox.getChildren().add(btShrink);

        // Create and register the handler
        btEnlarge.setOnAction(e -> rectanglePane.enlarge());
        btShrink.setOnAction(e -> rectanglePane.shrink());

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(rectanglePane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 200, 150);
        //* Proper title
        primaryStage.setTitle("ControlSquare"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        rectanglePane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                rectanglePane.enlarge();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                rectanglePane.shrink();
            }
        });

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                rectanglePane.enlarge();
            } else if (e.getCode() == KeyCode.DOWN) {
                rectanglePane.shrink();
            }
        });
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

//* Necessary property changes are made here.
class RectanglePane extends StackPane {
    private Rectangle rectangle = new Rectangle(50, 50);

    public RectanglePane() {
        getChildren().add(rectangle);
        rectangle.setStroke(Color.BLACK);
        //* I changed the color here just like in problem 3.
        rectangle.setFill(Color.RED);
    }

    public void enlarge() {
        rectangle.setWidth(rectangle.getWidth() + 2);
        rectangle.setHeight(rectangle.getHeight() + 2);
    }

    public void shrink() {
        rectangle.setWidth(rectangle.getWidth() > 2 ?
                rectangle.getWidth() - 2 : rectangle.getWidth());
        rectangle.setHeight(rectangle.getHeight() > 2 ?
                rectangle.getHeight() - 2 : rectangle.getHeight());
    }
}

/*

This is the full working version of problem 3.
I refactored and included the mouse and key class, along with the CirclePane (now RectanglePane) class.
I changed everywhere it said Circle (or circle) to Rectangle (or rectangle).
The title is unique in which it has "Square" instead.
The properties are changed from radius to width and height.

*/
