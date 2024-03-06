package org.mtshomework.repin.animals;

public class HerbivorousAnimal extends AbstractAnimal {
    @Override
    public String getBreed() {
        return "Травоядное " + this.breed;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getCost() {
        return this.cost;
    }

    @Override
    public String getCharacter() {
        return this.character;
    }
}
