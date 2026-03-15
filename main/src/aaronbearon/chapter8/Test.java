package aaronbearon.chapter8;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[][] array = new int[5][3];
        array[2][2] = 508;
        int[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        array[0] = x;
        array[2] = x;
        int[] y = {1, 2, 3, 4, 5, 6, 7, 8};
        //array[0] = y;
        array[1] = new int[6];
        System.out.println(Arrays.deepToString(array));
        x[0] = 100;
        System.out.println(Arrays.deepToString(array));
        System.out.println(array[2][2]);
//        int[] vals = new int[4];
//        int[] newArray = {1, 2, 3, 4, 5, 6};
//        vals = newArray;
//        System.out.println(Arrays.toString(vals));
    }
}
