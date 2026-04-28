package aaronbearon.chapter2.lesson2;

// Aaron Blum, 20250910
// CIST 2371 Java Lab 2, Problem 2-2

// We need Scanner to do our input
import java.util.Scanner;

// This program is a Fahrenheit to Celsius converter.

public class J102_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user for the Fahrenheit temperature
        System.out.print("Please enter the temperature in Fahrenheit: ");
        // Store it in a fahrenheit variable.
        double fahrenheit = input.nextDouble();

        // celsius variable to store the temperature converted from F to C.
        double celsius = (5.0 / 9) * (fahrenheit - 32);

        // Print the Fahrenheit temperature and the corresponding Celsius temperature.
        // The Celsius temperature is rounded to the nearest tenth.
        System.out.println("The Fahrenheit temperature " + fahrenheit + " is: "
                + Math.round(celsius * 10) / 10.0 + " degrees Celsius.");
    }
}
