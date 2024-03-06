package org.mtshomework.repin.animals;

public class Antelope extends HerbivorousAnimal {
    Antelope() {
        this.breed = "Антилопа";
        this.character = "Лесные";
        this.cost = 1000.00;
        this.generateName();
    }

    public static Animal factory() {
        return new Antelope();
    }
}
