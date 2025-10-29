package aaronbearon.chapter8.lesson8;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Character.toLowerCase;

/**
 * Aaron Blum
 * Java Lab 8
 * 2025-10-29
 */
public class J108_2 {
    /** Table of recorded grades. */
    private static final int[][][] gradeBySubjectBySemesterByStudent = {
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
            {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}},
            {{19, 20, 21}, {22, 23, 24}, {25, 26, 27}},
            {{28, 29, 30}, {31, 32, 33}, {34, 35, 36}},
            {{37, 38, 39}, {40, 41, 42}, {43, 44, 45}}
    };

    /** Number of students. */
    private static final int numStudents = gradeBySubjectBySemesterByStudent.length;

    /** fixed-width labels of each semester name for printing a chart */
    private static final String[] semesterNames = {"Fall     ", "Spring   ", "Summer   "};

    /** fixed-width labels of each subject name for printing a chart */
    private static final String[] subjectNames = {"A       ", "B       ", "C       "};


    /**
     * A program to compute the average grade in each class in different semesters.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("This school has semesters: Fall, Spring, and Summer.");
        System.out.println("Each semester has classes: A, B, and C.");

        while (true) {
            // Input semesters
            boolean[] semesters = inputSemesters(input);
            if (Arrays.equals(semesters, new boolean[3])) {
                break;
            }

            boolean[] subjects = inputSubjects(input);
            computeAndDisplayAverages(semesters, subjects);
        }

        System.out.println("Thank you.");
    }

    /**
     * Prompts the user to enter what semesters to process.
     *
     * @param input the scanner
     * @return a 3-element array of the selected semesters
     */
    public static boolean[] inputSemesters(Scanner input) {
        System.out.print("Which semester(s)? Fall(1), Spring(2) or Summer(3)? ");
        String semester = input.nextLine();
        boolean[] semesters = new boolean[3];
        for (char c : semester.toCharArray()) {
            // Ignore any invalid characters and just process valid input chars.
            if (c >= '1' && c <= '3') {
                semesters[c - '1'] = true;
            }
        }

        return semesters;
    }

    /**
     * Prompts the user to enter what subjects to process.
     *
     * @param input the scanner
     * @return a 3-element array of the selected subjects
     */
    public static boolean[] inputSubjects(Scanner input) {
        System.out.print("Which subject(s)? A, B, C? ");
        String subject = input.nextLine();
        boolean[] subjects = new boolean[3];
        for (char c : subject.toCharArray()) {
            c = toLowerCase(c);
            if (c >= 'a' && c <= 'c') {
                subjects[c - 'a'] = true;
            }
        }

        return subjects;
    }

    /**
     * Compute the average for each semester * each subject and display to user.
     *
     * @param semesters the semesters to process
     * @param subjects  the subjects to process
     */
    public static void computeAndDisplayAverages(boolean[] semesters, boolean[] subjects) {
        System.out.println("Subject Semester Ave");
        for (int sem = 0; sem < 3; sem++) {
            if (semesters[sem]) {
                for (int sub = 0; sub < 3; sub++) {
                    if (subjects[sub]) {
                        computeAndDisplayAverage(sem, sub);
                    }
                }
            }
        }
    }

    /**
     * Compute the average for the given semester and subject and display to user.
     *
     * @param semester the semester to process
     * @param subject  the subject to process
     */
    public static void computeAndDisplayAverage(int semester, int subject) {
        int sum = 0;
        for (int i = 0; i < numStudents; i++) {
            sum += gradeBySubjectBySemesterByStudent[i][semester][subject];
        }

        System.out.print(subjectNames[subject]);
        System.out.print(semesterNames[semester]);

        double average = (double) (sum) / numStudents;
        System.out.printf("%.1f%n", average);
    }
}
