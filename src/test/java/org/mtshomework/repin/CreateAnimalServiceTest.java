package org.mtshomework.repin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.exceptions.InvalidAnimalBirthDateException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreateAnimalServiceTest {

    @Test
    @DisplayName("Create animal")
    void create() {
        CreateAnimalService svc = new CreateAnimalServiceImpl();
        assertInstanceOf(Animal.class, svc.create());
    }

    @Test
    @DisplayName("Default animals list generator")
    void animals() {
        CreateAnimalService.DEFAULT_ANIMALS_AMOUNT = 1;
        CreateAnimalService svc = new CreateAnimalServiceImpl();

        Map<String, List<Animal>> animals;

        try {
             animals = svc.animals();
        } catch (InvalidAnimalBirthDateException e) {
            throw new RuntimeException("Invalid birthdate in CreateAnimalServiceTest");
        }

        assertNotEquals(null, svc);
        assertEquals(1, animals.size());
        assertNotNull(animals.get( animals.keySet().toArray()[0] ));
        assertEquals(1, animals.get( animals.keySet().toArray()[0] ).size() );
    }
}