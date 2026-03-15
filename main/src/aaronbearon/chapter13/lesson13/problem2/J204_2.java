package aaronbearon.chapter13.lesson13.problem2;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 4 Part 2
 * Description: Create the Car and Bike classes to implement the StartnStop interface.
 * The classes should override the interface methods.
 */
public class J204_2 {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
        car.stop();
        Bike bike = new Bike();
        bike.start();
        bike.stop();
    }
}

// Methods to be overridden by concrete classes
interface StartnStop {
    void start();

    void stop();
}

// start() and stop() Car implementations
class Car implements StartnStop {
    @Override
    public void start() {
        System.out.println("Car started...");
    }

    @Override
    public void stop() {
        System.out.println("Car stopped.");
    }
}

// start() and stop() Bike implementations
class Bike implements StartnStop {
    @Override
    public void start() {
        System.out.println("Bike started...");
    }

    @Override
    public void stop() {
        System.out.println("Bike stopped.");
    }
}

/*
+---------------+
| <<interface>> |
|  StartnStop   |
+---------------+
|    start()    |
|    stop()     |
+---------------+
        ^
        |\____________________
        |                     \
+---------------+     +---------------+
|  (Concrete)   |     |  (Concrete)   |
|      Car      |     |     Bike      |
+---------------+     +---------------+
|    start()    |     |    start()    |
|    stop()     |     |    stop()     |
+---------------+     +---------------+

Put two @Override methods in each concrete class to print a specific String.
The output should be:

Car started...
Car stopped.
Bike started...
Bike stopped.

Instantiate each concrete class in the main method and call their methods.

*/
