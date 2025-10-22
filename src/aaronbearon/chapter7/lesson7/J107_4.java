package aaronbearon.chapter7.lesson7;

import java.util.Scanner;

public class J107_4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final int SIZE = 10;
        char[] key = {'c', 'o', 'n', 'v', 'e', 'r', 's', 'i', 'n', 'g'};
        char[] filling = new char[SIZE];
        buildProcessArray(filling);
        int[] indices = new int[SIZE];
        while (true) {
            System.out.print("Guess one character (lowercase) in my array: ");
            char ch = input.next().charAt(0);
            if (ch == '-' || ch == '0') {
                break;
            }

            validateInput(ch);

            int spots = search(ch, indices, filling, key);
            printResults(ch, spots, indices, filling, key);
            resetIndices(indices);
        }

        System.out.println("Ok, done!");
    }

    public static void buildProcessArray(char[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = '_';
        }
    }

    public static void validateInput(char c) {
        if (c < 'a' || c > 'z') {
            System.err.println("Invalid char");
            System.exit(1);
        }
    }

    public static int search(char c, int[] indices, char[] filling, char[] key) {
        int j = 0;
        for (int i = key.length - 1; i >= 0; i--) {
            if (key[i] == c) {
                filling[i] = c;
                indices[j] = i + 1;
                j++;
            }
        }

        return j;
    }

    public static void printResults(char c, int spots, int[] indices, char[] filling, char[] key) {
        System.out.println("    1 2 3 4 5 6 7 8 9 0");
        System.out.print("My array: ");
        for (int i = 0; i < filling.length; i++) {
            System.out.print(filling[i]);
        }

        System.out.println();

        if (spots != 0) {
            System.out.print("Good: " + c + " is in ");
            for (int i = spots - 1; i >= 0; i--) {
                System.out.print(indices[i]);

                if (i != 0) {
                    System.out.print(" and ");
                }
            }
        } else {
            System.out.print("Nope");
        }

        System.out.println();
        System.out.println("----------------------------------------------------------------------");
    }

    public static void resetIndices(int[] indices) {
        for (int i = indices.length - 1; i >= 0; i--) {
            indices[i] = 0;
        }
    }
}
