package aaronbearon.chapter20.interview;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 11
 * Description: Store Student objects in an ArrayList.
 * Retrieve them through their certificate.
 */
public class J211_11 {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Henry", "Java"));
        students.add(new Student("Ben", "Python"));
        students.add(new Student("Ava", "C++"));
        students.add(new Student("Jane", "Java"));
        students.add(new Student("Emma", "Python"));
        printStudents(students);
    }

    /**
     * Input the certificate, and output the students with that certificate.
     *
     * @param students the ArrayList
     */
    static void printStudents(ArrayList<Student> students) {
        Scanner input = new Scanner(System.in);
        System.out.print("Certificate? ");
        String certificate = input.nextLine();
        System.out.println("Student(s) with a " + certificate + " Certificate");
        System.out.println("-------------------------------------------");
        for (Student student : students) {
            if (certificate.equals(student.getCertificate())) {
                System.out.println(student.getName());
            }
        }
    }
}

/**
 * Simple implementation of the immutable Student class (same as interview 12).
 */
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

/*

I used a simple ArrayList to store the students.

*/
