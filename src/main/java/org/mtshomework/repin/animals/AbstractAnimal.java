package org.mtshomework.repin.animals;

public abstract class AbstractAnimal implements Animal {
    protected String breed; // порода
    protected String name; // имя
    protected Double cost; // цена в магазине
    protected String character; // характер

    private long randomNameID() {
        return Math.round(Math.random() * 10000) + 1;
    }

    protected void generateName() {
        this.name = this.breed + "#" + this.randomNameID();
    }
}
