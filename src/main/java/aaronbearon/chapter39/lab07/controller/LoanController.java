package aaronbearon.chapter39.lab07.controller;

import aaronbearon.chapter39.lab07.model.Loan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Aaron Blum, CIST 2373 Java 3, Lab 7, Loan Controller.
 */
@Controller
public class LoanController {

    // Fill out the initial form with default settings, but don't compute the result.
    @GetMapping({"/", "/calculatePayment"})
    public String showForm(Model model) {
        Loan loan = new Loan();
        model.addAttribute("loanAmountRaw", String.valueOf(loan.getLoanAmount()));
        model.addAttribute("interestRateRaw", String.valueOf(loan.getAnnualInterestRate()));
        model.addAttribute("yearsRaw", String.valueOf(loan.getNumberOfYears()));
        return "loan";
    }

    // Upon form submission, the RequestParam uses the values from the input form.
    @PostMapping("/calculatePayment")
    public String calculateLoan(
            @RequestParam("loanAmountStr") String loanAmountStr,
            @RequestParam("interestRateStr") String interestRateStr,
            @RequestParam("yearsStr") String yearsStr,
            Model model) {

        // Important, save the user's data!
        model.addAttribute("loanAmountRaw", loanAmountStr);
        model.addAttribute("interestRateRaw", interestRateStr);
        model.addAttribute("yearsRaw", yearsStr);

        boolean hasErrors = false;
        double loanAmount = 0;
        double interestRate = 0;
        int years = 0;

        // Handle invalid field data.
        try {
            loanAmount = Double.parseDouble(loanAmountStr);
            if (loanAmount < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            model.addAttribute("loanAmountErr", "Must be a positive decimal!");
            hasErrors = true;
        }

        try {
            interestRate = Double.parseDouble(interestRateStr);
            if (interestRate < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            model.addAttribute("interestRateErr", "Must be a positive decimal!");
            hasErrors = true;
        }

        try {
            years = Integer.parseInt(yearsStr);
            if (years < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            model.addAttribute("yearsErr", "Must be a positive integer!");
            hasErrors = true;
        }

        if (hasErrors) {
            // early exit without processing
            return "loan";
        }

        try {
            Loan loan = new Loan(interestRate, years, loanAmount);

            double monthlyPayment = loan.getMonthlyPayment();
            double totalPayment = loan.getTotalPayment();

            // Handle out-of-range, or large numbers.
            if (Double.isInfinite(monthlyPayment) || Double.isNaN(monthlyPayment) ||
                    Double.isInfinite(totalPayment) || Double.isNaN(totalPayment)) {
                throw new ArithmeticException("Calculation boundaries exceeded.");
            }

            // Bind these values to the model.
            model.addAttribute("loanAmount", loan.getLoanAmount());
            model.addAttribute("interestRate", loan.getAnnualInterestRate());
            model.addAttribute("years", loan.getNumberOfYears());
            model.addAttribute("monthlyPayment", monthlyPayment);
            model.addAttribute("totalPayment", totalPayment);
        } catch (Exception e) {
            model.addAttribute("bottomErrorMessage",
                    "Processing Error: The calculated loan amounts exceed application limits.");
        }

        return "loan";
    }
}
