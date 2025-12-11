package aaronbearon.playground;

public class Storage {
    private static final int SIZE = 26;

    public static void main(String[] args) {
        String[] arr = genLine();
        shuffleItems(arr);
        collapseToGrid(arr);
    }

    public static String[] genLine() {
        String[] line = new String[SIZE * SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                line[i * SIZE + j] = ((char) (i + 'a')) + String.valueOf((char) (j + 'a'));
            }
        }

        return line;
    }

    public static void shuffleItems(String[] line) {
        for (int i = line.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            String temp = line[i];
            line[i] = line[j];
            line[j] = temp;
        }
    }

    public static void collapseToGrid(String[] line) {
        String[][] grid = new String[SIZE][SIZE];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(line, i * grid[i].length, grid[i], 0, grid[i].length);
        }

        printGrid(grid);
    }

    public static void printGrid(String[][] grid) {
        for (String[] items : grid) {
            for (String item : items) {
                System.out.print(item + " ");
            }

            System.out.println();
        }
    }
}
