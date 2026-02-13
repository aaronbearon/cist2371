package aaronbearon.chapter13.lesson13.problem4;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 4 Part 4
 * Description: Example of a complex class hierarchy of creatures,
 * with interfaces and abstract classes.
 */
public class J204_4 {
    public static void main(String[] args) {
        Soldier soldier = new Soldier("G.I.Jane", 31, "sergeant");
        Turtle turtle = new Turtle("Nimo", 70, "sea");
        Pigeon pigeon = new Pigeon("Kiwi", 2);

        // Matches sample output
        System.out.println(soldier.getName() + ": " + soldier.talk());
        System.out.println(soldier.getName() + ": eating...");
        System.out.println(soldier.getName() + ": swim " + soldier.swim());
        System.out.println(turtle.getName() + ": eating " + turtle.eat());
        System.out.println(turtle.getName() + ": swim " + turtle.swim());
        System.out.println(pigeon.getName() + ": " + pigeon.sound());
        System.out.println(pigeon.getName() + ": " + pigeon.attack());

        // More output to call more methods
        System.out.println();
        System.out.println(soldier.getName() + ": " + soldier.attack());
        System.out.println(soldier.getName() + ": " + soldier.shoot());
        System.out.println(turtle.getName() + ": " + turtle.attack());
        System.out.println(turtle.getName() + ": " + turtle.sound());
        System.out.println(turtle.getName() + ": " + turtle.hide());
        System.out.println(pigeon.getName() + ": " + pigeon.eat());
        System.out.println(pigeon.getName() + ": " + pigeon.fly());
        System.out.println(pigeon.getName() + ": " + pigeon.nest());
    }
}

// These are the 9 interfaces
interface CanAttack {
    String attack();
}

interface CanEat {
    String eat();
}

interface CanShoot {
    String shoot();
}

interface CanSwim {
    String swim();
}

interface CanFly {
    String fly();
}

interface MakeSound {
    String sound();
}

interface CanTalk {
    String talk();
}

interface CanHide {
    String hide();
}

interface CanNest {
    String nest();
}

// These fields are protected and methods are public.
// They are for all creatures including animals and humans.
abstract class Creature implements CanAttack, CanEat {
    protected String name;
    protected int age;
    protected String species;

    public Creature(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }

    public final String getName() {
        return name;
    }

    public final int getAge() {
        return age;
    }

    public final String getSpecies() {
        return species;
    }
}

// Jobs are specific to all humans
abstract class Human extends Creature implements CanTalk {
    protected String job;

    public Human(String name, int age, String job) {
        // All humans are of the species "Human being"
        super(name, age, "Human being");
        this.job = job;
    }

    public final String getJob() {
        return job;
    }
}

// Habitats are specific to all animals
abstract class Animal extends Creature implements MakeSound {
    protected String habitat;

    public Animal(String name, int age, String habitat, String species) {
        super(name, age, species);
        this.habitat = habitat;
    }

    public final String getHabitat() {
        return habitat;
    }
}

// Ranks are specific to all soldiers
class Soldier extends Human implements CanShoot, CanSwim {
    protected String rank;

    public Soldier(String name, int age, String rank) {
        super(name, age, "soldier");
        this.rank = rank;
    }

    @Override
    public String attack() {
        return "like a bee";
    }

    @Override
    public String eat() {
        return "everything...";
    }

    @Override
    public String shoot() {
        return "shooting...";
    }

    @Override
    public String swim() {
        return "over 5 km";
    }

    @Override
    public String talk() {
        return "talking...";
    }

    public final String getRank() {
        return rank;
    }
}

// Species is always Turtle
// Habitat is not always sea
// A SeaTurtle class would exist to extend Turtle
class Turtle extends Animal implements CanSwim, CanHide {
    public Turtle(String name, int age, String habitat) {
        super(name, age, habitat, "Turtle");
    }

    @Override
    public String attack() {
        return "biting...";
    }

    @Override
    public String eat() {
        return "shrimp...";
    }

    @Override
    public String swim() {
        return "over 100,000 km";
    }

    @Override
    public String sound() {
        return "ninja ninja";
    }

    @Override
    public String hide() {
        return "hiding...";
    }
}

