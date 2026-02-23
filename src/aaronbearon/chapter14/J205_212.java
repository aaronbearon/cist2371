package aaronbearon.chapter14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 5 Part 12
 * Description:
 * Turn the hexagon into a pentagon by changing some of the numbers
 */
public class J205_212 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        //* These usages of "Polygon" are replaced with "Pentagon".
        Scene scene = new Scene(new Pentagon(), 400, 400);
        primaryStage.setTitle("Pentagon"); // Set the stage title
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

//* Different class name
class Pentagon extends Pane {
    private void paint() {
        // Create a polygon and place polygon to pane
        Polygon polygon = new Polygon();
        polygon.setFill(Color.WHITE);
        polygon.setStroke(Color.BLACK);
        ObservableList<Double> list = polygon.getPoints();

        double centerX = getWidth() / 2, centerY = getHeight() / 2;
        double radius = Math.min(getWidth(), getHeight()) * 0.4;

        // Add points to the polygon list
        //* Add 5 instead of 6 points to the polygon list
        for (int i = 0; i < 5; i++) {
            //* Math PI is now divided by 5
            //* Add 90 degrees counterclockwise for odd polygon to put point on top. (base on bottom)
            list.add(centerX + radius * Math.cos((2 * i * Math.PI / 5) + (Math.PI / 2)));
            list.add(centerY - radius * Math.sin((2 * i * Math.PI / 5) + (Math.PI / 2)));
        }

        getChildren().clear(); // Clear pane
        getChildren().add(polygon);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paint();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paint();
    }
}

/*

There was a hexagon before.
It was made with 6 points with the following formula:
point x: (centerX + radius * Math.cos(2 * i * Math.PI / n))
point y: (centerX - radius * Math.sin(2 * i * Math.PI / n))
where "n" is the number of sides.
I changed the for loop index to 5.
I also changed the number in the place of "n" to 5.
Then I rotated it 90 degrees counterclockwise to put the point on top.
That's because it's an odd polygon and I wanted the base on the bottom.

*/
