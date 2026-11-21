package aaronbearon.chapter15.j206_1;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 6
 * Description:
 * This is the code to run when the button is clicked
 */
public class EvenOddController {
    // Based on the TextField fx:id names
    @FXML
    private TextField txtIn;

    @FXML
    private TextField txtOut;

    @FXML
    protected void onButtonClick() {
        // Try~catch can display error in the txtOut field
        try {
            // Use absolute value to enable negatives to count as even or odd.
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

/*

The txtOut field displays even or odd based on the number in the txtIn field.
If the contents of txtIn are invalid, an error is displayed in txtOut.

*/
