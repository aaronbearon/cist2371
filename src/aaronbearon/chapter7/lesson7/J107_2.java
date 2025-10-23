package aaronbearon.chapter7.lesson7;

/**
 * Aaron Blum
 * Java Lab 7
 * 2025-10-22
 */
public class J107_2 {
    /**
     * Read arguments off the command line and do a simple addition problem.
     */
    public static void main(String[] args) {
        int result = 0;
        switch (args[1].charAt(0)) {
            case '+':
                result = Integer.parseInt(args[0]) + Integer.parseInt(args[2]);
                break;
        }
        String A = args[0] + ' ' + args[1] + ' ' + args[2] + " = " + result;
        System.out.println(A);
    }
}
