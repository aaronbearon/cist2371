package aaronbearon.chapter8.interview;

public class J108_11 {
    public static void main(String[] args) {
        int[][] arr = genArr();
        printArr(arr);
    }

    public static int[][] genArr() {
        int[][] arr = new int[3][5];
        for (int j = 0, k = 1; j < arr[0].length; j++) {
            for (int i = 0; i < arr.length; i++, k++) {
                arr[i][j] = k;
            }
        }

        return arr;
    }

    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }
    }
}
