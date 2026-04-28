package aaronbearon.playground;

public class Table {
    public static void main(String[] args) {
        int height = 32;
        int width = 64;
        int y = (int) (Math.random() * height);
        int x = (int) (Math.random() * width);
        for (int j = 0; j < height; j++) {
            System.out.println();
            for (int i = 0; i < width; i++) {
                if (y == j && x == i) {
                    System.out.print("O");
                } else {
                    System.out.print("0");
                }
            }
        }

        System.out.println();
    }
}
