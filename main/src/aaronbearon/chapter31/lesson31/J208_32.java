package aaronbearon.chapter31.lesson31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 2
 * Description: Put the circles all on one row and add a new one to a new pane.
 * Change their colors and sizes.
 */
public class J208_32 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        HBox hBox = new HBox(5);
        //* Load window is big enough to fully show all the panes.
        Scene scene = new Scene(hBox, 500, 250);
        scene.getStylesheets().add("mystyle.css"); // Load the stylesheet

        Pane pane1 = new Pane();
        //* Set all circles to:
        //* Y values 100 and radius sizes 20.
        Circle circle1 = new Circle(50, 100, 20);
        Circle circle2 = new Circle(150, 100, 20);
        Circle circle3 = new Circle(100, 100, 20);
        pane1.getChildren().addAll(circle1, circle2, circle3);
        pane1.getStyleClass().add("border");

        //* Circles 1 and 2 are now gray.
        //* Circle 3, which is between them, is now cyan.
        circle1.getStyleClass().add("plaincircle"); // Add a style class
        circle2.getStyleClass().add("plaincircle"); // Add a style class
        circle3.setId("cyancircle"); // Add a style id

        Pane pane2 = new Pane();
        Circle circle4 = new Circle(100, 100, 20);
        circle4.getStyleClass().addAll("circleborder", "plainCircle");
        //* Circle 4 is now magenta.
        circle4.setId("magentacircle"); // Add a style class
        pane2.getChildren().add(circle4);
        pane2.getStyleClass().add("border");

        //* Added third pane
        Pane pane3 = new Pane();
        Circle circle5 = new Circle(100, 100, 20);
        circle5.getStyleClass().addAll("circleborder", "plainCircle");
        //* Circle 5 is now yellow.
        circle5.setId("yellowcircle"); // Add a style class
        pane3.getChildren().add(circle5);
        pane3.getStyleClass().add("border");

        hBox.getChildren().addAll(pane1, pane2, pane3);

        primaryStage.setTitle("StyleSheetDemo"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }

    // Launch the program from command-line
    public static void main(String[] args) {
        launch(args);
    }
}

/*

I added a third pane with a black border and added it to the hBox.
I also put a new circle there.
Using the CSS file, I made the white circles gray.
The colored circles are now cyan, magenta and yellow.
I made them small enough to prevent overlapping and put them all on one row.

*/
