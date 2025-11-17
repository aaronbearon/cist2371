package aaronbearon.chapter10.lesson10;

/**
 * Aaron Blum
 * Java Lab Part 2
 * 2025-11-16
 */
public class J110_2 {
    /**
     * Test out the BMI class with some examples.
     */
    public static void main(String[] args) {
        //* Smart sample data
        BMI person1 = new BMI(170, 75, false);
        System.out.printf("Your BMI is %.2f which is %s.%n", person1.getBMI(), person1.getStatus());
        BMI person2 = new BMI(73, 2.5, true);
        System.out.printf("Your BMI is %.2f which is %s.%n", person2.getBMI(), person2.getStatus());
        BMI person3 = new BMI(220.2, 56.3, false);
        System.out.printf("Your BMI is %.2f which is %s.%n", person3.getBMI(), person3.getStatus());
        BMI person4 = new BMI(109.5, 2, true);
        System.out.printf("Your BMI is %.2f which is %s.%n", person4.getBMI(), person4.getStatus());
    }
}

/**
 * BMI helps calculate a person's body mass index and whether they are healthy or not.
 */
class BMI {
    //* Remove unused fields; don't need the name or age for this problem.
    //* Made class immutable.
    private final double weight; // in pounds or kilograms, depending on isMetric
    private final double height; // in inches or meters, depending on isMetric
    private final boolean isMetric; //* New field to track metric vs. english units.

    // Conversion constants.
    private static final double KILOGRAMS_PER_POUND = 0.45359237;
    private static final double METERS_PER_INCH = 0.0254;

    /**
     * BMI constructs a new object.
     *
     * @param weight   in Kilograms or Pounds
     * @param height   in Meters or Inches
     * @param isMetric Sets kilograms/meter or pounds/inch
     */
    public BMI(double weight, double height, boolean isMetric) {
        this.weight = weight;
        this.height = height;
        this.isMetric = isMetric;
    }

    /**
     * Returns the person's BMI : kilogram weight divided by the square of meter height.
     */
    public double getBMI() {
        //* Combines the two converters into one simple selection.
        if (isMetric) {
            return weight / Math.pow(height, 2); //* use Math.pow
        } else {
            // Convert pounds/inch^2 to kilograms/meter^2.
            return weight * KILOGRAMS_PER_POUND / Math.pow(height * METERS_PER_INCH, 2); //* use Math.pow
        }
    }

    /**
     * Returns the person's BMI description.
     */
    public String getStatus() {
        double bmi = getBMI();
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
