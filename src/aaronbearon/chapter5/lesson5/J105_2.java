package aaronbearon.chapter5.lesson5;

public class J105_2 {
    public static void main(String[] args) {
        int j = 1;
        while (j <= 9) {
            System.out.print(" " + j);
            j++;
        }

        System.out.println("\n-----------------------------------------");
        multiLoops();
    }

    public static void multiLoops() {

        // Produces multiplication table.
        int i = 1;
        while (i <= 9) {
            System.out.print(i + " | ");
            int j = 1;
            while (j <= 9) {
                System.out.printf("%4d", (i * j));
                j++;
            }
            System.out.println();
            i++;
        }
    }
}
