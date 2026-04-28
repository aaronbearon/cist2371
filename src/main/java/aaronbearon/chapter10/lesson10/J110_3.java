package aaronbearon.chapter10.lesson10;

import java.math.BigDecimal;

import java.math.RoundingMode;

/**
 * Aaron Blum
 * Java Lab Part 3
 * 2025-11-16
 */
public class J110_3 {
    /**
     * Test Euler with sample precisions
     */
    public static void main(String[] args) {
        int[] preciseNums = {1, 2, 5, 10, 50, 100, 200};
        Euler euler = new Euler();
        for (int i = 1; i < preciseNums.length; i++) {
            euler.setPrecision(preciseNums[i]);
            System.out.printf("Euler precision %d: %.20f%n", euler.getPrecision(), euler.getEuler());
        }
    }
}

/**
 * Euler helps approximate Euler's number.
 */
class Euler {
    /**
     * Number of terms to add for approximation
     */
    private int precision;

    /**
     * Prevents rewriting complex code
     */
    private static final BigDecimal ONE = BigDecimal.valueOf(1);

    /**
     * No arg constructor
     */
    public Euler() {
        this(1);
    }

    /**
     * Constructor which accepts the number of terms
     *
     * @param precision Highest factorial number
     */
    public Euler(int precision) {
        this.precision = precision;
    }

    /**
     * Sets the precision
     *
     * @param precision Highest factorial number
     */
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    /**
     * Get the number of terms in the calculation.
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Computes approximate euler with the given number of terms
     */
    public BigDecimal getEuler() {
        BigDecimal euler = ONE;
        BigDecimal divisor = ONE;
        for (int i = 1; i <= precision; i++) {
            divisor = divisor.multiply(BigDecimal.valueOf(i));
            euler = euler.add(ONE.divide(divisor, 20, RoundingMode.HALF_UP));
        }

        return euler;
    }
}
