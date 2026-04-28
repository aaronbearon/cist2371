package aaronbearon.chapter4;

public class Test {
    public static void main(String[] args) {
        final String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 65; i <= 90; i++) {
            System.out.print((char) i);
        }
        for (int i = 97; i <= 122; i++) {
            System.out.print((char) i);
        }

        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.print(alphabets.charAt((int) (Math.random() * alphabets.length())));
        }
    }
}
