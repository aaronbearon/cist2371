package aaronbearon.chapter41.lab10.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double loanAmount;
    private Double interestRate;
    private Integer years;

    /** Validate fields */
    public void validate() throws IllegalArgumentException {
        if (loanAmount < 500) {
            throw new IllegalArgumentException("Loan amount must be 500 or higher.");
        }
        if (interestRate <= 0 || interestRate >= 100) {
            throw new IllegalArgumentException("interestRate must be (0..100).");
        }
        if (years < 1 || years > 100) {
            throw new IllegalArgumentException("Years must be between [1..100].");
        }
    }

    /** Find monthly payment */
    public double getMonthlyPayment() {
        double monthlyInterestRate = interestRate / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
                (1 / Math.pow(1 + monthlyInterestRate, years * 12)));
        return monthlyPayment;
    }

    /** Find total payment */
    public double getTotalPayment() {
        double totalPayment = getMonthlyPayment() * years * 12;
        return totalPayment;
    }
}
