package aaronbearon.midterm2;

public class j2mp_2 {
    public static void main(String[] args) {
        // use a 12% interest rate for a clean monthly 1% rate
        SavingsAccount s = new SavingsAccount(0, 0.12);
        System.out.printf("Expect active: FALSE, got: %B%n", s.isActive());
        System.out.printf("Expect balance: 0, got: $%.2f%n", s.getBalance());

        s.deposit(30);
        System.out.printf("Expect active: TRUE, got: %B%n", s.isActive());
        System.out.printf("Expect balance: 30, got: $%.2f%n", s.getBalance());

        s.withdraw(20);
        System.out.printf("Expect active: FALSE, got: %B%n", s.isActive());
        System.out.printf("Expect balance: 10, got: $%.2f%n", s.getBalance());

        s.monthlyProcess();
        System.out.printf("Expect active: FALSE, got: %B%n", s.isActive());
        System.out.printf("Expect balance: 10.10, got: $%.2f%n", s.getBalance());

        // Make several deposits and withdrawals to trigger a service charge this month.
        for (int i = 0; i < 10; i++) {
            s.deposit(30);
            s.withdraw(30);
        }
        // we will get charged $6 for 10 withdrawals (>4)
        // but we'll make a few more pennies interest
        s.monthlyProcess();
        System.out.printf("Expect active: FALSE, got: %B%n", s.isActive());
        System.out.printf("Expect balance: 4.14, got: $%.2f%n", s.getBalance());
    }
}

abstract class BankAccount {
    protected final double annualRate;
    protected double balance;
    protected int depositsThisMonth = 0;
    protected int withdrawalsThisMonth = 0;
    protected double monthlyCharges = 0;

    protected BankAccount(double balance, double annualRate) {
        if (balance < 0 || annualRate < 0) {
            throw new RuntimeException("Error, you can't enter negative numbers!");
        }

        this.balance = balance;
        this.annualRate = annualRate;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Error, only positive values allowed!");
        }

        balance += amount;
        depositsThisMonth++;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Error, only positive values allowed!");
        }

        balance -= amount;
        withdrawalsThisMonth++;
    }

    protected void calcInterest() {
        double monthlyRate = annualRate / 12;
        double monthlyInterest = balance * monthlyRate;
        balance += monthlyInterest;
    }

    public void monthlyProcess() {
        balance -= monthlyCharges;
        monthlyCharges = 0.0;
        calcInterest();
        withdrawalsThisMonth = depositsThisMonth = 0;
    }

    public double getBalance() {
        return balance;
    }
}

class SavingsAccount extends BankAccount {
    private boolean active;

    public SavingsAccount(double balance, double annualRate) {
        super(balance, annualRate);
        active = balance >= 25;
    }

    @Override
    public void withdraw(double amount) {
        if (!active) {
            throw new RuntimeException("account is inactive!");
        }
        super.withdraw(amount);
        active = balance >= 25;
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        active = balance >= 25;
    }

    @Override
    public void monthlyProcess() {
        if (withdrawalsThisMonth > 4) {
            monthlyCharges += withdrawalsThisMonth - 4;
        }
        super.monthlyProcess();
        active = balance >= 25;
    }

    public boolean isActive() {
        return active;
    }
}
