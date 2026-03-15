package aaronbearon.playground;

import java.util.Scanner;

public class Shaper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the rectangle dimensions.");
        System.out.print("Width: ");
        int w = input.nextInt();
        if (w < 0) {
            w *= -1;
        }

        System.out.print("Height: ");
        int h = input.nextInt();
        if (h < 0) {
            h *= -1;
        }

        System.out.println("Here is a visual layout of the rectangle below:");
        System.out.println();
        for (int j = 0; j <= h; j++) {
            if (j != 0) {
                for (int i = 0; i <= w; i++) {
                    if (i != 0) {
                        System.out.print("    ");
                    }

                    System.out.print("|");
                }

                System.out.println();
            }

            for (int i = 0; i <= w; i++) {
                if (i != 0) {
                    System.out.print("----");
                }

                System.out.print("+");
            }

            System.out.println();
        }

        System.out.println();
        System.out.print("As you can see above, the number of slots is " + (w * h) + ".");
    }
}
