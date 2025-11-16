package aaronbearon.chapter10.lesson10;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

// Use kilograms/meter and pounds/inch.
// User decides which.
// Calculate BMI : kilogram weight divided by the square of meter height.
public class J110_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Measure people and find their BMI. Press 0 to exit.");
        for (int i = 1, sentinel = 1; sentinel != 0; i++) {
            System.out.println("Person " + i + ":");
            measurePerson(new BMI());
            System.out.print("Continue? ");
            sentinel = input.nextInt();
        }

        System.out.println("Thank you!");
    }

    private static void measurePerson(BMI person) {
        inputFields(person);
        BigDecimal weight = person.getWeight();
        BigDecimal height = person.getHeight();
        BigDecimal units = person.getType();
        BigDecimal bmi = person.getBMI(weight, height, units);
        System.out.println("BMI: " + bmi + " (" + getRange(bmi) + ")");
    }

    private static void inputFields(BMI person) {
        Scanner input = new Scanner(System.in);
        System.out.print("Kilograms/meter or pounds/inch? (1 or 2) (type 2 for pounds/inch.): ");
        BigDecimal units = BigDecimal.valueOf(input.nextDouble());
        person.setType(units);
        System.out.print("Enter weight: ");
        BigDecimal weight = BigDecimal.valueOf(input.nextDouble());
        person.setWeight(weight);
        System.out.print("Enter height: ");
        BigDecimal height = BigDecimal.valueOf(input.nextDouble());
        person.setHeight(height);
    }

    private static String getRange(BigDecimal bmi) {
        if (bmi.compareTo(BigDecimal.valueOf(18.5)) < 0) {
            return "Underweight";
        } else if (bmi.compareTo(BigDecimal.valueOf(25)) < 0) {
            return "Normal";
        } else if (bmi.compareTo(BigDecimal.valueOf(30)) < 0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}

class BMI {
    private BigDecimal weight;

    private BigDecimal height;

    private BigDecimal type;

    BMI() {
        this(BigDecimal.valueOf(0));
    }

    BMI(BigDecimal weight) {
        this(weight, BigDecimal.valueOf(1));
    }

    BMI(BigDecimal weight, BigDecimal height) {
        this(weight, height, BigDecimal.valueOf(0));
    }

    BMI(BigDecimal weight, BigDecimal height, BigDecimal type) {
        privateWeight(weight);
        privateHeight(height);
        privateType(type);
    }

    void setWeight(BigDecimal weight) {
        privateWeight(weight);
    }

    void setHeight(BigDecimal height) {
        privateHeight(height);
    }

    void setType(BigDecimal type) {
        privateType(type);
    }

    private void privateWeight(BigDecimal weight) {
        this.weight = weight;
        if (weight.compareTo(BigDecimal.valueOf(0)) < 0) {
            this.weight = BigDecimal.valueOf(0);
        }
    }

    private void privateHeight(BigDecimal height) {
        this.height = height;
        if (height.compareTo(BigDecimal.valueOf(0)) <= 0) {
            this.height = BigDecimal.valueOf(1);
        }
    }

    private void privateType(BigDecimal type) {
        this.type = type;
        if (type.compareTo(BigDecimal.valueOf(2)) != 0) {
            this.type = BigDecimal.valueOf(1);
        }
    }

    BigDecimal getWeight() {
        return weight;
    }

    BigDecimal getHeight() {
        return height;
    }

    BigDecimal getType() {
        return type;
    }

    BigDecimal getBMI(BigDecimal weight, BigDecimal height, BigDecimal type) {
        if (type.compareTo(BigDecimal.valueOf(2)) == 0) {
            return takePoundsAndInches(weight, height);
        } else {
            return takeKilogramsAndMeters(weight, height);
        }
    }

    private BigDecimal takeKilogramsAndMeters(BigDecimal kilograms, BigDecimal meters) {
        BigDecimal pounds = kilograms.multiply(BigDecimal.valueOf(1).divide(
                BigDecimal.valueOf(0.45359237), 100, RoundingMode.HALF_UP));
        BigDecimal totalInches = meters.multiply(BigDecimal.valueOf(1).divide(
                BigDecimal.valueOf(0.0254), 100, RoundingMode.HALF_UP));
        BigInteger feet = BigInteger.valueOf(totalInches.intValue()).divide(BigInteger.valueOf(12));
        BigDecimal inches = totalInches.subtract(BigDecimal.valueOf(feet.multiply(BigInteger.valueOf(12)).intValue()));
        System.out.println("==========================");
        System.out.printf("Weight (lb): %.2f%n", pounds);
        System.out.printf("Height (ft): %d%n", feet);
        System.out.printf("Height (in): %.2f%n", inches);
        System.out.println("==========================");
        return kilograms.divide(meters.multiply(meters), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal takePoundsAndInches(BigDecimal pounds, BigDecimal inches) {
        BigDecimal kilograms = pounds.multiply(BigDecimal.valueOf(0.45359237));
        BigDecimal meters = inches.multiply(BigDecimal.valueOf(0.0254));
        return kilograms.divide(meters.multiply(meters), 2, RoundingMode.HALF_UP);
    }
}
