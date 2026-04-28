package aaronbearon.chapter16.lesson16;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 7 Part 1
 * Description: Change colors, sizes, and details for shapes and an image.
 */
public class J207_31 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        //* I set the imageUrl Resource to an image in the working directory.
        URL imageUrl = getClass().getResource("J207_31.gif");

        //* This is how you enable a path only in the working directory to be specified.
        assert imageUrl != null;
        ImageView germany = new ImageView(new Image(imageUrl.toExternalForm()));
        //* Changed the flag to Germany
        //* Changed the label content and color.
        Label lb1 = new Label("My grandmother's\ncountry: Germany", germany);
        lb1.setStyle("-fx-border-color: grey; -fx-border-width: 2");
        lb1.setContentDisplay(ContentDisplay.BOTTOM);

        //* Turned the black circle into a smaller red right triangle.
        Polygon triangle = new Polygon();
        triangle.setFill(Color.RED);
        triangle.getPoints().addAll(0.0, 0.0, 0.0, 35.0, 35.0, 35.0);
        Label lb2 = new Label("Triangle", triangle);
        lb2.setContentDisplay(ContentDisplay.TOP);
        lb2.setFont(new Font("Times New Roman", 15));
        lb2.setTextFill(Color.RED);

        //* Turned the black rectangle into a larger bright green parallelogram.
        Polygon parallelogram = new Polygon();
        parallelogram.setFill(Color.LIME);
        parallelogram.getPoints().addAll(100.0, 0.0, 20.0, 0.0, 0.0, 50.0, 80.0, 50.0);
        Label lb3 = new Label("Parallelogram", parallelogram);
        lb3.setContentDisplay(ContentDisplay.RIGHT);
        lb3.setFont(new Font("Times New Roman", 15));
        lb3.setTextFill(Color.LIME);

        //* Turned the black rectangle into a smaller green diamond.
        Polygon solidDiamond = new Polygon();
        solidDiamond.setFill(Color.GREEN);
        solidDiamond.getPoints().addAll(50.0, 12.5, 25.0, 0.0, 0.0, 12.5, 25.0, 25.0);
        Label lb4 = new Label("Diamond", solidDiamond); // new Ellipse(50, 50, 50, 25));
        lb4.setContentDisplay(ContentDisplay.LEFT);
        lb4.setFont(new Font("Times New Roman", 15));
        lb4.setTextFill(Color.GREEN);

        //* Notice above, the label is now the same color as the shape.

        //* The shape is now a smaller diamond with a blue outline.
        Polygon openDiamond = new Polygon();
        openDiamond.setFill(Color.WHITE);
        openDiamond.setStroke(Color.BLUE);
        openDiamond.getPoints().addAll(60.0, 15.0, 30.0, 0.0, 0.0, 15.0, 30.0, 30.0);

        //* label is in the diamond but lb5 is on top.
        //* Both labels are blue just like the diamond outline.
        StackPane stackPane = new StackPane();
        Label label = new Label("#");
        label.setFont(new Font("Times New Roman", 15));
        label.setTextFill(Color.BLUE);
        stackPane.getChildren().addAll(openDiamond, label);
        Label lb5 = new Label("A hashtag in a diamond", stackPane);
        lb5.setContentDisplay(ContentDisplay.BOTTOM);
        lb5.setFont(new Font("Times New Roman", 15));
        lb5.setTextFill(Color.BLUE);

        //* The above label names are different from original.
        //* Each label except for the flag is the color of its shape.
        //* Each label also has appropriate content.

        HBox pane = new HBox(20);
        pane.getChildren().addAll(lb1, lb2, lb3, lb4, lb5);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 750, 150);
        primaryStage.setTitle("LabelWithGraphic"); // Set the stage title
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

I Changed the built-in shapes to polygons and changed the flag to Germany.
Note: no need to submit a personal image. I got this image is from later problems.

*/
