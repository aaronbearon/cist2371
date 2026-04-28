package aaronbearon.chapter12;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class J203_5b {
    private static final String NAME = "Scott";
    private static final String NUMBER = "5935";

    private static class Repl {
        private final String search;
        private final String replace;

        private Repl(String search, String replace) {
            this.search = search;
            this.replace = replace;
        }

        private String ReplaceAll(String in) {
            return in.replaceAll(this.search, this.replace);
        }
    }

    private static final Repl[] REPLACEMENTS = new Repl[]{
            new Repl("Germany", "Brazil"),
            new Repl("\\ba\\b", "the"),
            new Repl("\\bA\\b", "The")
    };

    public static void main(String[] args) throws Exception {
        File newFile = new File("J203_" + NAME + NUMBER + ".txt");
        File oldFile = new File("Germany_Brazil.txt");
        if (!oldFile.exists()) {
            System.out.println("Old file doesn't exist.");
            if (newFile.exists()) {
                System.out.println("New file already exists.");
            }
        } else if (newFile.exists()) {
            System.out.println("New file already exists.");
        } else {
            writeOldToFile(oldFile, newFile, REPLACEMENTS);
        }
    }

    static void writeOldToFile(File inFile, File outFile, Repl[] repls) throws Exception {
        try (Scanner in = new Scanner(inFile); PrintWriter out = new PrintWriter(outFile)) {
            // Leading header.
            out.println(NAME + " " + NUMBER);
            while (in.hasNext()) {
                String line = in.nextLine();
                for (Repl repl : repls) {
                    line = repl.ReplaceAll(line);
                }

                out.println(line);
            }
        }
    }
}
