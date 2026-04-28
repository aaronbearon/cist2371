package aaronbearon.Interview_4_20;

public class Main {
    public static void main(String[] args) {
        Dog<String, Integer> dog1 = new Dog<>();
        dog1.setName("James");
        dog1.setAge(5);
        dog1.introduce();
        dog1.printType(dog1);
        System.out.println();

        Dog<String, Integer> dog2 = new Poodle<>();
        dog2.setName("Thomas");
        dog2.setAge(6);
        dog2.introduce();
        dog2.printType(dog2);
        System.out.println();

        Poodle<String, Integer> dog3 = new Poodle<>();
        dog3.setName("Robert");
        dog3.setAge(7);
        dog3.introduce();
        dog3.printType(dog3);
    }
}

class Dog<E, T> {
    private E name;
    private T age;

    public void setName(E name) {
        this.name = name;
    }

    public void setAge(T age) {
        this.age = age;
    }

    public void introduce() {
        System.out.println("I am " + name + ", and " + age + " years old.");
    }

    public void printType(Dog<E, T> dog) {
        if (this == dog) {
            System.out.println("I'm a dog.");
            printConcreteType();
        }
    }

    private void printConcreteType() {
        System.out.println("I'm most concretely a dog.");
    }
}

class Poodle<E, T> extends Dog<E, T> {
    public void printType(Dog<E, T> dog) {
        if (this == dog) {
            System.out.println("I'm a dog.");
            ((Poodle<E, T>) dog).printType((Poodle<E, T>) dog);
        }
    }

    public void printType(Poodle<E, T> poodle) {
        if (this == poodle) {
            System.out.println("I'm a poodle.");
            printConcreteType();
        }
    }

    private void printConcreteType() {
        System.out.println("I'm most concretely a poodle.");
    }
}
