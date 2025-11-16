package aaronbearon.chapter10.lesson10;

// Use kilograms/meter and pounds/inch.

// User decides which.

// Calculate BMI : kilogram weight divided by the square of meter height.
public class J110_2 {
    public static void main(String[] args) {
        //* Smart sample data
        BMI person1 = new BMI(170, 75, false);
        System.out.println("BMI is " + person1.getBMI() +
                ", which is " + person1.getStatus() + ".");
        BMI person2 = new BMI(73, 2.5, true);
        System.out.println("BMI is " + person2.getBMI() +
                ", which is " + person2.getStatus() + ".");
        BMI person3 = new BMI(220.2, 56.3, false);
        System.out.println("BMI is " + person3.getBMI() +
                ", which is " + person3.getStatus() + ".");
        BMI person4 = new BMI(109.5, 2, true);
        System.out.println("BMI is " + person4.getBMI() +
                ", which is " + person4.getStatus() + ".");
    }
}

//* Immutable class
class BMI {
    //* These are fixed once the object is created.
    //* Can't access them directly
    private final double weight; // in pounds
    private final double height; // in inches
    private final boolean isMetric;

    //* Flexible value, but no direct access
    private double bmi;

    //* Safe for direct access
    static final double KILOGRAMS_PER_POUND = 0.45359237;
    static final double METERS_PER_INCH = 0.0254;

    BMI(double weight, double height, boolean isMetric) {
        this.weight = weight;
        this.height = height;
        this.isMetric = isMetric;
    }

    double getBMI() {
        //* Combines the two converters into one simple selection.
        if (isMetric) {
            bmi = weight / Math.pow(height, 2);
        } else {
            bmi = weight * KILOGRAMS_PER_POUND / Math.pow(height * METERS_PER_INCH, 2);
        }

        return Math.round(bmi * 100) / 100.0;
    }

    String getStatus() {
        //* Same as before
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
