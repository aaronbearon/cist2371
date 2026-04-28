package aaronbearon.chapter20.lesson11;

import java.util.*;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 11
 * Description: Modify the core algorithm to test the expressions from the instructions.
 * Provide your own structure to the implementation.
 */
public class J211_2 {
    public static void main(String[] args) {//throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Here are some test cases below:");
        System.out.println();
        // Required test expressions
        validateExpression("1+2 * 3 - 1"); // 6
        validateExpression("((1+2)) * 3 - 1"); // 8
        validateExpression("(1+2) * (3 - 1)"); // 6
        //* Numbers with spaces between the digits now work.
        validateExpression("1+2 0"); // 21 (numbers can be separated by space, but 1 + 2 () 0 is wrong)
        // Optional text expressions
        validateExpression("((()(( 5 + ((2)))()*4)()))"); // 28
        validateExpression("((()( 5 + ((2))()*4)()))()"); // 13
        validateExpression(")((()( 5 + ((2))()*4)()))()"); // Parentheses wrongly ordered
        validateExpression("((()( 5 + ((2))()*4)())))(()"); // Parentheses wrongly ordered
        validateExpression(" ( 11 + 4 ) * 5 / 3 - 2 - ( 2 - 8 ) "); // 29
        System.out.println();
        // Allow user to test any expression.
        System.out.println("Please enter your own expression below:");
        validateExpression(input.nextLine());
    }

    /**
     * Any expression to be tested must be passed here.
     * This method handles any exception that may arise from an illegal expression.
     *
     * @param testExpression the expression to calculate
     */
    public static void validateExpression(String testExpression) {
        try {
            System.out.print(testExpression + " = ");
            EvaluateExpression e = new EvaluateExpression();
            System.out.println(e.calcExpression(testExpression));
        } catch (UnsupportedOperationException e) {
            System.out.println("Invalid operation: " + e.getMessage());
        } catch (EmptyStackException e) {
            System.out.println("Stack is already Empty!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid argument: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Runtime Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
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

I made numbers work even with spaces between the digits.

I created an enum class to store variable names with different operators.
That class has the method for storing the test expression in tokens.
It removes all the spaces and uses StringBuilder to make numbers from space-separated digits.

Tricky:
The implementation of the calculator which uses the enum class, is moved to a class with nothing static.
The test expressions in the main method, are passed into the validator that's in the same class.
This "validator" catches a range of exception types.
It also creates an instance of EvaluateExpression, and passes the expression in.
This overall structure prevents stacks from being partially filled for the next expression.

Basically, once the validateExpression method returns, everything regarding the other classes is destroyed.
That's why if the expression errors, the instance of EvaluateExpression is gone and a new instance is created.

The expression is also checked to see if parentheses are on the wrong side of a number or operator.
Once the expression is finished in the "non-static" class, an exception is thrown if the stacks didn't clear.

Operator precedence is maintained.

*/
