package aaronbearon.chapter13.interview;

/**
 * Aaron Blum, CIST 2372 Java 2, Interview
 * Description: Create the concrete Dog and Cat classes
 * from the abstract Animal class and implement them.
 */
public class J204_1 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();

        dog.makeSound();
        dog.sleep();
        cat.makeSound();
        cat.sleep();
    }
}

abstract class Animal {
    // Implemented by concrete subclasses
    public abstract void makeSound();

    // Implementation is the same for both.
    public void sleep() {
        System.out.println("Zzzz...");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

/*

           +---------------+
           |  (Abstract)   |
           |    Animal     |
           +---------------+
           |  (Abstract)   |
           |  makeSound()  |
           |  (Concrete)   |
           |    sleep()    |
           +---------------+
                   ^
         _________/|\_________
        /                     \
+---------------+     +---------------+
|  (Concrete)   |     |  (Concrete)   |
|      Dog      |     |      Cat      |
+---------------+     +---------------+
|  (Concrete)   |     |  (Concrete)   |
|  makeSound()  |     |  makeSound()  |
+---------------+     +---------------+

The sleep method inherited by the concrete class prints "Zzzz..."
    because it's defined in the abstract Animal class without being overridden.

Regarding the makeSound() method, dogs "Woof!" and cats "Meow!".

*/
