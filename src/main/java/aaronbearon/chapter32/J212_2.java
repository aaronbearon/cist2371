package aaronbearon.chapter32;

import java.util.ArrayList;

public class J212_2 {
    public static void main(String[] args) throws InterruptedException {
        Kitchen kitchen = new Kitchen(20);
        Stove stove = new Stove(4);
        ArrayList<Thread> threads = new ArrayList<>();
        for (char chef = 'A'; chef <= 'E'; chef++) {
            Thread thread = new Thread(new Chef(chef, kitchen, stove));
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("ALL DONE!");
    }
}

class Kitchen {
    private int ramen;

    public Kitchen(int ramen) {
        this.ramen = ramen;
    }

    public synchronized boolean take(char chef) {
        if (ramen > 0) {
            ramen--;
            System.out.printf("%c: takes 1, %d left.%n", chef, ramen);
            return true;
        } else {
            return false;
        }
    }
}

class Chef implements Runnable {
    private final char name;
    private final Kitchen kitchen;
    private final Stove stove;

    public Chef(char name, Kitchen kitchen, Stove stove) {
        this.name = name;
        this.kitchen = kitchen;
        this.stove = stove;
    }

    @Override
    public void run() {
        while (kitchen.take(name)) {
            // Prep
            try {
                Thread.sleep((int) (Math.random() * 1000) + 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int burnerId = stove.acquireBurner(name);

            // Cook
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                stove.releaseBurner(name, burnerId);
            }
        }
    }
}

class Stove {
    private final char[] burners;

    public Stove(int burnerCount) {
        burners = "_".repeat(burnerCount).toCharArray();
    }

    public synchronized int acquireBurner(char chef) {
        while (true) {
            for (int burnerId = 0; burnerId < burners.length; burnerId++) {
                if (burners[burnerId] == '_') {
                    burners[burnerId] = chef;
                    System.out.printf("    %c: Burner %d is ON.               %s%n", chef, burnerId + 1, String.valueOf(burners));
                    return burnerId;
                }
            }
            // No burners available, wait for one to be open.
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void releaseBurner(char chef, int burnerId) {
        if (burners[burnerId] != chef) {
            throw new IllegalStateException("wrong burner state");
        }
        burners[burnerId] = '_';
        System.out.printf("        %c: done, Burner %d is OFF.    %s%n", chef, burnerId + 1, String.valueOf(burners));
        // Let other cooks know a burner is free.
        this.notifyAll();
    }
}
