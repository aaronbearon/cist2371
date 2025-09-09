package aaronbearon.chapter2.lesson2;

import java.util.Scanner;

// This program is a Fahrenheit to Celsius converter.

public class J102_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user for the Fahrenheit temperature
        System.out.print("Please enter the temperature in Fahrenheit: ");
        // Store it in a variable.
        double fahrenheit = input.nextDouble();

        // Another variable is used to store the temperature converted from F to C.
        double celsius = (5.0 / 9) * (fahrenheit - 32);
        // The user sees their fahrenheit temperature and the corresponding Celsius temperature.
        System.out.println("The Fahrenheit temperature " + fahrenheit + " is: " + celsius + " degrees Celsius.");

        // How I would do it using printf, so the formatting would look good.
        // System.out.printf("The Fahrenheit temperature %.2f is: %.2f degrees Celsius.%n", fahrenheit, celsius);
    }
}
