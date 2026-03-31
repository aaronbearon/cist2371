package aaronbearon.chapter18.lesson9;

public class J209_4 {
    public static void main(String[] args) {
        int[] list = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        for (int i = 0; i <= list.length; i++) {
            System.out.printf("Index of %d: %d%n", i, binarySearch(list, i));
        }

        System.out.println();
        list = new int[]{20, 18, 16, 14, 12, 10, 8, 6, 4, 2, 0};

        for (int i = -1; i <= 21; i++) {
            System.out.printf("Index of %d: %d%n", i, binarySearch(list, i));
        }
    }

    public static int binarySearch(int[] list, int key) {
        return doBinarySearch(list, key, 0, list.length);
    }

    /**
     *
     * @param list  The actual array being searched
     * @param key   The value being searched for
     * @param start The start of the slice being searched
     * @param limit The limit of the slice being searched
     * @return index
     */
    public static int doBinarySearch(int[] list, int key, int start, int limit) {
        if (start == limit) {
            return -start - 1;
        }

        int mid = (start + limit) / 2;
        if ((mid < list.length - 1 && list[mid] <= list[mid + 1]) || (list[start] <= list[limit - 1] && start != limit - 1)) {
            System.out.println("WARNING: Array not sorted or non-unique!");
        }

        if (key > list[mid]) {
            return doBinarySearch(list, key, start, mid);
        } else if (key < list[mid]) {
            return doBinarySearch(list, key, mid + 1, limit);
        } else {
            return mid;
        }
    }
}

/*
TODO
*/
