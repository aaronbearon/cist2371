package aaronbearon.chapter15.lesson15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 12
 * Description:
 * Change the thickness of the 3 clock hands.
 * Place the 12 numbers around the clock non-manually.
 * Change the clock's position on the screen.
 */
public class J206_212 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        ClockPane clock = new ClockPane(); // Create a clock

        // Create a handler for animation
        EventHandler<ActionEvent> eventHandler = e -> {
            clock.setCurrentTime(); // Set a new clock time
        };

        // Create an animation for a running clock
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

        // Create a scene and place it in the stage
        Scene scene = new Scene(clock, 250, 250);
        primaryStage.setTitle("ClockAnimation"); // Set the stage title
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

class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;

    /** Construct a default clock with the current time */
    public ClockPane() {
        setCurrentTime();
    }

    /** Construct a clock with specified hour, minute, and second */
    public ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /** Return hour */
    public int getHour() {
        return hour;
    }

    /** Set a new hour */
    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    /** Return minute */
    public int getMinute() {
        return minute;
    }

    /** Set a new minute */
    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    /** Return second */
    public int getSecond() {
        return second;
    }

    /** Set a new second */
    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    /* Set the current time for the clock */
    public void setCurrentTime() {
        // Construct a calendar for the current date and time
        Calendar calendar = new GregorianCalendar();

        // Set current hour, minute and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock(); // Repaint the clock
    }

    /** Paint the clock */
    private void paintClock() {
        // Initialize clock parameters
        //* Adjusted parameters to put the whole clock in the upper left.
        double clockRadius =
                Math.min(getWidth(), getHeight()) * 0.8 * 0.5 / 2;
        double centerX = getWidth() / 4;
        double centerY = getHeight() / 4;

        // Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        //* Replace the manual 3, 6, 9, 12 with a loop and some math
        //* This includes the array
        Text[] t = new Text[12];
        for (int i = 0; i < t.length; i++) {
            double angle = Math.toRadians(i * 30 - 60);
            //* The 0.9 keeps the numbers slightly inside the clock border
            double x = centerX + clockRadius * 0.9 * Math.cos(angle);
            double y = centerY + clockRadius * 0.9 * Math.sin(angle);

            //* Measure the text node and then manually center it where we want it to go.
            //* Instead of using bottom-left baseline.
            t[i] = new Text(x, y, String.valueOf(i + 1));
            x -= t[i].getLayoutBounds().getWidth() / 2;
            //* The -4 is needed because the text dimensions are slightly different.
            y += t[i].getLayoutBounds().getHeight() / 2 - 4;
            t[i].setX(x);
            t[i].setY(y);
        }

        // Draw second hand
        double sLength = clockRadius * 0.8;
        double secondX = centerX + sLength *
                Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength *
                Math.cos(second * (2 * Math.PI / 60));
        Line sLine = new Line(centerX, centerY, secondX, secondY);
        //* Thicker hand
        sLine.setStrokeWidth(2);
        sLine.setStroke(Color.RED);

        // Draw minute hand
        double mLength = clockRadius * 0.65;
        double xMinute = centerX + mLength *
                Math.sin(minute * (2 * Math.PI / 60));
        double minuteY = centerY - mLength *
                Math.cos(minute * (2 * Math.PI / 60));
        Line mLine = new Line(centerX, centerY, xMinute, minuteY);
        //* Thicker hand
        mLine.setStrokeWidth(4);
        mLine.setStroke(Color.BLUE);

        // Draw hour hand
        double hLength = clockRadius * 0.5;
        double hourX = centerX + hLength *
                Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength *
                Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        Line hLine = new Line(centerX, centerY, hourX, hourY);
        //* Thicker hand
        hLine.setStrokeWidth(5);
        hLine.setStroke(Color.GREEN);

        //* Clock numbers are added by the for loop
        getChildren().clear(); // Clear the pane
        getChildren().addAll(circle);
        for (int i = 0; i < 12; i++) {
            getChildren().add(t[i]);
        }

        getChildren().addAll(sLine, mLine, hLine);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintClock();
    }
}

/*

This is like the clock from lesson 5, except that the clock is live.
That's because a keyframe is added to the animation with a duration of 1 second.
It also gets the current time every second.

I adjusted the thickness of the hands.

I used a for loop to make the 12 numbers. I used careful math to make sure the numbers were in the right place.
Since the text was centered in the bottom left of the text display, I used getLayoutBounds().
I changed each dimension by half the display fields.
Since the dimensions with text display are not the same, I needed to subtract 4 from the x dimension.

*/
