package aaronbearon.chapter37.lab08;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;

/**
 * Aaron Blum, CIST 2373 Java 3, Lab 8, Loan Controller with Servlet.
 */
@WebServlet({"/", "/calculatePayment"})
public class ComputeLoanServlet extends HttpServlet {
    private static final String tmplBlank = loadTemplate("calculatePaymentBlank.html");
    private static final String tmplComplete = loadTemplate("calculatePaymentComplete.html");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        String html = tmplBlank.formatted();
        response.getWriter().append(html);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        // Store user input
        String loanAmountRaw = request.getParameter("loanAmountStr");
        String interestRateRaw = request.getParameter("interestRateStr");
        String loanYearsRaw = request.getParameter("loanYearsStr");

        boolean hasErrors = false;
        String loanAmountErr = "";
        String interestRateErr = "";
        String loanYearsErr = "";

        double loanAmount = 0;
        double interestRate = 0;
        int loanYears = 0;

        // Variables for resulting output
        String loanAmountNew = "";
        String interestRateNew = "";
        String loanYearsNew = "";
        String monthlyPaymentNew = "";
        String totalPaymentNew = "";
        String bottomErrorMessage = "";

        // Handle invalid field data.
        try {
            loanAmount = Double.parseDouble(loanAmountRaw);
            if (loanAmount < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            loanAmountErr = "Must be a positive decimal!";
            hasErrors = true;
        }

        try {
            interestRate = Double.parseDouble(interestRateRaw);
            if (interestRate < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            interestRateErr = "Must be a positive decimal!";
            hasErrors = true;
        }

        try {
            loanYears = Integer.parseInt(loanYearsRaw);
            if (loanYears < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            loanYearsErr = "Must be a positive integer!";
            hasErrors = true;
        }

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumIntegerDigits(1);
        percentFormat.setMaximumFractionDigits(2);

        // Avoid further processing if a field has an error.
        if (!hasErrors) {
            loanAmountNew = String.valueOf(currencyFormat.format(loanAmount));
            interestRateNew = String.valueOf(percentFormat.format(interestRate / 100));
            loanYearsNew = String.valueOf(loanYears);
            try {
                Loan loan = new Loan(interestRate, loanYears, loanAmount);

                double monthlyPayment = loan.getMonthlyPayment();
                double totalPayment = loan.getTotalPayment();

                // Handle out-of-range, or large numbers.
                if (Double.isInfinite(monthlyPayment) || Double.isNaN(monthlyPayment) ||
                        Double.isInfinite(totalPayment) || Double.isNaN(totalPayment)) {
                    throw new ArithmeticException("Calculation boundaries exceeded.");
                }
                monthlyPaymentNew = String.valueOf(currencyFormat.format(monthlyPayment));
                totalPaymentNew = String.valueOf(currencyFormat.format(totalPayment));
            } catch (Exception e) {
                bottomErrorMessage = "Processing Error: The calculated loan amounts exceed application limits.";
            }
        }

        // Populate the resulting html with the data.
        response.setContentType("text/html; charset=UTF-8");
        String html = tmplComplete.formatted(
                loanAmountRaw, loanAmountErr,
                interestRateRaw, interestRateErr,
                loanYearsRaw, loanYearsErr,
                loanAmountNew,
                interestRateNew,
                loanYearsNew,
                monthlyPaymentNew,
                totalPaymentNew,
                bottomErrorMessage);
        response.getWriter().append(html);
    }

    // A name without a leading "/" is looked up relative to this class's package.
    private static String loadTemplate(String htmlName) {
        try (InputStream in = ComputeLoanServlet.class.getResourceAsStream(htmlName)) {
            if (in == null) {
                throw new IllegalStateException("Template not found on classpath: " + htmlName);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not read template: " + htmlName, e);
        }
    }
}
