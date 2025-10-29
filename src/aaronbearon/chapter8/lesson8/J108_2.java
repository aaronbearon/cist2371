package aaronbearon.chapter8.lesson8;

import java.util.Arrays;

import java.util.Scanner;

import static java.lang.Character.toLowerCase;

public class J108_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][][] school = {
                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}},
                {{19, 20, 21}, {22, 23, 24}, {25, 26, 27}},
                {{28, 29, 30}, {31, 32, 33}, {34, 35, 36}},
                {{37, 38, 39}, {40, 41, 42}, {43, 44, 45}}
        };

        System.out.println("This school has semesters: Fall, Spring, and Summer.");
        System.out.println("Each semester has classes: A, B, and C.");

        // Input semesters
        System.out.print("Which semester(s)? Fall(1), Spring(2) or Summer(3)? ");
        String semester = input.nextLine();
        boolean[] semesters = new boolean[3];
        for (char c : semester.toCharArray()) {
            // Ignore any invalid characters and just process valid input chars.
            if (c >= '1' && c <= '3') {
                semesters[c - '1'] = true;
            }
        }

        // System.out.println(Arrays.toString(semesters));

        System.out.print("Which subject? ");
        String subject = input.nextLine();
        boolean[] subjects = new boolean[3];
        for (char c : subject.toCharArray()) {
            c = toLowerCase(c);
            if (c >= 'a' && c <= 'c') {
                subjects[c - 'a'] = true;
            }
        }

        // System.out.println(Arrays.toString(subjects));
        System.out.println("Subject Semester Ave");

        for (int sem = 0; sem < 3; sem++) {
            if (semesters[sem]) {
                for (int sub = 0; sub < 3; sub++) {
                    if (subjects[sub]) {
                        computeAndDisplayAverage(sem, sub, school);
                    }
                }
            }
        }
    }

    public static void computeAndDisplayAverage(int sem, int sub, int[][][] school) {
        int sum = 0;
        for (int i = 0; i < school.length; i++) {
            sum += school[i][sem][sub];
        }

        System.out.print(String.valueOf((char) (sub + 'A')) + "       ");
        switch (sem) {
            case 0:
                System.out.print("Fall     ");
                break;
            case 1:
                System.out.print("Spring   ");
                break;
            case 2:
                System.out.print("Summer   ");
                break;
            default:
                System.out.print("Unknown error!");
                System.exit(1);
        }

        double average = Math.round(sum * 10.0 / school.length) / 10.0;
        System.out.println(average);
    }
}
