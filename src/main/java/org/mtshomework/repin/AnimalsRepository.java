package org.mtshomework.repin;

import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.exceptions.InvalidAnimalAgeException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {
    public Map<String, LocalDate> findLeapYearNames(Map<String, List<Animal>> animals);
    public Map<Animal, Integer> findOlderAnimal(Map<String, List<Animal>> animals, int age) throws InvalidAnimalAgeException;
    public Map<String, Integer> findDuplicate(Map<String, List<Animal>> animals);
}