// Species is always Pigeon
class Pigeon extends Animal implements CanFly, CanNest {
    public Pigeon(String name, int age) {
        super(name, age, "land", "Pigeon");
    }

    @Override
    public String attack() {
        return "pecking...";
    }

    @Override
    public String eat() {
        return "worm...";
    }

    @Override
    public String fly() {
        return "flying...";
    }

    @Override
    public String sound() {
        return "goo goo";
    }

    @Override
    public String nest() {
        return "nesting...";
    }
}

/*

Creature (Abstract)
-----------------------------
#name: String
#age: int
#species: String
-----------------------------
+Creature(name: String, age: int, species: String)
+getName(): String
+getAge(): int
+getSpecies(): String
-----------------------------

Attributes: Names, ages, and species apply to all creatures.
Methods: All creatures can attack() and eat().

Interfaces:
All creatures implement CanAttack and CanEat.



Human (Abstract --> Creature)
-----------------------------
#job: String
-----------------------------
+Human(name: String, age: int, job: String)
+getJob(): String
-----------------------------

Attributes: Jobs pertain exclusively to humans
Methods: Not all creatures might talk(),
    so only Can_Talk is explicitly implemented by the Human class.

Interfaces:
Both humans and animals have the interface CanAttack,
    allowing them to have this ability without requiring inheritance.
Adding additional classes like Monster can easily implement this interface.



Animal (Abstract --> Creature)
-----------------------------
#habitat: String
-----------------------------
+Animal(name: String, age: int, habitat: String, species: String)
+getHabitat(): String
-----------------------------

Attributes: Habitats pertain exclusively to animals
Methods: Not all creatures might sound(),
    so only MakeSound is explicitly implemented by the Human class.

Interfaces:
Both animals and humans have the interface CanEat,
    allowing them to have this ability without requiring inheritance.
Adding additional classes like Monster can easily implement this interface.



Soldier (Concrete --> Human)
----------------------------
#rank: String
----------------------------
+Soldier() name: String, age: int, rank: String
+attack(): String (Overriding from superclass)
+eat(): String (Overriding from superclass)
+shoot(): String (Overriding from superclass)
+swim(): String (Overriding from superclass)
+talk(): String (Overriding from superclass)
+getRank(): String
----------------------------

Attributes: Ranks pertain exclusively to soldiers
Methods: Not all humans might shoot() or swim(),
    so only CanShoot and CanSwim are explicitly implemented by the Soldier class.

Interfaces:
Both soldiers and farmers have the interface Can_Talk,
    allowing them to have this ability without requiring inheritance.
Adding additional classes like Doctor can easily implement this interface.



Turtle (Concrete --> Animal)
----------------------------
+Turtle() name: String, age: int
+attack(): String (Overriding from superclass)
+eat(): String (Overriding from superclass)
+swim(): String (Overriding from superclass)
+sound(): String (Overriding from superclass)
+hide(): String (Overriding from superclass)
----------------------------

Attributes: No exclusive attributes for turtles
Methods: Not all animals might swim() or hide(),
    so only CanSwim and CanHide are explicitly implemented by the Turtle class.

Interfaces:
Both turtles and pigeons have the interface MakeSound,
    allowing them to have this ability without requiring inheritance.
Adding additional classes like Dog can easily implement this interface.



Pigeon (Concrete --> Animal)
----------------------------
+Pigeon() name: String, age: int, name habitat
+attack(): String (Overriding from superclass)
+eat(): String (Overriding from superclass)
+fly(): String (Overriding from superclass)
+sound(): String (Overriding from superclass)
+nest(): String (Overriding from superclass)
----------------------------

Attributes: No exclusive attributes for pigeons
Methods: Not all animals might fly() or nest(),
    so only Can_Fly and CanNest are explicitly implemented by the Pigeon class.

Interfaces:
Both pigeons and turtles have the interface MakeSound,
    allowing them to have this ability without requiring inheritance.
Adding additional classes like Cat can easily implement this interface.



Additional Notes: Main method has strings inserted into the println statements to match the sample output.
There are more println calls in the main method to call more class methods because
    the sample output only shows the top part as indicate by the dots at the bottom.

*/
