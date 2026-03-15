package aaronbearon.chapter3;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*
        if (true == true) {
            if (false == true) {
                System.out.println("A");
            }
        } else System.out.println("B");
        */
        System.out.print("1. ");
        System.out.println(true || false && false);
        System.out.print("1. ");
        System.out.println(false || true && false);
        System.out.print("3. ");
        System.out.println(false || false && true);
        System.out.print("4. ");
        System.out.println(false || true && true);
        System.out.print("5. ");
        System.out.println(true || false && true);
        System.out.print("6. ");
        System.out.println(true || true && false);
        System.out.print("7. ");
        System.out.println(true && false || false);
        System.out.print("8. ");
        System.out.println(false && true || false);
        System.out.print("9. ");
        System.out.println(false && false || true);
        System.out.print("10. ");
        System.out.println(false && true || true);
        System.out.print("11. ");
        System.out.println(true && false || true);
        System.out.print("12. ");
        System.out.println(true && true || false);
        /*
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a number, " +
                " and I will tell you whether or not it's even. ");
        int num = input.nextInt();
        if (num % 2 == 0) {
            System.out.println("The number is even.");
        } else {
            System.out.println("The number is not even.");
        }
        */
    }
}

/* Problem analysis

x < 1000 bicycle
x >= 1000 and x < 2000 mercedes
x >= 2000 and x < 10000 BMW
x > 10000 airplane
*/

/* Java note

public class *** {
public static *** {
if () {}
else if () {}
else {}
}
}
*/
