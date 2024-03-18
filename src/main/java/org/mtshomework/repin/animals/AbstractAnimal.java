package org.mtshomework.repin.animals;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractAnimal implements Animal {
    protected String breed; // порода
    protected String name; // имя
    protected Double cost; // цена в магазине
    protected String character; // характер

    protected LocalDate birthDate; // дата рождения

    protected static List<String> NAMES = new ArrayList<>(List.of(
        "Baddi","Samson","Lavrik","Emil'","Spartak","Cezar'","Villi","Chara","Betti","Laura","Dzhena","Gledis","Dara","Sherli","Eloiza","Zira","Mila","Satoru","Takeshi","Akira","Dzhoko","Uki","Rimbo","Cukiko","Perak","Purvanto","Toshi","Solekh","Netro","Raden","Vasabisous","Hokkajdo","Akiro","Seok","Chol","Fen","Ardzhuna","Dzhung","Thaj","Shuan'reki","Hotaru","Kirana","Rara","Ponko","Amajya","Cukiya","Inda","Satiya","Koge","Secuko","Macuda","Tovada","Nuri","Harta","Mamiko","Chiaki","Darma","Tempura","Atmo","Dzhaya","Cukiko","Mani","Thu","Ayuta","Viksi","Vivi","Chupa","Busya","Uma","Kesh'yu","Smurfi","Kaffi","Lyucha","Artemon","Bethoven","Marli","Majlo","Sharik","Reks","Hatiko","Pappi","Mont","Pongo","Barbos"
    ));

    protected AbstractAnimal(String breed, String character, Double cost) {
        this.breed = breed;
        this.character = character;
        this.cost = cost;

        this.name = AbstractAnimal.NAMES.get( Math.max(0, (int) Math.round(Math.random() * AbstractAnimal.NAMES.size()-1)) );
        this.birthDate = LocalDate.ofEpochDay( LocalDate.now().toEpochDay() - Math.round(Math.random() * 3650) + 1 );

    }

    public int getAge() {
        return Math.round( (float) (LocalDate.now().toEpochDay() - getBirthDate().toEpochDay()) / 365);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(character, that.character) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDate);
    }
}
