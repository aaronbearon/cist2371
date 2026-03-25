package aaronbearon.chapter31.lesson8;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 10
 * Description: App that displays countries and flags.
 */
public class J208_311controller {

    @FXML private ImageView imageView;
    @FXML private TextArea taDescription;

    // Same private data fields
    private final Image usImage = new Image("https://liveexample.pearsoncmg.com/common/image/us.gif");
    private final Image ukImage = new Image("https://liveexample.pearsoncmg.com/common/image/uk.gif");
    private final Image caImage = new Image("https://liveexample.pearsoncmg.com/common/image/ca.gif");

    private final String usDescription = "Description for US ...";
    private final String ukDescription = "Description for UK ...";
    private final String caDescription = "Description for CA ...";

    // This is the for the default radio button.
    @FXML
    public void initialize() {
        imageView.setImage(usImage);
        taDescription.setText(usDescription);
    }

    // These are the handlers.
    @FXML
    public void handleRbUS() {
        imageView.setImage(usImage);
        taDescription.setText(usDescription);
    }

    @FXML
    public void handleRbUK() {
        imageView.setImage(ukImage);
        taDescription.setText(ukDescription);
    }

    @FXML
    public void handleRbCA() {
        imageView.setImage(caImage);
        taDescription.setText(caDescription);
    }
}

/*

Converted everything to Scene Builder.

*/
