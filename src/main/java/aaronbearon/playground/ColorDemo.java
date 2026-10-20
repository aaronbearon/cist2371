package aaronbearon.playground;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ColorDemo extends Application {
    private final double distance = 160;

    @Override
    public void start(Stage primaryStage) {
        StackPane mainPane = new StackPane();
        Circle[] circles = placeCircles(mainPane);
        Scene scene = new Scene(mainPane, 500, 400);
        primaryStage.setTitle("Color Fan");
        primaryStage.setScene(scene);
        primaryStage.show();
        for (int i = 0; i < circles.length; i++) {
            moveCircle(circles[i], i);
        }
    }

    public Circle[] placeCircles(StackPane stackPane) {
        Circle[] circles = new Circle[30];
        for (int i = 0; i < 30; i++) {
            circles[i] = new Circle(5);
            // circles[i].setManaged(false);
            build(circles[i], i);
            stackPane.getChildren().add(circles[i]);
        }

        Circle circle = new Circle(5);
        circle.setFill(Color.BLACK);
        circle.setStroke(Color.BLACK);
        stackPane.getChildren().add(circle);

        return circles;
    }

    public void build(Circle circle, int id) {
        double red;
        double green;
        double blue;
        red = getValue(id, 5);
        green = getValue(id, 15);
        blue = getValue(id, 25);
        circle.setFill(Color.color(red, green, blue, 1));
        circle.setStroke(Color.color(red, green, blue, 1));
        double cos = Math.cos(Math.toRadians(id * 12));
        double sin = Math.sin(Math.toRadians(id * 12));
        circle.setTranslateX(cos * distance);
        circle.setTranslateY(sin * distance);
    }

    public double getValue(int id, int modifiedId) {
        modifiedId += id;
        if (modifiedId >= 30) {
            modifiedId -= 30;
        }
        if (modifiedId >= 0 && modifiedId < 10) {
            return 1;
        } else if (modifiedId >= 10 && modifiedId < 15) {
            return (modifiedId * (0 - 0.2)) + 3;
        } else if (modifiedId >= 15 && modifiedId < 25) {
            return 0;
        } else {
            return (modifiedId * 0.2) - 5;
        }
    }

    public void moveCircle(Circle circle, int id) {
        double cos = Math.cos(Math.toRadians(id * 12));
        double sin = Math.sin(Math.toRadians(id * 12));
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1.5));
        transition.setNode(circle);
        transition.setByX(-cos * distance * 0.9);
        transition.setByY(-sin * distance * 0.9);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true);
        transition.setDelay(Duration.millis((id % 10) * 300));
        transition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
