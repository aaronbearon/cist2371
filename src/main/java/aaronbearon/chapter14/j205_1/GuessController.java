package aaronbearon.chapter14.j205_1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 5 controller
 * Description:
 * Display a message when a button is clicked.
 */
public class GuessController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("I'm Aaron Blum!");
    }
}

/*

I chose a short but descriptive display message.

*/
