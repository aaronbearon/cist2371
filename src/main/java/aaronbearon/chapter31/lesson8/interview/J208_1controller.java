package aaronbearon.chapter31.lesson8.interview;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 8
 * Description: Use the methods and write the code for the calculator.
 */
public class J208_1controller {

    /**
     * Interface to provide the math operation to perform, like plus minus multiply divide.
     */
    private interface Operation {
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

    private void doOperation(Operation op) {
        try {
            double a = Double.parseDouble(txt1.getText());
            double b = Double.parseDouble(txt2.getText());
            // Compute using the interface.
            double answer = op.Compute(a, b);
            txtOut.setText(String.valueOf(answer));
        } catch (Exception e) {
            txtOut.setText("ERR");
        }
    }
}

/*

I used an interface to provide the correct math operation so I can reuse the
parsing and exception code.

In scene builder, I lowered the preferred heights of the hBoxes down to 50.
This removes the spacing between hBoxes when the window is vertically small.

*/
