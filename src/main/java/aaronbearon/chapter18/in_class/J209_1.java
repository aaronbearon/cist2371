package aaronbearon.chapter18.in_class;

public class J209_1 {
    public static void main(String[] args) {
        long n = 1;
        System.out.println(n + "! = " + doFactorial(n));
    }

    public static long doFactorial(long n) {
        if (n < 1) {
            return 1;
        } else {
            return n * doFactorial(n - 1);
        }
    }
}
