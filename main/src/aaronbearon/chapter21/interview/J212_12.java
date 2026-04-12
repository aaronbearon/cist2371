package aaronbearon.chapter21.interview;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview 12
 * Description: Store Student objects in an HashMap with student name as the key.
 * Retrieve them through their certificate.
 */
public class J212_12 {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();
        addStudent(students, new Student("Henry", "Java"));
        addStudent(students, new Student("Ben", "Python"));
        addStudent(students, new Student("Ava", "C++"));
        addStudent(students, new Student("Jane", "Java"));
        addStudent(students, new Student("Emma", "Python"));
        printStudents(students);
    }

    /**
     * Add the students that were created in main method using the name as the key.
     *
     * @param students the hash map
     * @param student  a student created in main method
     */
    static void addStudent(HashMap<String, Student> students, Student student) {
        students.put(student.getName(), student);
    }

    /**
     * Input the certificate, and output the students with that certificate.
     *
     * @param students the hash map
     */
    static void printStudents(HashMap<String, Student> students) {
        Scanner input = new Scanner(System.in);
        System.out.print("Certificate? ");
        String certificate = input.nextLine();
        System.out.println("Student(s) with a " + certificate + " Certificate");
        System.out.println("-------------------------------------------");
        // This is the forEach method.
        students.forEach((_, s) -> {
            if (certificate.equals(s.getCertificate())) {
                System.out.println(s.getName());
            }
        });
    }
}

/**
 * Simple implementation of the immutable Student class (same as interview 11).
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

I created a HashMap with the student's name as the key and the student as the value.
I created each student as I passed it in to the addStudent method.
Then that method adds the student to the hashmap.
This hashmap stores the entries in no particular order.

*/
