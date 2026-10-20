package aaronbearon.chapter38;

import java.io.Serializable;

/**
 * ComputeTax is the main java bean that powers ComputeTax.jsp
 */
public class ComputeTax implements Serializable {
    /** the income the user entered */
    private double income = 0.0;
    /** the filing status the user entered */
    private Status status = Status.SINGLE;

    /* Bean properties */

    public double getIncome() {
        return income;
    }

    public void setIncome(double newValue) {
        income = newValue;
    }

    public int getStatus() {
        return status.ordinal();
    }

    public void setStatus(int status) {
        // Note: this will throw if an invalid status is set.
        this.status = Status.values()[status];
    }

    /* Bean methods */

    /**
     * computeTax returns the tax due based on the current bean state.
     * It uses the previously set income and status.
     */
    public double computeTax() {
        return computeTax(income, taxMileStonesByStatus[status.ordinal()]);
    }

    /* Helper types, data, and methods */

    /**
     * Status documents the four supported filing statuses a person can have in the tax system.
     */
    private static enum Status {
        SINGLE,
        MARRIED_JOINTLY,
        MARRIED_SEPARATELY,
        HEAD_HOUSEHOLD
    }

    /**
     * Each element represents the marginal tax rate at each level of progression in the tax system.
     * There are exactly six marginal tax rates that apply to every person.
     */
    private static final double[] taxRates = {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};

    /**
     * Each element in the outer array represents one filing status, based on ordinal (0 to 3).
     * <p>
     * Each element of each inner array represents the starting dollar value for the marginal tax rate bracket,
     * which matches the marginal tax rates above. There are six elements in each bucket because there are six
     * effective tax rates.
     * <p>
     * For example, for Single taxpayers, 372950 in income is the start of the highest marginal tax rate (35%).
     */
    private final double[][] taxMileStonesByStatus = {
            {0, 8350, 33950, 82250, 171550, 372950},
            {0, 16700, 67900, 137050, 208850, 372950},
            {0, 8350, 33950, 68525, 104425, 186475},
            {0, 11950, 45500, 117450, 190200, 372950}
    };

    private static double computeTax(double income, double[] taxMileStones) {
        double totalTax = 0;
        for (int i = 5; i >= 0; i--) {
            if (income > taxMileStones[i]) {
                double excess = income - taxMileStones[i];
                totalTax += excess * taxRates[i];
                income = taxMileStones[i];
            }
        }
        assert income == 0;
        return totalTax;
    }
}
