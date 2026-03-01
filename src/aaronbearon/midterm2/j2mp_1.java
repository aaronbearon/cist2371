package aaronbearon.midterm2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Midterm Porject Part 1
 * Description:
 * Refer to part 1 summary
 */
public class j2mp_1 {
    public static void main(String[] args) throws IOException {
        // Get filled NumberStats data
        StatsCollection data = StatsCollector.readFile();

        // Top tens
        int limit = 10;
        System.out.println("Here are the " + limit + " most common numbers below:");
        printNumbersWithFrequency(Sort.byFrequencyDescending(data.getAllNumbers()), limit);
        System.out.println("Here are the " + limit + " least common numbers below:");
        printNumbersWithFrequency(Sort.byFrequencyAscending(data.getAllNumbers()), limit);
        System.out.println("Here are the " + limit + " most overdue numbers below:");
        printNumbersWithLastSeen(Sort.byLastSeenAscending(data.getAllNumbers()), limit);

        // All numbers' frequencies
        System.out.println("Here are the frequencies of the regular (non-powerball) numbers:");
        printFrequencies(data.getNormalNumbers());
        System.out.println("Here are the frequencies of the powerball numbers:");
        printFrequencies(data.getPowerballNumbers());
    }

    /** Prints most frequent or least frequent numbers */
    static void printNumbersWithFrequency(NumberStats[] numbers, int limit) {
        limit = Math.min(numbers.length, limit);
        for (int i = 0; i < limit; i++) {
            System.out.printf("%2d.    %2d: %d times%n", i + 1, numbers[i].getID(), numbers[i].getCount());
        }

        System.out.println();
    }

    /** Prints numbers by last file position
     * Note, the last week seen is what's displayed.
     * The last week on the list is 654. */
    static void printNumbersWithLastSeen(NumberStats[] numbers, int limit) {
        limit = Math.min(numbers.length, limit);
        for (int i = 0; i < limit; i++) {
            // Add one to the week so the first week is 1 like file line 1 (instead of 0)
            int week = numbers[i].getFilePosition() / 6 + 1;
            System.out.printf("%2d. %2d: last seen week %d%n", i + 1, numbers[i].getID(), week);
        }

        System.out.println();
    }

    /** Prints the frequencies of each number in a certain type */
    static void printFrequencies(NumberStats[] numbers) {
        for (NumberStats number : numbers) {
            System.out.printf("%2d: %d times%n", number.getID(), number.getCount());
        }

        System.out.println();
    }
}

/**
 * NumberStats holds stats for numbers that can be either powerball or not.
 */
class NumberStats {
    private final int id; // the actual number, 1-69
    private int count; // how many times seen
    private int filePosition; // the last time it was seen in the file

    public NumberStats(int id) {
        this.id = id;
    }

    // For every file number read:
    // Increment the count
    // Set the filePosition in case it's the last instance of the file
    public void observe(int filePosition) {
        this.count++;
        this.filePosition = filePosition;
    }

    public int getID() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public int getFilePosition() {
        return filePosition;
    }
}

/**
 * StatsCollection holds separate statistics for normal numbers, powerball numbers, and all numbers in general.
 */
class StatsCollection {
    private final NumberStats[] normalNumbers = new NumberStats[69];
    private final NumberStats[] powerballNumbers = new NumberStats[35];
    private final NumberStats[] allNumbers = new NumberStats[69];

    // In each iteration, a NumberStats object with id[i + 1]
    //  is put inside the given numbers array
    // ID range is 1-69 or 1-35, not 0-68 or 0-34
    public StatsCollection() {
        for (int i = 0; i < 69; i++) {
            normalNumbers[i] = new NumberStats(i + 1);
        }

        for (int i = 0; i < 35; i++) {
            powerballNumbers[i] = new NumberStats(i + 1);
        }

        for (int i = 0; i < 69; i++) {
            allNumbers[i] = new NumberStats(i + 1);
        }
    }

    /**
     * WARNING: Don't return the actual array (reference)!
     * Return a copy of the array to prevent careless external modification.
     */
    public NumberStats[] getNormalNumbers() {
        return Arrays.copyOf(normalNumbers, normalNumbers.length);
    }

    public NumberStats[] getPowerballNumbers() {
        return Arrays.copyOf(powerballNumbers, powerballNumbers.length);
    }

    public NumberStats[] getAllNumbers() {
        return Arrays.copyOf(allNumbers, allNumbers.length);
    }

    public void observe(int id, int currentFilePosition) {
        // For each number, observe it as both an "all" number, and either a powerball/non-powerball
        // number as well.
        if (currentFilePosition % 6 < 5) { // powerball numbers are at position 5, 11, 17, 23, ...
            normalNumbers[id - 1].observe(currentFilePosition);
        } else {
            powerballNumbers[id - 1].observe(currentFilePosition);
        }

        allNumbers[id - 1].observe(currentFilePosition);
    }
}

class StatsCollector {
    public static StatsCollection readFile() throws IOException {
        // Create StatsCollection object storing arrays of objects with their IDs.
        StatsCollection result = new StatsCollection();

        try (FileReader file = new FileReader("pbnumbers.txt");
             Scanner in = new Scanner(file)) {
            int currentFilePosition = 0; // track the index of the number we're reading in the whole file
            while (in.hasNext()) {
                int id = in.nextInt();
                result.observe(id, currentFilePosition);
                currentFilePosition++;
            }
        }

        // The NumberStats data populated with necessary contents goes back to main
        return result;
    }
}

/** Holds the necessary sorting methods */
class Sort {
    public static NumberStats[] byFrequencyAscending(NumberStats[] in) {
        // Syntax for sorting an array of objects
        Arrays.sort(in, new Comparator<NumberStats>() {
            @Override
            public int compare(NumberStats a, NumberStats b) {
                return Integer.compare(a.getCount(), b.getCount());
            }
        });
        return in;
    }

    public static NumberStats[] byFrequencyDescending(NumberStats[] in) {
        Arrays.sort(in, new Comparator<NumberStats>() {
            @Override
            public int compare(NumberStats a, NumberStats b) {
                return -Integer.compare(a.getCount(), b.getCount()); // backwards
            }
        });
        return in;
    }

    public static NumberStats[] byLastSeenAscending(NumberStats[] in) {
        Arrays.sort(in, new Comparator<NumberStats>() {
            @Override
            public int compare(NumberStats a, NumberStats b) {
                return Integer.compare(a.getFilePosition(), b.getFilePosition());
            }
        });
        return in;
    }
}

/*
Refer to documentation and updated doc part 1.
*/
