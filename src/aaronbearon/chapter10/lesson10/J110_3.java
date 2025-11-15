package aaronbearon.chapter10.lesson10;

import java.math.BigDecimal;

import java.math.RoundingMode;

public class J110_3 {
    public static void main(String[] args) {
        int[] preciseNums = {1, 2, 5, 10, 50, 100, 200};
        Euler euler = new Euler();
        for (int i = 1; i < preciseNums.length; i++) {
            euler.setPrecision(preciseNums[i]);
            System.out.printf("Euler precision %d: %.20f%n", euler.getPrecision(), euler.getEuler());
        }
    }
}

class Euler {
    private int precision;

    Euler() {
        this(1);
    }

    Euler(int precision) {
        this.precision = precision;
    }

    void setPrecision(int precision) {
        this.precision = precision;
    }

    int getPrecision() {
        return precision;
    }

    BigDecimal getEuler() {
        return calculateEuler();
    }

    private BigDecimal calculateEuler() {
        BigDecimal euler = new BigDecimal("1.0");
        BigDecimal divisor = new BigDecimal("1.0");
        for (int i = 1; i <= precision; i++) {
            divisor = divisor.multiply(BigDecimal.valueOf(i));
            euler = euler.add(BigDecimal.valueOf(1).divide(divisor, 20, RoundingMode.HALF_UP));
        }

        return euler;
    }
}
