package aaronbearon.chapter12;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 3 Part 5
 * Description: Create a file with the specified name.
 * Write your fields on top of the Germany_Brazil.txt text to your file.
 * Create your own class to use with the files.
 */
public class J203_5 {

    private static final String NAME = "Aaron";
    private static final String NUMBER = "1417";

    //* Search and replace words used later are here.
    //* Involves regular expressions.
    private static final FieldSwapper[] fieldSets = {
            new FieldSwapper("Germany", "Brazil"),
            new FieldSwapper("\\ba\\b", "the"),
            new FieldSwapper("\\bA\\b", "The")
    };

    public static void main(String[] args) throws Exception {
        File newFile = new File("J203_" + NAME + NUMBER + ".txt");
        File oldFile = new File("Germany_Brazil.txt");

        //* Nested if inside if/else/if
        if (!oldFile.exists()) {
            System.out.println("Old file doesn't exist.");
            if (newFile.exists()) {
                System.out.println("New file already exists.");
            }
        } else if (newFile.exists()) {
            System.out.println("New file already exists.");
        } else {
            writeOldToFile(oldFile, newFile);
        }
    }

    public static void writeOldToFile(File inFile, File outFile) throws Exception {
        try (Scanner in = new Scanner(inFile);
             PrintWriter out = new PrintWriter(outFile)
        ) {
            out.println(NAME + " " + NUMBER);
            while (in.hasNext()) {
                String line = in.nextLine();

                //* Use for each to search and replace
                for (FieldSwapper fields : fieldSets) {
                    line = fields.ReplaceAll(line);
                }

                out.println(line);
            }
        }
    }
}

//* Extra class as required
//* Useful for searching and replacing
class FieldSwapper {
    private final String search;
    private final String replace;

    public FieldSwapper(String search, String replace) {
        this.search = search;
        this.replace = replace;
    }

    public String ReplaceAll(String in) {
        return in.replaceAll(this.search, this.replace);
    }
}

/*
 * Stored search and replace pairs as new objects in an array.
 * Used regular expressions to only replace the word "a".
 * Wrote personal fields followed by old file text to the new file.
 * Used the //* symbol to show original creativity.
 */
