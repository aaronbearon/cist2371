package aaronbearon.chapter15.lesson15.j206_2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField txtIn;

    @FXML
    private TextField txtOut;

    @FXML
    protected void onButtonClick() {
        try {
            int num = Math.abs(Integer.parseInt(txtIn.getText()));
            if (num % 2 == 0) {
                txtOut.setText("Even");
            }

            if (num % 2 == 1) {
                txtOut.setText("Odd");
            }
        } catch (Exception e) {
            txtOut.setText("Error!");
        }
    }
}
