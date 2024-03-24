package org.mtshomework.repin;

import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.exceptions.InvalidAnimalAgeException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {
    public Map<String, LocalDate> findLeapYearNames(Map<String, List<Animal>> animals);
    public Map<Animal, Integer> findOlderAnimal(Map<String, List<Animal>> animals, int age) throws InvalidAnimalAgeException;
    public Map<String, List<Animal>> findDuplicate(Map<String, List<Animal>> animals);
    public  int findAverageAge(Map<String, List<Animal>> animals);
    public List<Animal> findOldAndExpensive(Map<String, List<Animal>> animals, int age);
    public List<String> findMinConstAnimals(Map<String, List<Animal>> animals);
}
