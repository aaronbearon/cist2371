package aaronbearon.playground;

public class Storage {
    public static void main(String[] args) {
        System.out.println();
        String[][] grid = genGrid();
        shuffleGrid(grid);
        printGrid(grid);
    }

    public static String[][] genGrid() {
        final int SIZE = 26;
        String[][] grid = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ((char) (i + 'a')) + String.valueOf((char) (j + 'a'));
            }
        }

        return grid;
    }

    public static void shuffleGrid(String[][] grid) {
        String[] line = spreadGridToLine(grid);
        shuffleLineItems(line);
        collapseLineToGrid(grid, line);
    }

    public static String[] spreadGridToLine(String[][] grid) {
        String[] line = new String[grid.length * grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                line[i * grid[i].length + j] = grid[i][j];
            }
        }

        return line;
    }

    public static void shuffleLineItems(String[] line) {
        for (int i = line.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            String temp = line[i];
            line[i] = line[j];
            line[j] = temp;
        }
    }

    public static void collapseLineToGrid(String[][] grid, String[] line) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = line[i * grid[i].length + j];
            }
        }
    }

    public static void printGrid(String[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }
    }
}
