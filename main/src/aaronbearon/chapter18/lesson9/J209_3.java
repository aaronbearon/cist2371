package aaronbearon.chapter18.lesson9;

public class J209_3 {
    private static final char LEFT = 'A';
    private static final char MIDDLE = 'B';
    private static final char RIGHT = 'C';

    public static void main(String[] args) {
        System.out.println("Please move the Tower of Hanoi:");
        moveDiscs(4, LEFT, MIDDLE, RIGHT);
    }

    // Move all discs in minimum move count.
    public static void moveDiscs(int numDiscs, char start, char aux, char goal) {
        if (numDiscs > 1) {
            moveDiscs(numDiscs - 1, start, goal, aux);
        }

        System.out.println("Move disc " + numDiscs + " from " + start + " to " + goal + ".");
        if (numDiscs > 1) {
            moveDiscs(numDiscs - 1, aux, start, goal);
        }
    }
}

/*

Tower of Hanoi:

Pre-coding Documentation:

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

*/
