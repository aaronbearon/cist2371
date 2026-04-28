package aaronbearon.chapter19;

import java.util.Comparator;
import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 10
 * Description: Update binary search to use generics.
 */
public class J210_2 {
    // Person helps test generic search on non-comparable objects.
    private static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        int key = getKey();
        // Test array
        Integer[] asc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // The required array based on the instructions
        Integer[] desc = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        {
            int found = binarySearch(asc, key);
            System.out.printf("Index of %d ascending: %d%n", key, found);
        }
        {
            int found = biSearch(desc, key);
            System.out.printf("Index of %d descending: %d%n", key, found);
        }

        // This is in order both by name and age.
        Person[] people = new Person[]{
                new Person("Aaron", 20),
                new Person("Dad", 48),
                new Person("Mom", 50),
        };

        System.out.println();
        System.out.println("Person array:");
        // Find "Aaron" in index 0 of person array.
        int found = binarySearch(people, Comparator.comparing(p -> p.name), people[0]);
        System.out.printf("Index of Aaron by name: %d%n", found);
        // Find 48 in index 1 of person array.
        found = binarySearch(people, Comparator.comparingInt(p -> p.age), people[1]);
        System.out.printf("Index of Dad by age: %d%n", found);
    }

    /**
     * biSearch searches a list that's sorted in descending order, to comply with lab instructions.
     */
    public static <E extends Comparable<E>> int biSearch(E[] list, E key) {
        return binarySearch(list, Comparator.<E>reverseOrder(), key);
    }

    /**
     * binarySearch searches a list that's sorted in ascending order, which is more normal.
     *
     * @param list the array of values to search
     * @param key  the thing to search for
     * @param <E>  element type
     * @return index of the search key
     */
    public static <E extends Comparable<E>> int binarySearch(E[] list, E key) {
        return binarySearch(list, Comparator.<E>naturalOrder(), key);
    }

    /**
     * binarySearch searches a list that's sorted in ascending order, with a given comparator.
     *
     * @param list the array of values to search
     * @param cmp  the comparison function
     * @param key  the thing to search for
     * @param <E>  element type
     * @return index of the search key
     */
    public static <E> int binarySearch(E[] list, Comparator<E> cmp, E key) {
        return binarySearch(list, cmp, key, 0, list.length);
    }

    /**
     * This binary search can take an array and perform a search of any datatype.
     *
     * @param list  the array of values to search
     * @param cmp   the comparison function
     * @param key   the thing to search for
     * @param start lowest index to be searched
     * @param limit one more than the highest search index
     * @param <E>   element type
     * @return index of the search key
     */
    public static <E> int binarySearch(E[] list, Comparator<E> cmp, E key, int start, int limit) {
        if (start == limit) {
            return -start - 1;
        }

        int mid = (start + limit) / 2;

        // Written to handle ascending list
        if (cmp.compare(key, list[mid]) < 0) {
            return binarySearch(list, cmp, key, start, mid);
        } else if (cmp.compare(key, list[mid]) > 0) {
            return binarySearch(list, cmp, key, mid + 1, limit);
        } else {
            return mid;
        }
    }

    // Validate input
    public static int getKey() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Please enter a value to search for: ");
                return input.nextInt();
            } catch (Exception e) {
                System.out.println("Error, please enter an integer.");
            }
        }
    }
}

/*

I used scoping in the main method to redeclare the found variable for testing arrays.
I created a person class to test the binary search on non-comparable objects.

My four binary search methods are basically:

biSearch (reverse to ascend)
binarySearch (use natural order)

those two call another method:

binarySearch (which is basically ready to call the real binary search method with the real implementation)

The E is a generic type which can be any type that extends Comparable.

Comparator<E> cmp
and
Comparator.<E>***Order()

are important for making non-comparable objects work.
Comparators are talked about in chapter 20, which I read before submitting this lab.

*/
