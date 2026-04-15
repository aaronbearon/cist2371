package aaronbearon.chapter21.lesson12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 12
 * Description: Make the GUI calculator perform the expression-testing logic.
 */
public class calculator3_controller {
    @FXML
    private TextArea display;

    @FXML
    private void handleInput(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (value.equals("CE")) {
            display.setText("");
        } else if (value.equals("=") && !display.getText().isEmpty()) {
            display.appendText(validateExpression(display.getText()));
        } else {
            display.appendText(value);
        }
    }

    @FXML
    private void handleHover(MouseEvent event) {
        Button btn = (Button) event.getSource();
        String text = btn.getText();

        if (text.equals("(") || text.equals(")")) {
            // Hover style for white buttons
            btn.setStyle("-fx-background-color: #e0e4ff; -fx-text-fill: #5d6edb; -fx-font-size: 18px; -fx-background-radius: 0; -fx-border-color: #5d6edb;");
        } else {
            // Hover style for blue buttons
            btn.setStyle("-fx-background-color: #7382eb; -fx-text-fill: white; -fx-font-size: 18px; -fx-background-radius: 0;");
        }
    }

    @FXML
    private void handleExit(MouseEvent event) {
        Button btn = (Button) event.getSource();
        String text = btn.getText();

        if (text.equals("(") || text.equals(")")) {
            // Reset to original white style
            btn.setStyle("-fx-background-color: white; -fx-text-fill: #5d6edb; -fx-font-size: 18px; -fx-background-radius: 0; -fx-border-color: #5d6edb;");
        } else {
            // Reset to original blue style
            btn.setStyle("-fx-background-color: #5d6edb; -fx-text-fill: white; -fx-font-size: 18px; -fx-background-radius: 0;");
        }
    }

    /**
     * Any expression to be tested must be passed here.
     * This method handles any exception that may arise from an illegal expression.
     *
     * @param testExpression the expression to calculate
     */
    public String validateExpression(String testExpression) {
        try {
            System.out.print(testExpression + " = ");
            EvaluateExpression e = new EvaluateExpression();
            return " =    " + e.calcExpression(testExpression);
        } catch (Exception e) {
            return "ERR!";
        }
    }
}

/**
 * WARNING! Move the implementation away from the public class and make members non-static.
 * Otherwise, invalid expressions could leave a static stack partially filled for the next expression.
 */
class EvaluateExpression {
    // One stack for numbers and one for symbols
    private final Stack<Integer> numStack = new Stack<>();
    private final Stack<Character> opStack = new Stack<>();

    // The expression terminator
    private final char END = ';';

    /**
     * Process and calculate the result of the expression that was passed in.
     *
     * @param expression the string to calculate
     * @return string goes back to the validator method
     * @throws Exception checked exception
     */
    public int calcExpression(String expression) throws Exception {
        // WARNING! This boolean keeps the placement of numbers and */+- operators intact.
        // Keeps those two types alternating, starting with a number.
        boolean numRecent = false;
        String[] tokens = Symbols.buildExpression(expression);
        for (int i = 0; i < tokens.length; i++) {
            int symIndex = Symbols.found(tokens[i]);
            // Some expression errors can immediately be found.
            // Check those here, after the first one to prevent -array index out of bounds
            if (i > 0) {
                validate(Symbols.found(tokens[i - 1]), symIndex);
            }

            if (symIndex >= Symbols.values().length) {
                try {
                    numStack.push(Integer.parseInt(tokens[i]));
                } catch (Exception e) {
                    throw new Exception("Wrong int or symbol!");
                }
                numRecent = !numRecent;
                if (!numRecent) {
                    throw new Exception("Two straight ints not allowed except for spaces!");
                }
            } else if (symIndex > 1) {
                // Non parentheses operator should flip the true value to false
                numRecent = !numRecent;
                if (numRecent) {
                    throw new Exception("Two straight operators not allowed!");
                }
                prepStack(Symbols.getOperator(symIndex));
            } else {
                prepStack(Symbols.getOperator(symIndex));
            }
        }
        // Terminate the expression after the for loop.
        prepStack(END);
        // Final check ensures that the calculation went smoothly.
        if (opStack.empty()) {
            int answer = numStack.pop();
            if (numStack.empty()) {
                return answer;
            } else {
                throw new Exception("Numbers shouldn't be left over!");
            }
        } else {
            throw new Exception("Operators shouldn't be left over!");
        }
    }

