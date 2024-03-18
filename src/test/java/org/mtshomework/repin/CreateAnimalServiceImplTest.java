package org.mtshomework.repin;

import org.junit.jupiter.api.Test;
import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.exceptions.InvalidAnimalBirthDateException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreateAnimalServiceImplTest {

    CreateAnimalServiceImpl svc = new CreateAnimalServiceImpl();

    @Test
    void animals() {
        Map<String, List<Animal>> animals;

        try {
            animals = svc.animals(1);
        } catch (InvalidAnimalBirthDateException e) {
            throw new RuntimeException("Invalid birthdate in CreateAnimalServiceTest");
        }

        assertNotEquals(null, svc);
        assertEquals(1, animals.size());
        assertNotNull(animals.get( animals.keySet().toArray()[0] ));
        assertEquals(1, animals.get( animals.keySet().toArray()[0] ).size() );
    }
}