package aaronbearon.chapter31.lesson8.interview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class J208_1controller {

    private interface Op {
        double Compute(double a, double b);
    }

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txtOut;

    @FXML
    public void add() {
        doOperation((a, b) -> a + b);
    }

    @FXML
    public void subtract() {
        doOperation((a, b) -> a - b);
    }

    @FXML
    public void multiply() {
        doOperation((a, b) -> a * b);
    }

    @FXML
    public void divide() {
        doOperation((a, b) -> a / b);
    }

    @FXML
    public void quit() {
        System.exit(0);
    }

    private void doOperation(Op op) {
        try {
            double a = Double.parseDouble(txt1.getText());
            double b = Double.parseDouble(txt2.getText());
            double answer = op.Compute(a, b);
            txtOut.setText(String.valueOf(answer));
        } catch (Exception e) {
            txtOut.setText("ERR");
        }
    }
}
