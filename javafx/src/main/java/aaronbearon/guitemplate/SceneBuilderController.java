package aaronbearon.guitemplate;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class SceneBuilderController {
    public TextField txt1;
    public TextField txt2;
    public TextField txtOut;

    public void add(ActionEvent actionEvent) {
        txtOut.setText(String.valueOf(parseOp(txt1) + parseOp(txt2)));
    }

    public void subtract(ActionEvent actionEvent) {
        txtOut.setText(String.valueOf(parseOp(txt1) - parseOp(txt2)));
    }

    public void multiply(ActionEvent actionEvent) {
        txtOut.setText(String.valueOf(parseOp(txt1) * parseOp(txt2)));
    }

    public void divide(ActionEvent actionEvent) {
        txtOut.setText(String.valueOf(parseOp(txt1) / parseOp(txt2)));
    }

    private static double parseOp(TextField txt) {
        return Double.parseDouble(txt.getText());
    }
}
