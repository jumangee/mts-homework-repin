package org.mtshomework.repin.animals;

public class Turtle extends HerbivorousAnimal{
    Turtle() {
        this.breed = "Черепаха";
        this.character = "Морские";
        this.cost = 200.00;
        this.generateName();
    }

    public static Animal factory() {
        return new Turtle();
    }
}
