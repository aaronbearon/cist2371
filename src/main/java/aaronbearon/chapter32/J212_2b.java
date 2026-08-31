package aaronbearon.chapter32;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Aaron Blum, CIST 2373 Java 3, Interview 2 part 1.
 * Description: Five chefs cook ramen and take turns cooking on 4 burners.
 */
public class J212_2b extends Application {
    Label lblRamens = new Label("Ramen: 20");
    Label lblStove = new Label("____");
    Circle circle1 = new Circle(100.0, 100.0, 50.0);
    Circle circle2 = new Circle(300.0, 100.0, 50.0);
    Circle circle3 = new Circle(100.0, 300.0, 50.0);
    Circle circle4 = new Circle(300.0, 300.0, 50.0);
    private final Circle[] circles = {circle1, circle2, circle3, circle4};

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        for (Circle circle : circles) {
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
        }
        lblRamens.setTranslateX(180.0);
        lblRamens.setTranslateY(10.0);
        lblStove.setTranslateX(190.0);
        lblStove.setTranslateY(200.0);
        Pane outerPane = new Pane();
        outerPane.getChildren().addAll(lblStove, lblRamens);
        for (Circle circle : circles) {
            outerPane.getChildren().add(circle);
        }
        Scene scene = new Scene(outerPane, 400, 400);
        primaryStage.setTitle("J212_2b"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        startKitchen(20);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void startKitchen(int ramens) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                runKitchen(ramens, (String stove) -> Platform.runLater(() -> {
                            lblStove.setText(stove);
                            for (int i = 0; i < lblStove.getText().length(); i++) {
                                if (lblStove.getText().charAt(i) == '_') {
                                    circles[i].setFill(Color.WHITE);
                                } else {
                                    circles[i].setFill(Color.RED);
                                }
                            }
                        }),
                        (Integer ramens) -> Platform.runLater(() -> lblRamens.setText("Ramen: " + ramens.toString())));
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    public void runKitchen(int ramens, Consumer<String> updateStove, Consumer<Integer> updateRamens) throws InterruptedException {
        Kitchen kitchen = new Kitchen(ramens, updateRamens);
        Stove stove = new Stove(4, updateStove);
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

    /**
     * The kitchen holds orders to be taken.
     */
    static class Kitchen {
        private int ramens;
        private final Consumer<Integer> updateRamens;

        public Kitchen(int ramen, Consumer<Integer> updateRamens) {
            this.ramens = ramen;
            this.updateRamens = updateRamens;
        }

        /**
         * Try to take an order. Returns true if an order is taken or false if there are no more orders.
         */
        public boolean take(char chef) {
            boolean result;
            int ramensAfter;
            synchronized (this) {
                if (ramens > 0) {
                    ramens--;
                    result = true;
                } else {
                    result = false;
                }
                ramensAfter = ramens;
            }
            System.out.printf("%c: takes 1, %d left.%n", chef, ramensAfter);
            updateRamens.accept(ramensAfter);
            return result;
        }
    }

    /**
     * A chef takes orders from the kitchen and cooks them using the stove.
     */
    static class Chef implements Runnable {
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

    /**
     * Allows chefs to take turns using synchronized burners.
     */
    static class Stove {
        private final char[] burners;
        private final Consumer<String> updateStove;

        public Stove(int burnerCount, Consumer<String> updateStove) {
            burners = "_".repeat(burnerCount).toCharArray();
            this.updateStove = updateStove;
        }

        /**
         * Acquire a free burner exclusively. Blocks until a burner is free.
         * You must release burner after.
         */
        public int acquireBurner(char chef) {
            int result;
            String stoveAfter;
            synchronized (this) {
                outer:
                while (true) {
                    for (int burnerId = 0; burnerId < burners.length; burnerId++) {
                        if (burners[burnerId] == '_') {
                            burners[burnerId] = chef;
                            result = burnerId;
                            stoveAfter = String.valueOf(burners);
                            break outer;
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
            System.out.printf("    %c: Burner %d is ON.               %s%n", chef, result + 1, stoveAfter);
            updateStove.accept(stoveAfter);
            return result;
        }

        /**
         * Release a previously acquired burner.
         */
        public void releaseBurner(char chef, int burnerId) {
            String stoveAfter;
            synchronized (this) {
                if (burners[burnerId] != chef) {
                    throw new IllegalStateException("wrong burner state");
                }
                burners[burnerId] = '_';
                stoveAfter = String.valueOf(burners);
                // Let other cooks know a burner is free.
                this.notifyAll();
            }
            System.out.printf("        %c: done, Burner %d is OFF.    %s%n", chef, burnerId + 1, stoveAfter);
            updateStove.accept(stoveAfter);
        }
    }
}

/*

The Stove class contains two synchronized methods for burner access.
The Kitchen class has a synchronized method for taking a ramen order.
Multiple chefs (or instances of Runnable) cook in the kitchen using the stove.

*/
