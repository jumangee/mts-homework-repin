package org.mtshomework.repin;

import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.exceptions.InvalidAnimalAgeException;
import org.mtshomework.repin.exceptions.InvalidAnimalListException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnimalsRepositoryImpl implements AnimalsRepository {
    @Override
    public Map<String, LocalDate> findLeapYearNames(Map<String, List<Animal>> animals) {
        if (animals == null) {
            throw new InvalidAnimalListException("Incorrect animals in findLeapYearNames");
        }

        Stream<Animal> animalStream = animals.values().stream().flatMap(Collection::stream);

        return animalStream
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap((a) -> a.getBreed() + " " + a.getName(), Animal::getBirthDate, (a1, a2) -> a1));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(Map<String, List<Animal>> animals, int age) throws InvalidAnimalAgeException {
        if (animals == null) {
            throw new InvalidAnimalListException("Incorrect animals in findOlderAnimal");
        }
        if (animals.isEmpty()) {
            throw new InvalidAnimalListException("Animals is empty");
        }
        if (age < 0) {
            throw new InvalidAnimalAgeException("Incorrect age in findOlderAnimal");
        }

        Map<Animal, Integer> result = animals.values().stream().flatMap(Collection::stream)
                .filter(animal -> animal.getAge() > age)
                .collect(Collectors.toMap(animal -> animal, Animal::getAge));

        if (result.isEmpty()) {
            animals.values().stream().flatMap(Collection::stream)
                    .max(Comparator.comparing(Animal::getAge))
                    .map((a) -> result.put(a, a.getAge()));
        }

        return result;
    }

    @Override
    public Map<String, List<Animal>> findDuplicate(Map<String, List<Animal>> animals) {
        if (animals == null) {
            throw new InvalidAnimalListException("Incorrect animals in findDuplicate");
        }
        Map<String, List<Animal>> result = new HashMap<>();

        animals.keySet().stream()
                .forEach((breed) -> {
                    List<Animal> animalList = animals.get(breed);

                    animalList
                        .stream()
                        .forEach(animal -> {
                            List<Animal> duplicates = animalList.stream()
                                .filter((a) -> !a.equals(animal) && a.getName().equals(animal.getName()))
                                .toList();
                            if (!duplicates.isEmpty()) {
                                result.put(breed, duplicates);
                            }
                        });
                });

        return result;
    }

    @Override
    public int findAverageAge(Map<String, List<Animal>> animals) {
        return (int)animals.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Animal::getAge)
                .average()
                .orElse(-1);
    }

    public Double findAveragePrice(Map<String, List<Animal>> animals) {
        return animals.values().stream()
                .flatMap(Collection::stream)
                .mapToDouble(Animal::getCost)
                .average()
                .orElse(0);
    }

    @Override
    public List<Animal> findOldAndExpensive(Map<String, List<Animal>> animals, int age) {
        Double avgPrice = findAveragePrice(animals);
        return animals.values().stream()
                .flatMap(Collection::stream)
                .filter(animal -> animal.getAge() > age)
                .filter(animal -> animal.getCost() > avgPrice)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .toList();
    }

    @Override
    public List<String> findMinConstAnimals(Map<String, List<Animal>> animals) {
        return animals.values().stream()
                .flatMap(Collection::stream)
                .sorted(Comparator.comparingDouble(Animal::getCost))
                .limit(3)
                .sorted((a1, a2) -> -a1.getName().compareTo(a2.getName()))
                .map(Animal::getName)
                .toList();
    }
}
