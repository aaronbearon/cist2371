package aaronbearon.chapter18.lesson9;

import java.util.Scanner;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 9 part 3
 * Description: Simulate the tower of Hanoi with recursion.
 */
public class J209_3 {
    private static final char LEFT = 'A';
    private static final char MIDDLE = 'B';
    private static final char RIGHT = 'C';

    public static void main(String[] args) {
        System.out.println("Please move the Tower of Hanoi from " + LEFT + " to " + RIGHT + ".");
        moveDiscs(LEFT, RIGHT, getNumsCount());
    }

    // Move all discs in minimum move count.
    public static void moveDiscs(char start, char goal, int numDiscs) {
        if (start == goal) {
            throw new IllegalArgumentException("Start and goal cannot be the same");
        }
        if (numDiscs <= 0) {
            throw new IllegalArgumentException("numDiscs must be >= 0");
        }

        char aux = 0;
        if (start != LEFT && goal != LEFT) {
            aux = LEFT;
        }
        if (start != MIDDLE && goal != MIDDLE) {
            aux = MIDDLE;
        }
        if (start != RIGHT && goal != RIGHT) {
            aux = RIGHT;
        }

        if (numDiscs > 1) {
            moveDiscs(start, aux, numDiscs - 1);
        }
        System.out.println("Move disc " + numDiscs + " from " + start + " to " + goal + ".");
        if (numDiscs > 1) {
            moveDiscs(aux, goal, numDiscs - 1);
        }
    }

    // Return the number of non-zero discs and properly handle exceptions.
    public static int getNumsCount() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("How many discs do you want to move? ");
                int count = input.nextInt();
                if (count < 0) {
                    throw new Exception();
                }
                return count;
            } catch (Exception e) {
                System.out.println("Error, please enter an integer.");
            }
        }
    }
}

/*

Tower of Hanoi Pre-coding Documentation:
I basically derived my core algorithm from here.

Move 1-4 from A-C.
    Move 1-3 from A-B.
        Move 1-2 from A-C.
            Move 1 from A-B.
            Move 2 from A-C.
            Move 1 from B-C.
        End 1-2 A-C.

        Move 3 from A-B.

        Move 1-2 from C-B.
            Move 1 from C-A.
            Move 2 from C-B.
            Move 1 from A-B.
        End 1-2 C-B.
    End 1-3 A-B.

    Move 4 from A-C.

    Move 1-3 from B-C.
        Move 1-2 from B-A.
            Move 1 from B-C.
            Move 2 from B-A.
            Move 1 from C-A.
        End 1-2 B-A.

        Move 3 from B-C.

        Move 1-2 from A-C.
            Move 1 from A-B.
            Move 2 from A-C.
            Move 1 from B-C.
        End 1-2 A-C.
    End 1-3 B-C.
End 1-4 A-C.

The section below is based on the algorithm I ended up with:

I created constant chars A B and C for the class.
I added exception handling inside the moveDiscs method, in addition to the exception handling method.
The moveDiscs method can infer the auxiliary from the start and goal.

I chose A to be the start and C to be the goal.

*/
