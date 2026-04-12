package aaronbearon.chapter20.lesson11;

import java.util.*;


public class J211_2 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Here are some test cases below");
        System.out.println();
        // Required test expressions
        validateExpression("1+2 * 3 - 1"); // 6
        validateExpression("((1+2)) * 3 - 1"); // 8
        validateExpression("(1+2) * (3 - 1)"); // 6
        validateExpression("1+2 0"); // 21 (numbers can be separated by only space, but 1 + 2 () 0 is wrong)
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
        } catch (EmptyStackException e) {
            System.out.println("Stack is already empty!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

            if (symIndex >= Symbols.OPS_LEN) {
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
                prepStack(Symbols.getSymbol(symIndex));
            } else {
                prepStack(Symbols.getSymbol(symIndex));
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

    private void validate(int l, int r) throws Exception {
        if ((l == Symbols.LPAREN && r >= Symbols.MUL && r < Symbols.OPS_LEN) || (l == Symbols.RPAREN && r >= Symbols.OPS_LEN)) {
            throw new Exception("'(sign' and/or ')num' not allowed!");
        }
        if ((l >= Symbols.OPS_LEN && r == Symbols.LPAREN) || (l >= Symbols.MUL && l < Symbols.OPS_LEN && r == Symbols.RPAREN)) {
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
        if (!opStack.empty()) {
            oldOpIndex = Symbols.found(String.valueOf(opStack.peek()));
            newOpIndex = Symbols.found(String.valueOf(op));
            if (oldOpIndex > Symbols.RPAREN) {
                if (!(oldOpIndex > Symbols.DIV && newOpIndex > Symbols.RPAREN && newOpIndex < Symbols.ADD)) {
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    char oldOp = opStack.pop();
                    numStack.push(getAnswer(num1, oldOp, num2));
                    clearStack(op);
                }
                return;
            }
        }

        if (op == ')') {
            opStack.pop();
        }
    }

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

class Symbols {
    public static final int LPAREN = 0;
    public static final int RPAREN = 1;
    public static final int MUL = 2;
    public static final int DIV = 3;
    public static final int ADD = 4;
    public static final int SUB = 5;
    public static final int OPS_LEN = 6;

    // This time, here's what PEMDAS means:
    // Parentheses open, End parentheses, Multiplication, Division, Addition, and Subtraction.
    private static final char[] ops = {'(', ')', '*', '/', '+', '-'};

    public static String[] buildExpression(String expression) {
        // First remove all existing spaces.
        expression = expression.replaceAll("\\s+", "");

        List<String> newExpr = new ArrayList<>();
        StringBuilder numBuf = new StringBuilder();
        for (char c : expression.toCharArray()) {
            switch (c) {
                case '(', ')', '*', '/', '+', '-':
                    if (!numBuf.isEmpty()) {
                        newExpr.add(numBuf.toString());
                        numBuf = new StringBuilder();
                    }
                    newExpr.add(String.valueOf(c));
                    break;
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    numBuf.append(c);
                    break;
                default:
                    throw new IllegalArgumentException("invalid character: " + c);
            }
        }
        if (!numBuf.isEmpty()) {
            newExpr.add(numBuf.toString());
        }

        return newExpr.toArray(new String[0]);

//        StringBuilder newExpression = new StringBuilder();
//        // Hold numbers
//        numBuf = new StringBuilder(" ");
//        boolean isNumber;
//        for (int i = 0; i < expression.length(); i++) {
//            isNumber = true;
//            for (char op : ops) {
//                if (expression.charAt(i) == op) {
//                    isNumber = false;
//                    break;
//                }
//            }
//            if (isNumber) {
//                numBuf.append(expression.charAt(i));
//            } else {
//                newExpression.append(numBuf).append(" ").append(expression.charAt(i)).append(" ");
//                numBuf = new StringBuilder(" ");
//            }
//        }
//        newExpression.append(numBuf);
//        return newExpression.toString().trim().split("\\s+");
    }

    public static int getCharSize() {
        return ops.length;
    }

    public static char getSymbol(int index) {
        return ops[index];
    }

    public static int found(String key) {
        if (key.length() == 1) {
            char ch = key.charAt(0);
            for (int i = 0; i < ops.length; i++) {
                if (ops[i] == ch) {
                    return i;
                }
            }
        }
//        for (int i = 0; i < ops.length; i++) {
//            if (String.valueOf(ops[i]).equals(key)) {
//                return i;
//            }
//        }
        return ops.length;
    }
}
