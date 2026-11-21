package aaronbearon.chapter16.interview.j207_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 7
 * Description: This is the file with most of the code.
 */
public class HelloController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btnSubmit;
    @FXML
    private Label lblUsers;

    private int userCount = 0;

    @FXML
    protected void onSubmit(ActionEvent actionEvent) {
        if (txtName.getText().equals("0")) {
            System.exit(0);
        }
        txtName.setText("");
        txtPhone.setText("");
        btnSubmit.setDisable(true);
        userCount++;
        lblUsers.setText(userCount + " users entered");
    }

    @FXML
    protected void textChanged(KeyEvent keyEvent) {
        // Disabled with invalid fields means enabled with valid fields.
        btnSubmit.setDisable(!isValidFields());
    }

    private boolean isValidFields() {
        if (txtName.getText().equals("0")) {
            return true;
        }
        if (txtName.getText().isEmpty()) {
            return false;
        }
        return txtPhone.getText().length() == 4;
    }
}

/*
The submit button is only enabled text field validations are passed.
TODO: The phone number field can contain letters too, fix later.
*/
