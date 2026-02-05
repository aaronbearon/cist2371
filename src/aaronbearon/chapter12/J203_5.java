package aaronbearon.chapter12;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class J203_5 {
    public static void main(String[] args) throws Exception {
        FileManager fields = new FileManager();
        File newFile = new File(fields.getField(4) + fields.getField(5) +
                fields.getField(6) + fields.getField(7));
        File oldFile = new File("Germany_Brazil.txt");
        if (!oldFile.exists()) {
            System.out.println("Old file doesn't exist.");
            if (newFile.exists()) {
                System.out.println("New file already exists.");
            }
        } else if (newFile.exists()) {
            System.out.println("New file already exists.");
        } else {
            writeOldToFile(oldFile, newFile, fields);
        }
    }

    static void writeOldToFile(File inFile, File outFile, FileManager fields) throws Exception {
        PrintWriter out = new PrintWriter(outFile);
        out.println("First name: " + fields.getField(5) +
                "\r\nLast 4 phone number digits: " + fields.getField(6));
        Scanner in = new Scanner(inFile);
        while (in.hasNext()) {
            String s1 = in.nextLine();
            String s2 = s1.replaceAll(fields.getField(0), fields.getField(1));
            String s3 = s2.replaceAll(fields.getField(2), fields.getField(3));
            out.println(s3);
        }

        in.close();
        out.close();
    }
}

class FileManager {
    private final String[] fields = {"Germany", "Brazil", " a ", " the ",
            "J203_", "Aaron", "1417", ".txt"};

    public String getField(int i) {
        return fields[i];
    }
}
