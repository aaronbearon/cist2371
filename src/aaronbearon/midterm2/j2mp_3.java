package aaronbearon.midterm2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Midterm Porject Part 3
 * Description:
 * Refer to part 3 summary
 */
public class j2mp_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Payroll employee;

        // Break at the bottom of the try catch block
        while (true) {
            // Forced entry of valid basic employee fields
            try {

                // name
                System.out.print("What's his/her name? ");
                String name = input.nextLine();

                // ID
                System.out.print("Please enter the ID number: ");
                int id = input.nextInt();

                employee = new Payroll(name, id);
                break;

                // Unchecked exception
            } catch (InputMismatchException e) {
                System.out.println("Error, invalid input!");

                // Checked exceptions
            } catch (InvalidNameException | InvalidIDException e) {
                System.out.println("Error: " + e.getMessage());
            }

            input.nextLine();
        }

        // Break at the bottom of the try catch block
        while (true) {
            // Forced entry of valid hours and pay
            try {

                // Hourly pay rate
                System.out.print("What is " + employee.getNAME() + "'s pay rate? ");
                int rate = input.nextInt();
                employee.setRate(rate);

                // Hours worked during the week
                System.out.print("How many hours did " + employee.getNAME() + " work this week? ");
                int hours = input.nextInt();
                employee.setWork(hours);
                break;

                // Unchecked exception
            } catch (InputMismatchException e) {
                System.out.println("Error, invalid input!");

                // Checked exceptions
            } catch (InvalidPayRateException | InvalidHoursException e) {
                System.out.println("Error: " + e.getMessage());
            }

            input.nextLine();
        }

        // Output to user (only if data passed validation)
        System.out.print("#" + employee.getID() + ": " + employee.getNAME());
        System.out.println("'s gross pay is $" + employee.getGrossPay() + ".");
    }
}

class Payroll {
    // Don't change these after object creation
    private final String NAME;
    private final int ID;

    // Modify with care
    private int hourlyPayRate;
    private int hoursWorked;

    /** Check the name and id before initialization */
    public Payroll(String NAME, int ID) throws InvalidNameException, InvalidIDException {
        if (NAME.isEmpty()) {
            throw new InvalidNameException("Name can't be empty!");
        }

        if (ID < 0) {
            throw new InvalidIDException("ID must be positive!");
        }

        this.NAME = NAME;
        this.ID = ID;
    }

    /** Check the pay rate before initialization */
    public void setRate(int hourlyPayRate) throws InvalidPayRateException {
        if (hourlyPayRate < 0 || hourlyPayRate > 25) {
            throw new InvalidPayRateException("Hours Worked must be in range of 0 and 25!");
        }

        this.hourlyPayRate = hourlyPayRate;
    }

    /** Check the hours worked before initialization */
    public void setWork(int hoursWorked) throws InvalidHoursException {
        if (hoursWorked < 0 || hoursWorked > 84) {
            throw new InvalidHoursException("Pay rate must be in range of 0 and 84!");
        }

        this.hoursWorked = hoursWorked;
    }

    // Important calculation
    public int getGrossPay() {
        return hourlyPayRate * hoursWorked;
    }

    // Get final fields for user info
    public String getNAME() {
        return NAME;
    }

    public int getID() {
        return ID;
    }
}

// Custom exceptions for the different Payroll fields
class InvalidNameException extends Exception {
    public InvalidNameException(String message) {
        super(message);
    }
}

class InvalidIDException extends Exception {
    public InvalidIDException(String message) {
        super(message);
    }
}

class InvalidPayRateException extends Exception {
    public InvalidPayRateException(String message) {
        super(message);
    }
}

class InvalidHoursException extends Exception {
    public InvalidHoursException(String message) {
        super(message);
    }
}
