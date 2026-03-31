package aaronbearon.chapter18.lesson9.interview;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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

    private void printSum(int amount) {
        int sum = getSum(amount, " = ");
        txtOut.setText(txtOut.getText() + sum);
    }

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
