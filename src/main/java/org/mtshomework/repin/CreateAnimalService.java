package org.mtshomework.repin;

import org.mtshomework.repin.animals.*;
import org.mtshomework.repin.exceptions.InvalidAnimalBirthDateException;
import org.mtshomework.repin.exceptions.InvalidAnimalException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class CreateAnimalService {
    enum ANIMAL {
        WOLF {
            public Animal create() {
                return new Wolf();
            }
        },
        EAGLE {
            public Animal create() {
                return new Eagle();
            }
        },
        TURTLE {
            public Animal create() {
                return new Turtle();
            }
        },
        ANTELOPE {
            public Animal create() {
                return new Antelope();
            }
        };

        public Animal create() {
            return null;
        }

        private static final ANIMAL[] vals = values();

        public ANIMAL next() {
            return vals[(this.ordinal() + 1) % vals.length];
        }
    }

    private ANIMAL nextAnimal = ANIMAL.WOLF;

    Animal create() {
        Animal result = nextAnimal.create();
        nextAnimal = nextAnimal.next();
        return result;
    }

    protected static Integer DEFAULT_ANIMALS_AMOUNT = 100;

    public Map<String, List<Animal>> animals() throws InvalidAnimalBirthDateException {
        int size = 0;
        HashMap<String, List<Animal>> result = new HashMap<String, List<Animal>>();
        System.out.println("Generating animals: " + DEFAULT_ANIMALS_AMOUNT);
        while (size < DEFAULT_ANIMALS_AMOUNT) {
            Animal animal = create();
            List<Animal> list = result.computeIfAbsent(animal.getBreed(), k -> new LinkedList<>());
            list.add(animal);
            size++;
        }
        return result;
    }
}
