package aaronbearon.chapter18.lesson9;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 9 part 4
 * Description: Write and test the binary search with recursion, without copying the textbook.
 */
public class J209_4 {
    public static void main(String[] args) {
        // The testing array required by the lab instructions.
        int[] list = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        System.out.println("First array:");
        for (int i = -1; i <= list.length; i++) {
            System.out.printf("Index of %d: %d%n", i, binarySearch(list, i));
        }

        System.out.println();
        // Another array, but odd numbers can report negative indices.
        list = new int[]{20, 18, 16, 14, 12, 10, 8, 6, 4, 2, 0};

        System.out.println("Second array:");
        for (int i = -1; i <= 21; i++) {
            System.out.printf("Index of %d: %d%n", i, binarySearch(list, i));
        }
    }

    //* Collapse the variables into one return statement.
    //* No need for list.length - 1
    public static int binarySearch(int[] list, int key) {
        return doBinarySearch(list, key, 0, list.length);
    }

    /**
     * doBinarySearch performs the binary search
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

        //* Sanity check the input list's ordering if we have enough elements.
        if (mid != start && list[mid] >= list[start]) {
            System.out.println("WARNING: Array not sorted or non-unique!");
        }
        if (mid != limit - 1 && list[mid] <= list[limit - 1]) {
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

I made the binary search only work on arrays sorted descending order.

I also added a detector against arrays that aren't truly descending.
This only works on some numbers, but can alert the user about a sorting problem, unless it's non-existent.

For example an array {12, 11, 10, 9, 8, 9, 7}.
No problems can be detected while searching for 12, but 9 will detect a problem.

Basically, since binary search only works on sorted arrays,
    unsorted arrays can now be detected in some way.

*/
