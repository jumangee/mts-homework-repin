package org.mtshomework.repin;

import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.exceptions.InvalidAnimalAgeException;
import org.mtshomework.repin.exceptions.InvalidAnimalListException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnimalsRepositoryImpl implements AnimalsRepository {
    static class AnimalIterator {
        protected Iterator<String> listIterator;
        protected Iterator<Animal> itemIterator;
        protected Map<String, List<Animal>> animals;

        protected Animal animal;

        AnimalIterator(Map<String, List<Animal>> animals) {
            if (animals == null) {
                throw new InvalidAnimalListException("Incorrect animals in AnimalIterator");
            }
            this.animals = animals;
            nextAnimal();
        }

        Animal animal() {
            Animal result = this.animal;
            this.nextAnimal();
            return result;
        }

        boolean hasNext() {
            return animal != null;
        }

        protected void nextAnimal() {
            if (itemIterator != null) {
                if (itemIterator.hasNext()) {
                    this.animal = itemIterator.next();
                    return;
                }
            }

            if (listIterator == null) {
                listIterator = animals.keySet().iterator();
            }
            if (!listIterator.hasNext()) {
                this.animal = null;
                return;
            }
            List<Animal> list = animals.get(listIterator.next());
            itemIterator = list.iterator();
            this.animal = itemIterator.hasNext() ? itemIterator.next() : null;
        }
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames(Map<String, List<Animal>> animals) {
        if (animals == null) {
            throw new InvalidAnimalListException("Incorrect animals in findLeapYearNames");
        }
        HashMap<String, LocalDate> result = new HashMap<>();
        AnimalIterator iterator = new AnimalIterator(animals);
        while (iterator.hasNext()) {
            Animal animal = iterator.animal();
            if (animal.getBirthDate().isLeapYear()) {
                result.put(animal.getName(), animal.getBirthDate());
            }
        }
        return result;
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
        HashMap<Animal, Integer> result = new HashMap<>();
        AnimalIterator iterator = new AnimalIterator(animals);
        Animal oldest = null;

        while (iterator.hasNext()) {
            Animal animal = iterator.animal();
            if (oldest != null) {
                if (animal.getAge() > oldest.getAge()) {
                    oldest = animal;
                }
            } else {
                oldest = animal;
            }
            int animalAge = animal.getAge();
            if (animalAge > age ) {
                result.put(animal, animalAge);
            }
        }

        if (result.isEmpty() && oldest != null) {
            result.put(oldest, oldest.getAge());
        }

        return result;
    }

    @Override
    public Map<String, Integer> findDuplicate(Map<String, List<Animal>> animals) {
        if (animals == null) {
            throw new InvalidAnimalListException("Incorrect animals in findDuplicate");
        }
        HashMap<String, Integer> result = new HashMap<>();
        Iterator<String> iterator = animals.keySet().iterator();

        while (iterator.hasNext()) {
            List<Animal> list = animals.get(iterator.next());
            Iterator<Animal> animalIterator = list.iterator();

            while (animalIterator.hasNext()) {
                Animal animal = animalIterator.next();
                int duplicates = 0;

                Iterator<Animal> checkIterator = list.iterator();
                while (checkIterator.hasNext()) {
                    Animal checkAnimal = checkIterator.next();
                    if (checkAnimal.equals(animal)) continue;

                    if (checkAnimal.getName().equals( animal.getName() )) {
                        duplicates++;
                    }
                }

                if (duplicates > 0) {
                    result.put(animal.getBreed(), duplicates);
                }
            }
        }

        return result;
    }
}
