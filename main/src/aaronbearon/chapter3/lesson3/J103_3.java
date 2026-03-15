package aaronbearon.chapter3.lesson3;

/**
 * Lab 3, Part 3
 * Aaron Blum
 */
public class J103_3 {
    static void m3_1(int number1, int number2) {
        // 3-1. This code is a number sorter from greatest to least.
        // It does this by comparing each number, and switching them if the first number is less.
        if (number1 < number2) {    // Like if number1 is 5 and number2 is 7, then 5 is less than 7
            int temp = number1;     // Then temp would become 5
            number1 = number2;      // number1 becomes 7
            number2 = temp;         // number2 becomes 5. Now they are sorted from greatest to least.
        }
    }

    static void m3_2_a(int N) {
        // 3-2. Translate the given logical expression into plain language and then fill in the placeholder.
        if (N % 3 == 0 && N % 5 == 0) {
            // If N is a multiple of both 3 and 5.
            // The statement is true when N is a multiple of 15.
            // So if N is 75, then this is true. If N is 19, this is false.
        }
    }

    static void m3_2_b(int N) {
        // 3-2b. Convert each Boolean condition into words and fill in the placeholder.
        // Determine if N is leap year.
        if ((N % 4) == 0) {             // 1st if range: line 1 ~ line 9
            // When N is divisible by 4 (like N is 2024)
            // Could be a leap year, keep checking.
            if ((N % 100) == 0) {       // 2nd if range: line 2 ~ line 6
                // When N is divisible by 100 (like N is 1900)
                // Could be a leap year, keep checking.
                if ((N % 400) == 0) {   // 3rd if range: line 3 ~ line 5
                    // When N is divisible by 400 (like N is 2000)
                    // Yes, this is a leap year (divisible by 400)
                } else {
                    // When N is divisible by 100, not 400
                    // Not a leap year (like 1700)
                }
            } else {                    // else range: line 6 ~ line 8
                // Yes, this is a leap year (divisible by 4, not 100. Like 2024.)
            }
        } else {
            // Not a leap year (not divisible by 4. Like 2021.)
        }
    }

    static void m3_3(int N) {
        if ((N % 4 == 0 && N % 100 != 0) || (N % 400 == 0)) {
            // If N is a multiple of 400, or N is a multiple of 4 without being a multiple of 100.
            // The statement is true when N = a leap year.
        }
    }

    static void m3_4() {
        // 3-4. Compare the algorithm in 3-2 and 3-3.
        // 3-2 and 3-3 both contain leap year calculators.
        // The algorithm in 3-2 uses nested if statements, while the algorithm in 3-3 uses one large condition.
        // Both algorithms accurately determine if a year is a leap year, but 3-3 is better because it's simplified.
    }

    static void m3_5(int A, int B) {
        // 3-5. Translate the given logical expression into plain language and then
        // fill in the placeholder.
        int A1 = A / 10;    // A1 is the tens digit of A.
        int A2 = A % 10;    // A2 is the ones digit of A.
        int B1 = B / 10;    // B1 is the tens digit of B.
        int B2 = B % 10;    // B2 is the ones digit of B.
        if (A == B) {
            // When A and B are the same (like A and B are both 56)
        } else if (B2 == A1 && B1 == B2) {
            // When switching A's digits gets you B's digits (like A is 43, B is 34)
        } else if (B1 == A1 || B1 == A2 || B2 == A1 || B2 == A2) {
            // When A and B have at least one common digit (like A is 93 and B is 19)
        } else {
            // When A and B have no common digits (like A is 11 and B is 24)
        }
    }
}
