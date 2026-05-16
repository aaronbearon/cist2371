package aaronbearon.summer2026;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorization {
    public static void main(String[] args) {
        PrimesManager p = PrimesManager.setPrimesTillMax(104730);
        p.printPrimes();
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

    /* TODO: Write a method to return the smallest prime factor of a number.
        It should be the smallest prime to check for in once the big number is divided by that prime.
        Example: Since 1001 is first discovered to be a multiple of 7 and then 1001 / 7 = 143,
            check 143 for being a multiple of 7 first.
        This will eventually print out the prime factorization of every number up to MAX.
    */
}
