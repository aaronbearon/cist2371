package aaronbearon.chapter15.lesson15;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 6 Part 1
 * Description:
 * Make the numbers in the first 3 fields produce the sum in one remaining box and product in the other.
 * (Don't forget appropriate labels)
 */
public class J206_21 extends Application {
    //* The 5 textField field names are changed to better reflect the problem.
    private TextField tfnumberOne = new TextField();
    private TextField tfnumberTwo = new TextField();
    private TextField tfnumberThree = new TextField();
    private TextField tfSum = new TextField();
    private TextField tfMultiplication = new TextField();
    private Button btCalculate = new Button("Calculate");

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        //* The changes are reflected here.
        //* The labels are also changed to reflect the problem.
        gridPane.add(new Label("1."), 0, 0);
        gridPane.add(tfnumberOne, 1, 0);
        gridPane.add(new Label("2."), 0, 1);
        gridPane.add(tfnumberTwo, 1, 1);
        gridPane.add(new Label("3."), 0, 2);
        gridPane.add(tfnumberThree, 1, 2);
        gridPane.add(new Label("Sum:"), 0, 3);
        gridPane.add(tfSum, 1, 3);
        gridPane.add(new Label("Product:"), 0, 4);
        gridPane.add(tfMultiplication, 1, 4);
        gridPane.add(btCalculate, 1, 5);

        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        tfnumberOne.setAlignment(Pos.BOTTOM_RIGHT);
        tfnumberTwo.setAlignment(Pos.BOTTOM_RIGHT);
        tfnumberThree.setAlignment(Pos.BOTTOM_RIGHT);
        tfSum.setAlignment(Pos.BOTTOM_RIGHT);
        tfMultiplication.setAlignment(Pos.BOTTOM_RIGHT);
        tfSum.setEditable(false);
        tfMultiplication.setEditable(false);
        GridPane.setHalignment(btCalculate, HPos.RIGHT);

        // Process events
        btCalculate.setOnAction(e -> calcSumAndProduct());

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 250);
        //* Proper title for this problem.
        primaryStage.setTitle("Sum and Product"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    //* Proper method name for this problem
    private void calcSumAndProduct() {
        // Get values from text fields
        //* These 3 basically have the same purpose.
        int num1 = Integer.parseInt(tfnumberOne.getText());
        int num2 = Integer.parseInt(tfnumberTwo.getText());
        int num3 = Integer.parseInt(tfnumberThree.getText());

        // Don't "// Display monthly payment and total payment" anymore
        //* Display the sum and product.
        tfSum.setText(String.format("%d",
                num1 + num2 + num3));
        tfMultiplication.setText(String.format("%d",
                num1 * num2 * num3));
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

/*

Note: Assuming the problems are in order 1-13,
    this whole documentation section won't repeat everything that's the same in each.

Line 60 is a lambda statement.
It simplifies an anonymous inner class and calls the now named calcSumAndProduct method.

I only included the loanCalculator, not the loan class.
I saw that the first three fields produce the resulting 4th and 5th fields.
So, I gave everything appropriate names and labels, including the title.
I made the 4th field be the sum of the first three, and the last field be the product.
I decided to use the int datatype for each text field.

So basically, the user enters 3 numbers, one in each of the first three fields.
When the user hits calculate, their sum is shown in the 4th field, and their product is in the 5th.

*/
