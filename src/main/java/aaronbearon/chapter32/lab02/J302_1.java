package aaronbearon.chapter32.lab02;

/**
 * Aaron Blum, CIST 2373 Java 3, Lab 2
 * Description: Multithread ping pong
 */
public class J302_1 {
    public static void main(String[] args) throws Exception {
        // PingPong is an instance of Runnable
        Runnable ping = new PingPong("ping", 10);
        Runnable pong = new PingPong("pong", 10);
        Thread pingThread = new Thread(ping);
        Thread pongThread = new Thread(pong);
        // Start the threads, and use join to pause the current thread flow.
        pingThread.start();
        pongThread.start();
        pingThread.join();
        pongThread.join();
        // System.out.println("main is exiting!");
    }
}

/**
 * PingPong prints either "ping" or "pong" a given number of times.
 */
class PingPong implements Runnable {
    private final String word;
    private final int times;

    public PingPong(String word, int times) {
        this.word = word;
        if (!(word.equals("ping") || word.equals("pong"))) {
            throw new RuntimeException("Must be either ping or pong.");
        }
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            Thread.yield();
            System.out.println(word);
        }
    }
}
