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
            } catch (InvalidNameException | InvalidIdException e) {
                System.out.println("Error: " + e.getMessage());
            }

            input.nextLine();
        }

        // Break at the bottom of the try catch block
        while (true) {
            // Forced entry of valid hours and pay
            try {
                // Hourly pay rate
                System.out.print("What is " + employee.getName() + "'s pay rate? ");
                double rate = input.nextDouble();
                employee.setRate(rate);
                // Hours worked during the week
                System.out.print("How many hours did " + employee.getName() + " work this week? ");
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
        System.out.printf("#%d: %s's gross pay is $%.2f.%n", employee.getId(), employee.getName(), employee.getGrossPay());
    }
}

class Payroll {
    // Don't change these after object creation
    private final String name;
    private final int id;
    // Modify with care
    private double hourlyPayRate;
    private int hoursWorked;

    /** Check the name and id before initialization */
    public Payroll(String name, int id) throws InvalidNameException, InvalidIdException {
        if (name.isEmpty()) {
            throw new InvalidNameException();
        }

        if (id < 0) {
            throw new InvalidIdException();
        }

        this.name = name;
        this.id = id;
    }

    /** Check the pay rate before initialization */
    public void setRate(double hourlyPayRate) throws InvalidPayRateException {
        if (hourlyPayRate < 0 || hourlyPayRate > 25) {
            throw new InvalidPayRateException();
        }

        this.hourlyPayRate = hourlyPayRate;
    }

    /** Check the hours worked before initialization */
    public void setWork(int hoursWorked) throws InvalidHoursException {
        if (hoursWorked < 0 || hoursWorked > 84) {
            throw new InvalidHoursException();
        }

        this.hoursWorked = hoursWorked;
    }

    // Important calculation
    public double getGrossPay() {
        return hourlyPayRate * hoursWorked;
    }

    // Get final fields for user info
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

// Custom exceptions for the different Payroll fields
class InvalidNameException extends Exception {
    public InvalidNameException() {
        super("name can't be empty!");
    }
}

class InvalidIdException extends Exception {
    public InvalidIdException() {
        super("id must be positive!");
    }
}

class InvalidPayRateException extends Exception {
    public InvalidPayRateException() {
        super("Pay rate must be in range of 0 and 25");
    }
}

class InvalidHoursException extends Exception {
    public InvalidHoursException() {
        super("Hours Worked must be in range of 0 and 84!");
    }
}

/*
Refer to documentation and updated doc part 3.
*/
