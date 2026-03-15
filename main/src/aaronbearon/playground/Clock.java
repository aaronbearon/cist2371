package aaronbearon.playground;

public class Clock {
    public static void main(String[] args) {
        for (int k = 0; k < 24; k++) {
            for (int j = 0; j < 60; j++) {
                for (int i = 0; i < 60; i++) {
                    System.out.print(" " + k + ":" + j + ":" + i);
                }

                System.out.println();
            }
        }
    }
}
