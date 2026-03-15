package aaronbearon.chapter16.lesson16;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class J207_35 extends Application {
    // Declare an array of Strings for flag titles
    //* I picked 5 from the list and reordered them.
    private String[] flagTitles = {
            "United States of America", "India", "United Kingdom", "Germany", "China"};

    // Declare an ImageView array for the national flags of countries
    //* I picked 5 from the list and reordered them.
    //* They're the same 5 as above and in that same order.
    private ImageView[] flagImage = {
            new ImageView("https://liveexample.pearsoncmg.com/html/image/us.gif"),
            new ImageView("https://liveexample.pearsoncmg.com/html/image/india.gif"),
            new ImageView("https://liveexample.pearsoncmg.com/html/image/uk.gif"),
            new ImageView("https://liveexample.pearsoncmg.com/html/image/germany.gif"),
            new ImageView("https://liveexample.pearsoncmg.com/html/image/china.gif")
    };

    // Declare an array of strings for flag descriptions
    //* There's only 5 now.
    private String[] flagDescription = new String[5];

    // Declare and create a description pane
    private DescriptionPane descriptionPane = new DescriptionPane();

    // Create a combo box for selecting countries
    private ComboBox<String> cbo = new ComboBox<>(); // flagTitles

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Set text description
        //* There's only 5 now.
        flagDescription[0] = "A big country in North America with 50 different states. It is famous for being a leader in movies, music, and technology. The US has all kinds of weather and landscapes, from the busy streets of New York City to the sunny beaches of California.";
        flagDescription[1] = "A massive country in South Asia and one of the world's oldest civilizations. It is known for its diverse languages and culture, and wonderful cuisine like curries and breads. India is famous for the Taj Mahal, cricket, and a growing leadership in technology and space exploration.";
        flagDescription[2] = "An island nation in Europe made up of England, Scotland, Wales, and Northern Ireland. The UK is famous for its long history of kings and queens, landmarks like Big Ben, and its love for tea and soccer.";
        flagDescription[3] = "A country in the central Europe that is known for great engineering, especially cars and machines. It has both modern cities and old-fashioned towns with castles. Germany is also famous for its festivals and food like pretzels and bratwurst.";
        flagDescription[4] = "A very large country in Asia with one of the biggest populations in the world. It has an ancient history and is famous for the Great Wall. China is a major leader in making electronics and other products used all over the world.";

        // Set the first country for display
        setDisplay(0);

        // Add combo box and description pane to the border pane
        BorderPane pane = new BorderPane();

        BorderPane paneForComboBox = new BorderPane();
        paneForComboBox.setLeft(new Label("Select a country: "));
        paneForComboBox.setCenter(cbo);
        pane.setTop(paneForComboBox);
        cbo.setPrefWidth(400);
        cbo.setValue("United States of America");

        ObservableList<String> items =
                FXCollections.observableArrayList(flagTitles);
        cbo.getItems().addAll(items); // Add items to combo box
        pane.setCenter(descriptionPane);

        // Display the selected country
        cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ComboBoxDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /** Set display information on the description pane */
    public void setDisplay(int index) {
        descriptionPane.setTitle(flagTitles[index]);
        descriptionPane.setImageView(flagImage[index]);
        descriptionPane.setDescription(flagDescription[index]);
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

class DescriptionPane extends BorderPane {
    /** Label for displaying an image and a title */
    private Label lblImageTitle = new Label();

    /** Text area for displaying text */
    private TextArea taDescription = new TextArea();

    public DescriptionPane() {
        // Center the icon and text and place the text under the icon
        lblImageTitle.setContentDisplay(ContentDisplay.TOP);
        lblImageTitle.setPrefSize(200, 100);

        // Set the font in the label and the text field
        lblImageTitle.setFont(new Font("SansSerif", 16));
        taDescription.setFont(new Font("Serif", 14));

        taDescription.setWrapText(true);
        taDescription.setEditable(false);

        // Create a scroll pane to hold the text area
        ScrollPane scrollPane = new ScrollPane(taDescription);

        // Place label and scroll pane in the border pane
        setLeft(lblImageTitle);
        setCenter(scrollPane);
        setPadding(new Insets(5, 5, 5, 5));
    }

    /** Set the title */
    public void setTitle(String title) {
        lblImageTitle.setText(title);
    }

    /** Set the image view */
    public void setImageView(ImageView icon) {
        lblImageTitle.setGraphic(icon);
    }

    /** Set the text description */
    public void setDescription(String text) {
        taDescription.setText(text);
    }
}
