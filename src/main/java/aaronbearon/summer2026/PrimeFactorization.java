package aaronbearon.summer2026;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorization {
    private static final int MAX = 25000;

    public static void main(String[] args) {
        PrimesManager p = PrimesManager.setPrimesTillMax(MAX);
        p.printFactors();
    }
}

class PrimesManager {
    private final int MAX;
    private final List<Integer> primes = new ArrayList<>();

    // Prevent class from being extended.
    private PrimesManager(int MAX) {
        this.MAX = MAX;
    }

    // Create an object, fill its primes, then return the object.
    public static PrimesManager setPrimesTillMax(int max) {
        if (max < 2) {
            throw new IllegalArgumentException();
        }

        PrimesManager p = new PrimesManager(max);
        p.fillPrimes();
        return p;
    }

    // Fill the List with primes upon object creation.
    private void fillPrimes() {
        for (int i = 2; i <= MAX; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
    }

    // Pass any number and check if it's prime.
    private boolean isPrime(int number) {
        for (Integer prime : primes) {
            if (number % prime == 0 && number != prime) {
                return false;
            } else if (number < prime * prime) {
                return true;
            }
        }

        return true;
    }

    // Debug printing of primes
    public void printPrimes() {
        for (int i = 0; i < primes.size(); i++) {
            if (i % 10 == 0) {
                System.out.println();
                System.out.print(1 + (i / 10) + "...   ");
            }

            System.out.print(primes.get(i) + "  ");
        }
    }

    public void printFactors() {
        System.out.print("                ");
        for (int i = 2; i <= MAX; i++) {
            printFactorizations(i);
            if (i % 10 == 0) {
                System.out.println();
            } else {
                System.out.print("                ");
            }
        }
    }

    public void printFactorizations(int number) {
        for (Integer prime : primes) {
            if (number % prime == 0 && number != prime) {
                System.out.print(prime + " * ");
                printFactorizations(number / prime);
                return;
            } else if (number < prime * prime) {
                break;
            }
        }

        System.out.print(number);
    }
}
