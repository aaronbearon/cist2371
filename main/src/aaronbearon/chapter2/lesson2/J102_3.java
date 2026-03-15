package aaronbearon.chapter2.lesson2;

// Aaron Blum, 20250910
// CIST 2371 Java Lab 2, Problem 2-3

public class J102_3 {
    public static void main(String[] args) {
        String myName = "Aaron Blum";
        String myMiddle = "M";
        int myAge = 20;
        double pi = 3.141592;

        // We need to pull out each part of the name.
        // Let's split the full name string into substrings, using space.
        String[] parts = myName.split(" ");
        String myFirst = parts[0];
        String myLast = parts[1];

        // Part A
        // Display output using print.
        // It doesn't automatically print a linefeed, so add one.
        System.out.print("A. print ============================\n");
        System.out.print("My name is " + myFirst + " " + myMiddle + ". " + myLast + ".\n");
        System.out.print("I am " + myAge + " years old.\n");
        System.out.print("Pi is " + pi + ".\n");
        System.out.print("\n");

        // Part B
        // Display output using println.
        // It does automatically print a linefeed.
        System.out.println("B. println ===========================");
        System.out.println("Name    :  " + myName);
        System.out.println("Initial :  " + myMiddle);
        System.out.println("Age     :  " + myAge);
        System.out.println("Pi      :  " + pi);
        System.out.println();

        // Part C
        // Display output using printf.
        // Printf allows me to do some neat formatting. It doesn't automatically print a new line.
        System.out.printf("C. printf ===========================\n");
        System.out.printf("My name is %s %s. %s and %d years old.\n", myFirst, myMiddle, myLast, myAge);
        System.out.printf("Pi      :  %f\n", pi);
        System.out.printf("Pi      :  %8.5f\n", pi);
        System.out.printf("Pi      :  %8.4f\n", pi);
        System.out.printf("Pi      :  %8.2f\n", pi);
        System.out.printf("Pi      :  %8.1f\n", pi);
        System.out.printf("\n");

    }
}
