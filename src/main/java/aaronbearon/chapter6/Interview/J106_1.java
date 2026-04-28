package aaronbearon.chapter6.Interview;

import java.util.Arrays;

public class J106_1 {
    public static void main(String[] args) {
//        double[] myList = new double[10]; // -> {0, 0, 0, 0, 0, 0}
//        double[] myList2 = {0, 0, 0, 0, 0};
//        System.out.println(Arrays.toString(myList));
//        System.out.println(Arrays.toString(myList2));

        double[] myList = {1, 2, 3, 4, 5};
        double temp = myList[0];
        for (int i = 0, max = 4; i < max; ) {
            myList[i] = myList[i + 1];
            System.out.println(myList[i]);
            i++;
            if (i == max) {
                myList[i] = temp;
                System.out.println(myList[i]);
            }
        }

        System.out.println(Arrays.toString(myList));
    }
}
