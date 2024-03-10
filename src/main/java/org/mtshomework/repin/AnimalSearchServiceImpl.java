package org.mtshomework.repin;

import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.exceptions.InvalidAnimalBirthDateException;
import org.mtshomework.repin.exceptions.InvalidAnimalException;

import java.time.LocalDate;

public class AnimalSearchServiceImpl implements AnimalSearchService {

    @Override
    public void checkLeapYearAnimal(Animal animal) throws InvalidAnimalException, InvalidAnimalBirthDateException {
        if (animal == null) {
            throw new InvalidAnimalException("на вход пришло некорректный объект животного " + LocalDate.now());
        }
        if (animal.getBirthDate() == null) {
            throw new InvalidAnimalBirthDateException("у животного "+animal.getName()+" не указана дата его рождения");
        }

        System.out.println(animal.getName() +
                (animal.getBirthDate().isLeapYear() ?
                        " БЫЛ рожден в високосный год"
                        :
                        " не был рожден в високосный год")
        );
    }
}