    /**
     * Don't allow certain patterns of consecutive items.
     *
     * @param l ordinal of previous item
     * @param r ordinal of current item
     * @throws Exception if invalid
     */
    private void validate(int l, int r) throws Exception {
        final int OPS_LEN = Symbols.values().length;
        if ((l == Symbols.LPAREN.ordinal() && r >= Symbols.MUL.ordinal() && r < OPS_LEN) ||
                (l == Symbols.RPAREN.ordinal() && r >= OPS_LEN)) {
            throw new Exception("'(sign' and/or ')num' not allowed!");
        }
        if ((l >= OPS_LEN && r == Symbols.LPAREN.ordinal()) ||
                (l >= Symbols.MUL.ordinal() && l < OPS_LEN && r == Symbols.RPAREN.ordinal())) {
            throw new Exception("'num(' and/or 'sign)' not allowed!");
        }
    }

    /**
     * Call a recursive helper method that can only push to the stack after all recursive calls.
     *
     * @param op the new operator to handle
     */
    private void prepStack(char op) {
        if (op != '(') {
            clearStack(op);
        }
        if (op != ')' && op != END) {
            opStack.push(op);
        }
    }

    /**
     * Using recursion, process the stacks as much as possible with the given information.
     *
     * @param op the same new operator to handle as the previous method call
     */
    private void clearStack(char op) {
        int oldOpIndex;
        int newOpIndex;
        // This logic only occurs with non-empty stack
        if (!opStack.empty()) {
            oldOpIndex = Symbols.found(String.valueOf(opStack.peek()));
            newOpIndex = Symbols.found(String.valueOf(op));
            // Next checks require top of op stack to be one of the 4 signs.
            if (oldOpIndex > Symbols.RPAREN.ordinal()) {
                // For this to work, it's not allowed for stack op to be + or - and
                //  for new op to next op to be * or /.
                if (!(oldOpIndex > Symbols.DIV.ordinal() && newOpIndex > Symbols.RPAREN.ordinal() &&
                        newOpIndex < Symbols.ADD.ordinal())) {
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    char oldOp = opStack.pop();
                    numStack.push(getAnswer(num1, oldOp, num2));
                    // Recursive call
                    clearStack(op);
                }
                return;
            }
        }

        // Only if the innermost logic doesn't occur.
        if (op == ')') {
            opStack.pop();
        }
    }

    // Return a lambda version of a switch statement.
    // It takes the two numbers in order with the operation.
    private int getAnswer(int num1, char sign, int num2) {
        return switch (sign) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new UnsupportedOperationException("Only implemented for */+-");
        };
    }
}

/**
 * Create an enumerated type with a set of 6 fields, each with an op character.
 * You can retrieve each of their ordinals along with the length of Symbols, which is 6.
 */
enum Symbols {
    LPAREN('('),
    RPAREN(')'),
    MUL('*'),
    DIV('/'),
    ADD('+'),
    SUB('-');

    private final char symbol;

    Symbols(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Process the string carefully, including making numbers from digits separated by spaces.
     *
     * @param expression the user's expression
     * @return an array of String tokens
     */
    public static String[] buildExpression(String expression) {
        // First remove all existing spaces.
        expression = expression.replaceAll("\\s+", "");

        List<String> newExpr = new ArrayList<>();
        StringBuilder numBuf = new StringBuilder();
        for (char c : expression.toCharArray()) {
            // The char is one of the 6 symbols.
            if (found(String.valueOf(c)) < Symbols.values().length) {
                if (!numBuf.isEmpty()) {
                    newExpr.add(numBuf.toString());
                    numBuf = new StringBuilder();
                }
                newExpr.add(String.valueOf(c));
                // The char is a digit.
            } else if (Character.isDigit(c)) {
                numBuf.append(c);
                // The char is illegal.
            } else {
                throw new IllegalArgumentException("invalid character: " + c);
            }
        }
        if (!numBuf.isEmpty()) {
            newExpr.add(numBuf.toString());
        }
        return newExpr.toArray(new String[0]);
    }

    // Get the value's symbol.
    public static char getOperator(int val) {
        for (Symbols op : Symbols.values()) {
            if (op.ordinal() == val) {
                return op.symbol;
            }
        }
        throw new IllegalArgumentException("No operator found.");
    }

    // Get the symbol's value.
    public static int found(String key) {
        if (key.length() == 1) {
            char ch = key.charAt(0);
            for (Symbols op : Symbols.values()) {
                if (op.symbol == ch) {
                    return op.ordinal();
                }
            }
        }
        return Symbols.values().length;
    }
}

/*

A lot of the documentation here is the same as the non-GUI version.

This time, I used a grid pane, used precise math, and made the calculator look like the one in the instructions.
The textarea is on top.

I made one action handler from most of the buttons to get the source and read the button's text.
I set color styles for mouse hovering and not hovering.

The exception handling just catches any exception (any instance) and prints "ERR!"
That happens when = is pressed on a non-empty expression.

The logic from pressing = is basically copy and paste from the non-GUI version.

*/
