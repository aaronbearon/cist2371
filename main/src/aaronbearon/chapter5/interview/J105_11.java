package aaronbearon.chapter5.interview;

public class J105_11 {
    public static void main(String[] args) {
        System.out.println("Below, you'll see PI printed up to 15 decimal places.");
        for (int i = 2; i <= 15; i++) {
            System.out.printf("Pi (%2d): %." + i + "f%n", i, Math.PI);
        }
    }
}
