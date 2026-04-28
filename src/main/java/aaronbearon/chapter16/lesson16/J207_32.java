package aaronbearon.chapter16.lesson16;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 7 Part 2
 * Description: Show off the flags of 5 countries.
 */
public class J207_32 extends Application {
    // Declare an array of Strings for flag titles
    //* I picked 5 from the list and reordered them.
    private String[] flagTitles = {
            "United States of America", "India", "United Kingdom", "Germany", "China"};

    // Declare an ImageView array for the national flags of countries
    //* I picked 5 from the list and reordered them.
    //* They're the same 5 as above and in that same order.
    private ImageView[] ImageViews = {
            new ImageView("https://liveexample.pearsoncmg.com/html/image/us.gif"),
            new ImageView("https://liveexample.pearsoncmg.com/html/image/india.gif"),
            new ImageView("https://liveexample.pearsoncmg.com/html/image/uk.gif"),
            new ImageView("https://liveexample.pearsoncmg.com/html/image/germany.gif"),
            new ImageView("https://liveexample.pearsoncmg.com/html/image/china.gif")
    };

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        ListView<String> lv = new ListView<>
                (FXCollections.observableArrayList(flagTitles));
        //* Changed starting size to remove scroll bars at big enough window size.
        lv.setPrefSize(150, 150);
        lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create a pane to hold image views
        FlowPane imagePane = new FlowPane(10, 10);
        BorderPane pane = new BorderPane();
        pane.setLeft(new ScrollPane(lv));
        pane.setCenter(imagePane);

        lv.getSelectionModel().selectedItemProperty().addListener(
                ov -> {
                    imagePane.getChildren().clear();

                    //* Allows only two countries to be selected at a time.
                    int count = 0;
                    for (Integer i : lv.getSelectionModel().getSelectedIndices()) {
                        if (count < 2) {
                            imagePane.getChildren().add(ImageViews[i]);
                        } else {
                            break;
                        }

                        count++;
                    }
                });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ListViewDemo"); // Set the stage title
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

I removed all but 5 of the given countries.
I removed the ability to select more than two.

*/
