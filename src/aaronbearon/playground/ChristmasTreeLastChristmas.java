package aaronbearon.playground;

/**
 * Christmas Tree from Java classmate Adam.
 */
public class ChristmasTreeLastChristmas {

    // ANSI escape codes for colors (work in many terminals)
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void main(String[] args) throws InterruptedException {

        // Put lyrics in here
        String[] lyrics = {
                "Last Christmas, I gave you my heart",
                "But the very next day, you gave it away",
                "This year, to save me from tears",
                "I'll give it to someone special",
                "Last Christmas, I gave you my heart",
                "But the very next day, you gave it away (You gave it away)",
                "This year, to save me from tears",
                "I'll give it to someone special (Special)"
        };

        // Array of color choices for the tree lights
        String[] colors = {RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN};


        //    For each lyric line:
        //    clear the screen
        //    pick a color
        //    print the tree in that color
        //    print the lyric line under the tree
        //    pause a bit so you can read it

        for (int i = 0; i < lyrics.length; i++) {
            clearScreen();

            // Pick color based on the line index
            String color = colors[i % colors.length];

            // Print colored tree
            printTree(color);

            // Print the lyric line
            System.out.println();
            System.out.println(lyrics[i]);

            // Wait 1.5 seconds before next line
            Thread.sleep(1500);
        }

        // At the end, reset color
        System.out.println();
        System.out.println(RESET + "Merry Christmas!");
    }

    // Clears the console screen in many terminals using ANSI codes
    // (May not work in all IDE consoles, but it's safe to call.)

    public static void clearScreen() {
        System.out.print("\u001B[2J"); // clear screen
        System.out.print("\u001B[H");  // move cursor to top-left
        System.out.flush();
    }


    // Prints a simple ASCII Christmas tree in the given color

    public static void printTree(String color) {
        System.out.println(color + "           *");
        System.out.println("          ***");
        System.out.println("         *****");
        System.out.println("        *******");
        System.out.println("       *********");
        System.out.println("      ***********");
        System.out.println("     *************");
        System.out.println("    ***************");
        System.out.println("          |||");
        System.out.println("          |||" + RESET); // reset color at the end
    }
}
