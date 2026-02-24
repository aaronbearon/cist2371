package aaronbearon.midterm2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class j2mp_1 {
    public static void main(String[] args) throws IOException {
        DataHolder d = StatisticsCollector.openFile();
    }
}

class StatisticsCollector {
    private static final int[] counters = new int[69];
    private static final int[] lastInstances = new int[69];
    private static final int[] powerBalls = new int[35];

    public static DataHolder openFile() throws IOException {
        int totalNumbers = 0;
        int regularNumbers = 0;
        int powerBallNumbers = 0;
        int currentFileNum = 0;
        try (FileReader file = new FileReader("pbnumbers.txt");
             Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                currentFileNum = in.nextInt();
                totalNumbers += 1;
                if (totalNumbers % 6 != 0) {
                    regularNumbers += 1;
                    counters[currentFileNum - 1] += 1;
                    lastInstances[currentFileNum - 1] = regularNumbers;
                } else {
                    powerBallNumbers += 1;
                    powerBalls[currentFileNum - 1] += 1;
                }
            }
        }

        return new DataHolder(counters, lastInstances, powerBalls);
    }
}

class DataHolder extends StatisticsCollector {
    private final int[][] data;

    protected DataHolder(int[] counters, int[] lastInstances, int[] powerBalls) {
        data = new int[][]{counters, lastInstances, powerBalls};
    }
}

/*

System.out.println(currentFileNum);
System.out.println(totalNumbers);
System.out.println(regularNumbers);
System.out.println(powerBallNumbers);
for (int i : counters) {
    System.out.print(i + " ");
}

System.out.println();
for (int i : powerBalls) {
    System.out.print(i + " ");
}

System.out.println();
for (int i : lastInstances) {
    System.out.print(i + " ");
}

*/
