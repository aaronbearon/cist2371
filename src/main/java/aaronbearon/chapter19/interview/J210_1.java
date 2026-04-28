package aaronbearon.chapter19.interview;

import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 10
 * Description: Write and test a generic multiplication class that can handle int and double.
 */
public class J210_1 {
    public static void main(String[] args) {
        // Put the try catch logic in the while loop.
        while (true) {
            try {
                System.out.print("num1 (int or double): ");
                Number num1 = getInput();
                System.out.print("num2 (int or double): ");
                Number num2 = getInput();
                if (num1 instanceof Integer && num2 instanceof Integer) {
                    Multiplication<Integer> m = new Multiplication<>(Integer.class, num1.intValue(), num2.intValue());
                    Integer result = m.getProduct();
                    System.out.println(num1 + " x " + num2 + " = " + result);
                } else {
                    // Cast them both to Double
                    Multiplication<Double> m = new Multiplication<>(Double.class, num1.doubleValue(), num2.doubleValue());
                    Double result = m.getProduct();
                    System.out.println(num1 + " x " + num2 + " = " + result);
                }
                System.out.println("-------------------------");
            } catch (Exception e) {
                System.out.println("Done!");
                return;
            }
        }
    }

    /**
     * Detect the input type and return it
     *
     * @return Number
     */
    public static Number getInput() throws Exception {
        Scanner input = new Scanner(System.in);
        if (input.hasNextInt()) {
            return input.nextInt();
        } else if (input.hasNextDouble()) {
            return input.nextDouble();
        } else {
            throw new Exception();
        }
    }
}

/**
 * This class can use E as an Integer or Double, basically working with numbers of those types.
 *
 * @param <E> subtype of Number
 */
class Multiplication<E extends Number> {
    private final Class<E> C;
    private final E num1;
    private final E num2;

    public Multiplication(Class<E> C, E num1, E num2) {
        this.C = C;
        this.num1 = num1;
        this.num2 = num2;
    }

    public E getProduct() {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            return this.C.cast(num1.intValue() * num2.intValue());
        } else if (num1 instanceof Double && num2 instanceof Double) {
            return this.C.cast(num1.doubleValue() * num2.doubleValue());
        } else {
            throw new UnsupportedOperationException("Only implemented for int and double.");
        }
    }
}

/*

In the Multiplication class, I used <E extends Number>.
This means E could be an Integer or Double.

C is an object of the Class E.
I did C.cast(***) to avoid having to do (E) (***), which would cause a compile warning.

My getInput method can detect the input type of the user input and store it in the Number type variable.
If either number is a Double, their double versions are both passed into the Multiplication class.
Still, each num variable in the main method knows the datatype of the number the user entered.

*/
