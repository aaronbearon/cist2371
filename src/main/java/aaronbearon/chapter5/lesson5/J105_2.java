package aaronbearon.chapter5.lesson5;

/**
 Lab 5, Part 2 - Multiplication Table
 Aaron Blum
 */
public class J105_2 {
    public static void main(String[] args) {
        // Produces the column headers.
        System.out.print("  |");
        int j = 1;
        while (j <= 9) {
            System.out.print("  " + j);
            j++;
        }

        System.out.println("\n------------------------------");

        // Produces multiplication table.
        int i = 1;
        while (i <= 9) {
            System.out.print(i + " |");
            j = 1;
            while (j <= 9) {
                System.out.printf("%3d", i * j);
                j++;
            }
            System.out.println();
            i++;
        }
    }
}
