package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 10
 * Description:
 * Add another rectangle, but with your own color and proper label
 */
public class J205_210 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create rectangles
        Rectangle r1 = new Rectangle(25, 10, 60, 30);
        r1.setStroke(Color.BLACK);
        r1.setFill(Color.WHITE);
        Rectangle r2 = new Rectangle(25, 50, 60, 30);
        Rectangle r3 = new Rectangle(25, 90, 60, 30);
        r3.setArcWidth(15);
        r3.setArcHeight(25);
        //* Added a rectangle below the rest of the rectangles with 130 x property.
        //* The width is longer and is also underneath the rotating rectangles.
        Rectangle r4 = new Rectangle(25, 130, 175, 30);
        r4.setStroke(Color.YELLOW);
        r4.setFill(Color.GREEN);

        // Create a group and add nodes to the group
        Group group = new Group();
        //* Added and positioned new text for Rectangle r4 and added r4.
        group.getChildren().addAll(new Text(10, 27, "r1"), r1,
                new Text(10, 67, "r2"), r2, new Text(10, 107, "r3"), r3,
                new Text(10, 147, "r4"), r4);

        for (int i = 0; i < 4; i++) {
            Rectangle r = new Rectangle(100, 50, 100, 30);
            r.setRotate(i * 360 / 8);
            r.setStroke(Color.color(Math.random(), Math.random(),
                    Math.random()));
            r.setFill(Color.WHITE);
            group.getChildren().add(r);
        }

        // Create a scene and place it in the stage
        //* Added extra height to make the new rectangle fully visible upon loading.
        Scene scene = new Scene(new BorderPane(group), 250, 200);
        primaryStage.setTitle("ShowRectangle"); // Set the stage title
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

Three rectangles are at insets (25, y, 60, 30).
This y is different to set the rectangle heights.
Rectangle r1 is white. Rectangles r2 and r3 are black.
Rectangle r3 is curved with arcWidth, arcHeight at 15, 25.
Four wider rectangles are created with the for loop.
They're random colors and on the right of rectangles r1-r3 (which have labels next to them).
Each colored rectangle is 45 more degrees clockwise than the previous.
I made a Rectangle r4 with a label underneath the whole thing and extended the window load height.
This rectangle covers the whole width of the above structure.
It's also GREEN with a YELLOW outline.

*/
