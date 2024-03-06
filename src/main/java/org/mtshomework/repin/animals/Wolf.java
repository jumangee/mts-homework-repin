package org.mtshomework.repin.animals;

public class Wolf extends PredatorAnimal {
    Wolf() {
        this.breed = "Волк";
        this.character = "Лесные";
        this.cost = 1500.00;
        this.generateName();
    }

    public static Animal factory() {
        return new Wolf();
    }
}
