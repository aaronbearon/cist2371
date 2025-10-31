package aaronbearon.playground;

public class Storage {
    public static void main(String[] args) {
        String[][] arr = genArr();
    }

    public static String[][] genArr() {
        String[][] arr = new String[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                arr[i][j] = ((char) (i + 'a')) + String.valueOf((char) (j + 'a'));
                System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }

        return arr;
    }

    public static void shuffleArr(String[][] arr) {
    }

    public static void printArr(String[][] arr) {
    }
}
