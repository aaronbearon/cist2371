package aaronbearon.chapter7.interview;

public class J107_12 {
    public static void main(String[] args) {
        char[] array = genChar();
        printChar(array);
    }

    public static char[] genChar() {
        char[] array = new char[20];
        for (int i = 0; i < array.length; i++) {
            int rand = (int) (Math.random() * 52);
            if (rand < 26) {
                array[i] = (char) (rand + 'A');
            } else {
                array[i] = (char) (rand + 'a' - 26);
            }
        }

        return array;
    }

    public static void printChar(char[] array) {
        for (char c : array) {
            System.out.print(c + " ");
        }

        System.out.println();
    }
}
