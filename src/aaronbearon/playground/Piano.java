package aaronbearon.playground;

public class Piano {
    public static void main(String[] args) {
        System.out.println("You'll see a whole piano below:");
        System.out.println();
        mane();
        System.out.println();
    }

    private static void mane() {
        for (int j = 0; j < 7; j++) {
            char jChar = (char) (j + 'C');
            for (int i = 0; i < 4; i++) {
                System.out.print(jChar);
                System.out.print('1');
            }
        }
    }
}
