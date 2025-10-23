package aaronbearon.chapter7.interview;

public class J107_1 {
    public static void main(String[] args) {
        char[] array = genChar();
        printChar(array);
    }

    public static char[] genChar() {
        char[] array = new char[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) ('A' + (int) (Math.random() * 52));
            if (array[i] > 'Z') {
                array[i] = (char) (((array[i] - 26) - 'A') + 'a');
            }
        }

        return array;
    }

    public static void printChar(char[] array) {
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }

        System.out.println();
    }
}
