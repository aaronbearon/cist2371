package aaronbearon.chapter32.Interview02;

/**
 * Aaron Blum, CIST 2373 Java 3, Interview 2 part 1.
 * Description: Efficient car assembler.
 */
public class J212_1 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> manufacture("A", "interior"));
        Thread threadB = new Thread(() -> manufacture("B", "exterior"));
        Thread threadC = new Thread(() -> manufacture("C", "assembling"));
        threadA.start();
        threadB.start();
        // Tested formulation below, but join() is better.
        // while (threadA.isAlive() || threadB.isAlive()) {
        //   Thread.yield();
        // }
        threadA.join();
        threadB.join();
        System.out.println("Assembly ready!");
        threadC.start();
        threadC.join();
        System.out.println("All done!");
    }

    /**
     * Multiple workers can do different jobs simultaneously.
     */
    public static void manufacture(String entity, String job) {
        System.out.println(entity + " starts " + job);
        for (int i = 1, j = 5; i <= j; i++) {
            System.out.println("    " + job + " status: " + i + "/" + j);
            if (i == 3 && !entity.equals("C")) {
                System.out.println(entity + " taking a break...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println(entity + ": " + job + " done!");
    }
}

/*

The manufacture method eliminates the need to create a class for multithreading.

*/
