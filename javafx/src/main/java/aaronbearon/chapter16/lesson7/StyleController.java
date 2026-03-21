package aaronbearon.chapter16.lesson7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 7 Part 10
 * Description: Demo various text styles with user controls.
 */
public class StyleController {
    @FXML
    public Text text;

    @FXML
    public CheckBox chkBold;
    @FXML
    public CheckBox chkItalic;
    @FXML
    public CheckBox chkUnderline;
    @FXML
    public CheckBox chkStrikethrough;

    @FXML
    public RadioButton rbRed;
    @FXML
    public RadioButton rbYellow;
    @FXML
    public RadioButton rbGreen;
    @FXML
    public RadioButton rbBlue;
    @FXML
    public RadioButton rbPurple;

    @FXML
    public TextField txtMessage;

    private final Font fontBoldItalic = Font.font("Times New Roman",
            FontWeight.BOLD, FontPosture.ITALIC, 20);
    private final Font fontBold = Font.font("Times New Roman",
            FontWeight.BOLD, FontPosture.REGULAR, 20);
    private final Font fontItalic = Font.font("Times New Roman",
            FontWeight.NORMAL, FontPosture.ITALIC, 20);
    private final Font fontNormal = Font.font("Times New Roman",
            FontWeight.NORMAL, FontPosture.REGULAR, 20);

    @FXML
    public void leftClicked(ActionEvent e) {
        text.setX(text.getX() - 10);
    }

    @FXML
    public void rightClicked(ActionEvent e) {
        text.setX(text.getX() + 10);
    }

    @FXML
    protected void chkActionHandler(ActionEvent e) {
        if (chkBold.isSelected() && chkItalic.isSelected()) {
            text.setFont(fontBoldItalic); // Both check boxes checked
        } else if (chkBold.isSelected()) {
            text.setFont(fontBold); // The Bold check box checked
        } else if (chkItalic.isSelected()) {
            text.setFont(fontItalic); // The Italic check box checked
        } else {
            text.setFont(fontNormal); // Both check boxes unchecked
        }

        //* I Added these to set if they're checked.
        text.setUnderline(chkUnderline.isSelected());
        text.setStrikethrough(chkStrikethrough.isSelected());
    }

    @FXML
    public void radioActionHandler(ActionEvent e) {
        if (rbRed.isSelected()) {
            text.setFill(Color.RED);
        } else if (rbYellow.isSelected()) {
            text.setFill(Color.YELLOW);
        } else if (rbGreen.isSelected()) {
            text.setFill(Color.GREEN);
        } else if (rbBlue.isSelected()) {
            text.setFill(Color.BLUE);
        } else if (rbPurple.isSelected()) {
            text.setFill(Color.PURPLE);
        } else {
            text.setFill(Color.BLACK);
        }
    }

    @FXML
    public void messageHandler(ActionEvent e) {
        text.setText(txtMessage.getText());
    }
}

/*
Scene builder control with event handlers.
Reacts to user events, updates UI.
*/
