package aaronbearon.playground;

public class Piano {
    public static void main(String[] args) {
        System.out.println("You'll see a whole piano below:");
        System.out.println();

        // Print top half of piano.
        for (int l = 0; l < 24; l++) {
            final int MAX = 8;

            // Print single row of piano.
            for (int k = 0; k <= MAX; k++) {
                int jStart;

                // Print lowest notes of row.
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

                // Print single octave of row.
                for (int j = jStart; j < 7; j++) {
                    char jChar = (char) (((j + 2) % 7) + 'A');

                    // Adjust spacing.
                    if (jChar == 'E') {
                        System.out.print(kChar);
                    }

                    // Print Letter note followed by octave number.
                    for (int i = 0; i < 4; i++) {
                        System.out.print(jChar);
                        System.out.print(kChar);
                    }

                    // Print highest note of row.
                    if (k == MAX) {
                        for (int extra = 0; extra < 3; extra++) {
                            System.out.print('C');
                            System.out.print((char) (MAX + '0'));
                        }

                        break;
                    }

                    // Adjust spacing.
                    if (jChar == 'C') {
                        System.out.print(jChar);
                    }

                    // Print black keys in necessary places.
                    if (jChar != 'E' && jChar != 'B') {
                        for (int i = 0; i < 8; i++) {
                            System.out.print('+');
                        }
                    }
                }
            }

            System.out.println();
        }

        // Print bottom half of piano.
        for (int l = 0; l < 16; l++) {
            final int MAX = 8;

            // Print single row of piano.
            for (int k = 0; k <= MAX; k++) {

                // Print lowest notes of row.
                int jStart;
                if (k == 0) {
                    jStart = 5;
                } else {
                    jStart = 0;
                }

                char kChar = (char) (k + '0');

                // Print single octave of row.
                for (int j = jStart; j < 7; j++) {

                    // Print Letter note followed by octave number.
                    char jChar = (char) (((j + 2) % 7) + 'A');
                    for (int i = 0; i < 7; i++) {
                        System.out.print(jChar);
                        System.out.print(kChar);
                    }

                    // Allow the highest note to be printed.
                    if (k == MAX) {
                        break;
                    }
                }
            }

            System.out.println();
        }
    }
}
