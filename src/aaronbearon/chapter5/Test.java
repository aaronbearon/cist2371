package aaronbearon.chapter5;

public class Test {
    public static void main(String[] args) {
        for (int i = 2; i <= 15; i++) {
            System.out.printf("Pi (%2d): %." + i + "f%n", i, Math.PI);
        }
    }
}
