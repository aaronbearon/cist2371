package aaronbearon.playground;

public class ImmutableArrayDemo {
    private static final ArrayHolder container = new ArrayHolder(new int[][][]{
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
            {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}},
            {{19, 20, 21}, {22, 23, 24}, {25, 26, 27}}
    });

    public static void main(String[] args) {
        int[][][] arr = new int[][][]{{{1}}};
        ArrayHolder a = new ArrayHolder(arr);
        arr = new int[][][]{{{1, 2}, {3, 4}}, {{5, 6}, {7, 8}}};
        ArrayHolder b = new ArrayHolder(arr);
        printArray(container);
        printArray(a);
        printArray(b);
    }

    private static void printArray(ArrayHolder b) {
        for (int i = 0; i < b.getSize(); i++) {
            for (int j = 0; j < b.getSize(i); j++) {
                for (int k = 0; k < b.getSize(i, j); k++) {
                    System.out.print(b.getVal(i, j, k) + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }
}

class ArrayHolder {
    private final int[][][] array;

    public ArrayHolder(int[][][] sourceArray) {
        int[][][] targetArray = new int[sourceArray.length][][];
        for (int i = 0; i < sourceArray.length; i++) {
            targetArray[i] = new int[sourceArray[i].length][];
            for (int j = 0; j < sourceArray[i].length; j++) {
                targetArray[i][j] = new int[sourceArray[i][j].length];
                for (int k = 0; k < sourceArray[i][j].length; k++) {
                    targetArray[i][j][k] = sourceArray[i][j][k] + 1;
                    targetArray[i][j][k] -= 1;
                }
            }
        }
        array = targetArray;
    }

    public int getSize() {
        return array.length;
    }

    public int getSize(int i) {
        return array[i].length;
    }

    public int getSize(int i, int j) {
        return array[i][j].length;
    }

    public int getVal(int i, int j, int k) {
        return array[i][j][k];
    }
}
