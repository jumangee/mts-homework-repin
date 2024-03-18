package org.mtshomework.repin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.animals.Turtle;
import org.mtshomework.repin.animals.Wolf;
import org.mtshomework.repin.exceptions.InvalidAnimalAgeException;
import org.mtshomework.repin.exceptions.InvalidAnimalListException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalsRepositoryImplTest {

    @Test
    @DisplayName("findLeapYearNames: animals list not null")
    void findLeapYearNames_AnimalsArg() {
        AnimalsRepositoryImpl repository = new AnimalsRepositoryImpl();
        assertThrows(InvalidAnimalListException.class, () -> repository.findLeapYearNames(null));
    }

    @Test
    @DisplayName("findOlderAnimal: invalid age")
    void findLeapYearNames_AgeArg() {
        CreateAnimalServiceImpl svc = new CreateAnimalServiceImpl();
        AnimalsRepositoryImpl repository = new AnimalsRepositoryImpl();
        assertThrows(InvalidAnimalAgeException.class, () -> repository.findOlderAnimal(svc.animals(1), -1));
    }

    @Test
    @DisplayName("findOlderAnimal: animals list not null")
    void findOlderAnimal_AnimalsArg() {
        AnimalsRepositoryImpl repository = new AnimalsRepositoryImpl();
        assertThrows(InvalidAnimalListException.class, () -> repository.findLeapYearNames(null));
    }

    @Mock
    Turtle turtle;

    @Test
    @DisplayName("findOlderAnimal: age detection")
    void findLeapYearNames_result() throws InvalidAnimalAgeException {
        AnimalsRepositoryImpl repository = new AnimalsRepositoryImpl();
        Map<String, List<Animal>> animals = new HashMap<>();

        when(turtle.getAge()).thenReturn(100);
        animals.put("tester", new ArrayList<>(List.of(turtle)));

        Map<Animal, Integer> result = repository.findOlderAnimal(animals, 10);
        assertEquals(100, result.get(turtle));
    }

    protected LocalDate yearsDiff(int years) {
        return LocalDate.now().minusDays(365*years);
    }

    @Test
    @DisplayName("findOlderAnimal: oldest detection when no age candidates")
    void findLeapYearNames_oldest() throws InvalidAnimalAgeException {
        AnimalsRepositoryImpl repository = new AnimalsRepositoryImpl();
        Map<String, List<Animal>> animals = new HashMap<>();

        when(turtle.getAge()).thenReturn(2);

        Wolf wolfMock = mock(Wolf.class);
        when(wolfMock.getAge()).thenReturn(5);

        animals.put("tester", new ArrayList<>(List.of(turtle, wolfMock)));

        Map<Animal, Integer> result = repository.findOlderAnimal(animals, 10);
        assertEquals(5, result.get(wolfMock));
    }

    @Test
    @DisplayName("findDuplicate: animals list not null")
    void findDuplicate_AnimalsArg() {
        AnimalsRepositoryImpl repository = new AnimalsRepositoryImpl();
        assertThrows(InvalidAnimalListException.class, () -> repository.findLeapYearNames(null));
    }
}