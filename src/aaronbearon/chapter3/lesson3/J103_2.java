package aaronbearon.chapter3.lesson3;

import java.util.Scanner;

public class J103_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input the weight in kilograms and the height in centimeters.
        System.out.print("Enter weight in kilograms: ");
        double weight = input.nextDouble();
        System.out.print("Enter height in centimeters: ");
        double height = input.nextDouble();

        // Create our conversion constants.
        final double POUNDS_PER_KILOGRAM = 1 / 0.4535;
        final double INCHES_PER_CENTIMETER = 1 / 2.54;

        // Do the conversion from kilograms to pounds and centimeters to inches.
        double weightInPounds = weight * POUNDS_PER_KILOGRAM;
        double heightInInches = height * INCHES_PER_CENTIMETER;

        // Pull out the whole number of feet.
        int feet = (int) heightInInches / 12;

        // get the remaining inches.
        double inches = heightInInches - (12 * feet);

        // Calculate BMI : kilogram weight divided by the square of meter height.
        double bmi = weight / Math.pow((height / 100), 2);

        // Determine what range the BMI is in; store it in weightRange.
        // This doesn't work right if a tricky user enters all zeros.
        String weightRange;
        if (bmi < 18.5)
            weightRange = "Underweight";
        else if (bmi < 25)
            weightRange = "Normal";
        else if (bmi < 30)
            weightRange = "Overweight";
        else
            weightRange = "Obese";

        // Output our results: pounds, feet, inches, BMI, and range.
        System.out.println("==========================");
        System.out.printf("Weight (lb): %.2f%n", weightInPounds);
        System.out.printf("Height (ft): %d%n", feet);
        System.out.printf("Height (in): %.2f%n", inches);
        System.out.println("==========================");
        System.out.printf("Your BMI is %.2f which is %s.%n", bmi, weightRange);
    }
}
