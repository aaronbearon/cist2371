package aaronbearon.midterm2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class j2mp_1 {
    public static void main(String[] args) throws IOException {
        StatsCollection data = StatsCollector.readFile();

        int limit = 10;
        System.out.println("Here are the " + limit + " most common numbers below:");
        printNumbersWithFrequency(Sort.byFrequencyDescending(data.getAllNumbers()), limit);
        System.out.println("Here are the " + limit + " least common numbers below:");
        printNumbersWithFrequency(Sort.byFrequencyAscending(data.getAllNumbers()), limit);
        System.out.println("Here are the " + limit + " most overdue numbers below:");
        printNumbersWithLastSeen(Sort.byLastSeenAscending(data.getAllNumbers()), limit);
        limit = 9999;
        System.out.println("Here are the frequencies of the regular (non-powerball) numbers:");
        printFrequencies(data.getNormalNumbers(), limit);
        System.out.println("Here are the frequencies of the powerball numbers:");
        printFrequencies(data.getPowerballNumbers(), limit);
    }

    static void printNumbersWithFrequency(NumberStats[] numbers, int limit) {
        limit = Math.min(numbers.length, limit);
        for (int i = 0; i < limit; i++) {
            System.out.printf("%2d.    %2d: %d times%n", i + 1, numbers[i].getID(), numbers[i].getCount());
        }

        System.out.println();
    }

    static void printNumbersWithLastSeen(NumberStats[] numbers, int limit) {
        limit = Math.min(numbers.length, limit);
        for (int i = 0; i < limit; i++) {
            int week = numbers[i].getFilePosition() / 6 + 1;
            System.out.printf("%2d. %2d: last seen week %d%n", i + 1, numbers[i].getID(), week);
        }

        System.out.println();
    }

    static void printFrequencies(NumberStats[] numbers, int limit) {
        limit = Math.min(numbers.length, limit);
        for (int i = 0; i < limit; i++) {
            System.out.printf("%2d: %d times%n", numbers[i].getID(), numbers[i].getCount());
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

    public NumberStats[] getNormalNumbers() {
        return Arrays.copyOf(normalNumbers, normalNumbers.length);
    }

    public NumberStats[] getPowerballNumbers() {
        return Arrays.copyOf(powerballNumbers, powerballNumbers.length);
    }

    public NumberStats[] getAllNumbers() {
        return Arrays.copyOf(allNumbers, allNumbers.length);
    }
}

class StatsCollector {
    public static StatsCollection readFile() throws IOException {
        StatsCollection result = new StatsCollection();

        try (FileReader file = new FileReader("pbnumbers.txt");
             Scanner in = new Scanner(file)) {
            int currentFilePosition = 0; // track the index of the number we're reading in the whole file
            while (in.hasNext()) {
                int id = in.nextInt();

                if (currentFilePosition % 6 < 5) { // powerball numbers are at position 5, 11, 17, 23, ...
                    result.getNormalNumbers()[id - 1].observe(currentFilePosition);
                } else {
                    result.getPowerballNumbers()[id - 1].observe(currentFilePosition);
                }

                result.getAllNumbers()[id - 1].observe(currentFilePosition);
                currentFilePosition++;
            }
        }

        return result;
    }
}

class Sort {
    public static NumberStats[] byFrequencyAscending(NumberStats[] in) {
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
