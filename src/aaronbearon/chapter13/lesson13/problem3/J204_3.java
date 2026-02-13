package aaronbearon.chapter13.lesson13.problem3;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 4 Part 3
 * Description: Create the Bird, Dog, and Cat classes.
 * They should implement the CanFly and CanTalk interfaces as shown in the diagram at the bottom.
 */
public class J204_3 {
    public static void main(String[] args) {
        Bird bird = new Bird();
        bird.talk();
        bird.fly();
        Dog dog = new Dog();
        dog.talk();
        Cat cat = new Cat();
        cat.talk();
    }
}

// Implemented by Bird concrete class
interface CanFly {
    void fly();
}

// Implemented by all 3 concrete classes
interface CanTalk {
    void talk();
}

class Bird implements CanTalk, CanFly {
    @Override
    public void talk() {
        System.out.println("Chirp chirp!");
    }

    @Override
    public void fly() {
        System.out.println("Bird is flying");
    }
}

class Dog implements CanTalk {
    @Override
    public void talk() {
        System.out.println("Woof!");
    }
}

class Cat implements CanTalk {
    @Override
    public void talk() {
        System.out.println("Meow!");
    }
}

/*

+---------------+    +---------------+
| <<interface>> |    | <<interface>> |
|    CanFly     |    |    CanTalk    |
+---------------+    +---------------+
|     fly()     |    |    talk()     |
|               |    |               |
+---------------+    +---------------+
        ^                    ^
        | __________________/|\___________________
        |/                   |                    \
+---------------+    +---------------+    +---------------+
|  (Concrete)   |    |  (Concrete)   |    |  (Concrete)   |
|     Bird      |    |      Dog      |    |      Cat      |
+---------------+    +---------------+    +---------------+
|    talk()     |    |    talk()     |    |    talk()     |
|     fly()     |    |               |    |               |
+---------------+    +---------------+    +---------------+

In Dog and Cat classes, @Override talk()
Bird class should also @Override talk(), along with fly()

bird.talk() --- "Chirp chirp!"
bird.fly()  --- "Bird is flying"
dog.talk()  --- "Woof!"
cat.talk()  --- "Meow!"

(Don't print the quotation marks)

*/
