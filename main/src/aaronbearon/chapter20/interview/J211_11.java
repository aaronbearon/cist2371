package aaronbearon.chapter20.interview;

import java.util.ArrayList;
import java.util.Scanner;

public class J211_11 {
    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        students.add(new Student("Henry", "Java"));
        students.add(new Student("Ben", "Python"));
        students.add(new Student("Ava", "C++"));
        students.add(new Student("Jane", "Java"));
        students.add(new Student("Emma", "Python"));
        printStudents();
    }

    public static void printStudents() {
        Scanner input = new Scanner(System.in);
        System.out.print("Certificate? ");
        String certificate = input.nextLine();
        System.out.println("Student(s) with a Java Certificate");
        System.out.println("-------------------------------------------");
        for (Student student : students) {
            if (certificate.equals(student.getCertificate())) {
                System.out.println(student.getName());
            }
        }
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
