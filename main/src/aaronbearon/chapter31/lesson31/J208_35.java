package aaronbearon.chapter31.lesson31;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 5
 * Description: Move the entire graph to the right of the window.
 */
public class J208_35 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a polyline to draw a sine curve
        Polyline polyline = new Polyline();
        for (double angle = -360; angle <= 360; angle++) {
            polyline.getPoints().addAll(angle, Math.sin(Math.toRadians(angle)));
        }
        polyline.setTranslateY(100);
        polyline.setTranslateX(200);
        polyline.setScaleX(0.5);
        polyline.setScaleY(50);
        polyline.setStrokeWidth(1.0 / 25);

        // Draw x-axis
        Line line1 = new Line(10, 100, 420, 100);
        Line line2 = new Line(420, 100, 400, 90);
        Line line3 = new Line(420, 100, 400, 110);

        // Draw y-axis
        Line line4 = new Line(200, 10, 200, 200);
        Line line5 = new Line(200, 10, 190, 30);
        Line line6 = new Line(200, 10, 210, 30);

        // Draw x, y axis labels
        Text text1 = new Text(380, 70, "X");
        Text text2 = new Text(220, 20, "Y");

        //* Add nodes to a group
        Group group = new Group();
        group.getChildren().addAll(polyline, line1, line2, line3, line4, line5, line6, text1, text2);

        //* Add the group to the stack pane with baseline right and padding of 10.
        StackPane pane = new StackPane();
        pane.getChildren().add(group);
        StackPane.setAlignment(group, Pos.BASELINE_RIGHT);
        StackPane.setMargin(group, new Insets(0, 10, 0 ,0));

        Scene scene = new Scene(pane, 450, 200);
        primaryStage.setTitle("ScaleDemo"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }

    // Launch the program from command-line
    public static void main(String[] args) {
        launch(args);
    }
}

/*

I assigned all the nodes to a group, and then added that group to a stack pane.
I set the stack pane's alignment to baseline right with right padding of 10;
This puts the graph 10 px from the very right of the window.

*/
