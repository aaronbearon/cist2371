package aaronbearon.chapter18.lesson9.interview;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 9
 * Description: Print the sum of natural numbers up to user's number in Scene Builder
 */
public class J209_1_controller {
    @FXML
    private TextField txtIn;

    @FXML
    private TextField txtOut;

    @FXML
    public void onCalculate() {
        txtOut.setText("");
        try {
            if (Integer.parseInt(txtIn.getText()) >= 1) {
                printSum(Integer.parseInt(txtIn.getText()));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            txtOut.setText("Error, please enter a positive integer.");
        }
    }

    // Recursive helper method
    private void printSum(int amount) {
        int sum = getSum(amount, " = ");
        txtOut.setText(txtOut.getText() + sum);
    }

    // Recursive method is basically the same as non-gui part
    private int getSum(int amount, String symbol) {
        int sumNext;
        if (amount > 1) {
            sumNext = amount + getSum(amount - 1, "+");
        } else {
            sumNext = amount;
        }

        txtOut.setText(txtOut.getText() + amount + symbol);
        return sumNext;
    }
}

/*

Same as part 1-2 but with GUI.
Also, the try~catch block is now in the starting method.

*/
