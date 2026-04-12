package aaronbearon.chapter21.interview;

import java.util.HashMap;
import java.util.Scanner;

public class J212_12 {
    private static final HashMap<Integer, Student> hashMap = new HashMap<>();

    public static void main(String[] args) {
        hashMap.put(0, new Student("Henry", "Java"));
        hashMap.put(1, new Student("Ben", "Python"));
        hashMap.put(2, new Student("Ava", "C++"));
        hashMap.put(3, new Student("Jane", "Java"));
        hashMap.put(4, new Student("Emma", "Python"));
        printStudents();
    }

    public static void printStudents() {
        Scanner input = new Scanner(System.in);
        System.out.print("Certificate? ");
        String certificate = input.nextLine();
        System.out.println("Student(s) with a Java Certificate");
        System.out.println("-------------------------------------------");
        hashMap.forEach((_, s) -> {
            if (certificate.equals(s.getCertificate())) {
                System.out.println(s.getName());
            }
        });
    }
}

class Student {
    private final String name;
    private final String certificate;

    public Student(String name, String certificate) {
        this.name = name;
        this.certificate = certificate;
    }

    public String getName() {
        return name;
    }

    public String getCertificate() {
        return certificate;
    }
}
