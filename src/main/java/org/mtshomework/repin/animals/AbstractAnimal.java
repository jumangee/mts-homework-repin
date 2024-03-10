package org.mtshomework.repin.animals;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;

public abstract class AbstractAnimal implements Animal {
    protected String breed; // порода
    protected String name; // имя
    protected Double cost; // цена в магазине
    protected String character; // характер

    protected LocalDate birthDate; // дата рождения

    private long randomNameID() {
        return Math.round(Math.random() * 10000) + 1;
    }

    protected void birth() {
        this.name = this.breed + "#" + this.randomNameID();
        this.birthDate = LocalDate.ofEpochDay( Math.round(Math.random() * 10000000) + 1 );
    }
}
