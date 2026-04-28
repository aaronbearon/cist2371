package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 11
 * Description:
 * Make a "quarter pizza" or quarter of a circle with your favorite color
 */
public class J205_211 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        //* Angle starts at 0 degrees (pointing right) and goes 90 degrees counterclockwise.
        //* This "quarter pizza" stays red because red is my favorite color.
        Arc arc1 = new Arc(150, 100, 80, 80, 0, 90); // Create an arc
        arc1.setFill(Color.RED); // Set fill color
        arc1.setType(ArcType.ROUND); // Set arc type
        //* This gives the "quarter pizza" an outline.
        arc1.setStroke(Color.BLACK);

        Arc arc2 = new Arc(150, 100, 80, 80, 30 + 90, 35);
        arc2.setFill(Color.WHITE);
        arc2.setType(ArcType.OPEN);
        arc2.setStroke(Color.BLACK);

        Arc arc3 = new Arc(150, 100, 80, 80, 30 + 180, 35);
        arc3.setFill(Color.WHITE);
        arc3.setType(ArcType.CHORD);
        arc3.setStroke(Color.BLACK);

        Arc arc4 = new Arc(150, 100, 80, 80, 30 + 270, 35);
        arc4.setFill(Color.GREEN);
        arc4.setType(ArcType.CHORD);
        arc4.setStroke(Color.BLACK);

        // Create a group and add nodes to the group
        Group group = new Group();
        //* A proper label is now assigned to the modified arc.
        group.getChildren().addAll(new Text(210, 40, "\"quarter red pizza\""),
                arc1, new Text(20, 40, "arc2: open"), arc2,
                new Text(20, 170, "arc3: chord"), arc3,
                new Text(210, 170, "arc4: chord"), arc4);

        // Create a scene and place it in the stage
        Scene scene = new Scene(new BorderPane(group), 300, 200);
        primaryStage.setTitle("ShowArc"); // Set the stage title
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

An arc has 6 fields.
The first two are the location of the center.
The second two are the radius dimensions creating a virtual ellipse.
This means the radius goes to the edge of the virtual ellipse.
The last pair of fields are the starting angle direction and the length (in counterclockwise degrees).

My quarter pizza is still red because red is my favorite color.

I set the last two fields to 0, 90 to put the slice entirely in the upper right quadrant.
I also added a black outline to the quarter pizza, which now has a proper label.

There are three other arcs scattered around the circle which are the same as before.

*/
