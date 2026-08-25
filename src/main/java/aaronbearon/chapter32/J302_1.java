package aaronbearon.chapter32;

public class J302_1 {
    public static void main(String[] args) throws Exception {
        Runnable ping = new PingPong("ping", 10);
        Runnable pong = new PingPong("pong", 10);
        Thread pingThread = new Thread(ping);
        Thread pongThread = new Thread(pong);
        pingThread.start();
        pongThread.start();
        pingThread.join();
        pongThread.join();
        System.out.println("main is exiting!");
    }
}

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
