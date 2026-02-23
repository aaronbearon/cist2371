package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//* Required for URL class
import java.net.URL;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 5b
 * Description:
 * Replace the image from part a with one from the user's computer
 */
public class J205_25b extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a border pane
        BorderPane pane = new BorderPane();

        // Place nodes in the pane
        pane.setTop(getHBox());
        pane.setLeft(getVBox());

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("ShowHBoxVBox"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private HBox getHBox() {
        HBox hBox = new HBox(15); // Create an HBox with 15px spacing
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setStyle("-fx-background-color: gold");
        hBox.getChildren().add(new Button("Computer Science"));
        hBox.getChildren().add(new Button("Chemistry"));

        //* I set the imageUrl Resource to an image in the working directory.
        URL imageUrl = getClass().getResource("J205_25b.jpg");

        //* This is how you enable a path only in the working directory to be specified.
        assert imageUrl != null;
        ImageView imageView = new ImageView(new Image(imageUrl.toExternalForm()));

        hBox.getChildren().add(imageView);
        return hBox;
    }

    private VBox getVBox() {
        VBox vBox = new VBox(15); // Create a VBox with 15px spacing
        vBox.setPadding(new Insets(15, 5, 5, 5));
        vBox.getChildren().add(new Label("Courses"));

        Label[] courses = {new Label("CSCI 1301"), new Label("CSCI 1302"),
                new Label("CSCI 2410"), new Label("CSCI 3720")};

        for (Label course : courses) {
            VBox.setMargin(course, new Insets(0, 0, 0, 15));
            vBox.getChildren().add(course);
        }

        return vBox;
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

Same as before but with an image of trees from my computer.
The image, named J205_25b.jpg, must be submitted.
Otherwise, the image won't show up when the user runs the program.

I used toExternalForm() to avoid having to include the complete path.
        That method gave an IDE warning in case the source wasn't there.
        So the IDE suggested that I put the line below which "handles" a NULL "exception".
        assert imageUrl != null;

*/
