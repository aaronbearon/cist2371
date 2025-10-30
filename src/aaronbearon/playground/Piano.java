package aaronbearon.playground;

public class Piano {
    public static void main(String[] args) {
        System.out.println("You'll see a whole piano below:");
        System.out.println();
        for (int l = 0; l < 24; l++) {
            final int MAX = 8;
            for (int k = 0; k <= MAX; k++) {
                int jStart;
                if (k == 0) {
                    jStart = 5;
                    System.out.print('A');
                    System.out.print((char) (k + '0'));
                    System.out.print('A');
                    System.out.print((char) (k + '0'));
                } else {
                    jStart = 0;
                }

                char kChar = (char) (k + '0');
                for (int j = jStart; j < 7; j++) {
                    char jChar = (char) (((j + 2) % 7) + 'A');
                    if (jChar == 'E') {
                        System.out.print(kChar);
                    }

                    for (int i = 0; i < 4; i++) {
                        System.out.print(jChar);
                        System.out.print(kChar);
                    }

                    if (k == MAX) {
                        for (int extra = 0; extra < 3; extra++) {
                            System.out.print('C');
                            System.out.print((char) (MAX + '0'));
                        }

                        break;
                    }

                    if (jChar == 'C') {
                        System.out.print(jChar);
                    }

                    if (jChar != 'E' && jChar != 'B') {
                        for (int i = 0; i < 8; i++) {
                            System.out.print('+');
                        }
                    }
                }
            }

            System.out.println();
        }

        for (int l = 0; l < 16; l++) {
            final int MAX = 8;
            for (int k = 0; k <= MAX; k++) {
                int jStart;
                if (k == 0) {
                    jStart = 5;
                } else {
                    jStart = 0;
                }

                char kChar = (char) (k + '0');
                for (int j = jStart; j < 7; j++) {
                    char jChar = (char) (((j + 2) % 7) + 'A');
                    for (int i = 0; i < 7; i++) {
                        System.out.print(jChar);
                        System.out.print(kChar);
                    }

                    if (k == MAX) {
                        break;
                    }
                }
            }

            System.out.println();
        }
    }
}
