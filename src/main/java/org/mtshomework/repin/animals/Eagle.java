package org.mtshomework.repin.animals;

public class Eagle extends PredatorAnimal {
    Eagle() {
        this.breed = "Орёл";
        this.character = "Птицы";
        this.cost = 2500.00;
        this.generateName();
    }

    public static Animal factory() {
        return new Eagle();
    }
}
