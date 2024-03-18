package org.mtshomework.repin.animals;

import java.time.LocalDate;

public class PredatorAnimal extends AbstractAnimal {
    public PredatorAnimal(String breed, String character, Double cost) {
        super(breed, character, cost);
    }
    public String getBreed() {
        return "Хищник " + this.breed;
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

    @Override
    public LocalDate getBirthDate() {return this.birthDate; }
}
