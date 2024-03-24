package org.mtshomework.repin;

import org.mtshomework.repin.animals.*;
import org.mtshomework.repin.exceptions.InvalidAnimalBirthDateException;
import org.mtshomework.repin.exceptions.InvalidAnimalException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CreateAnimalServiceImpl extends CreateAnimalService {

    public Map<String, List<Animal>> animals(Integer amount) throws InvalidAnimalBirthDateException {
        HashMap<String, List<Animal>> result = new HashMap<String, List<Animal>>();
        for (int i = 0; i < amount; i++) {
            Animal animal = create();
            List<Animal> list = result.get(animal.getBreed());
            if (list == null) {
                result.put(animal.getBreed(), new LinkedList<>(List.of(animal)));
            } else {
                list.add(animal);
            }
        }
        return result;
    }
}
