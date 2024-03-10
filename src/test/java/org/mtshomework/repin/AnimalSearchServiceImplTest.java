package org.mtshomework.repin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.animals.Antelope;
import org.mtshomework.repin.exceptions.InvalidAnimalBirthDateException;
import org.mtshomework.repin.exceptions.InvalidAnimalException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;

@ExtendWith(MockitoExtension.class)
class AnimalSearchServiceImplTest {

    AnimalSearchService svc = new AnimalSearchServiceImpl();

    @Mock
    Animal mockAnimal;

    private static ByteArrayOutputStream outContent;
    private final static PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        System.out.println("BeforeAll setUpStreams() method called");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.out.println("AfterAll restoreStreams() method called");
    }

    @Test
    @DisplayName("Test for animal is not null")
    void checkLeapYearAnimal_IncorrectParam() {
        InvalidAnimalException e = assertThrows(InvalidAnimalException.class, () -> svc.checkLeapYearAnimal(null));

        assertEquals("на вход пришло некорректный объект животного " + LocalDate.now(), e.getMessage());
    }

    @Test
    @DisplayName("Test for animal.birthDate is not null")
    void checkLeapYearAnimal_IncorrectBirthDate() {
        when(mockAnimal.getBirthDate()).thenReturn(null);

        InvalidAnimalBirthDateException e = assertThrows(InvalidAnimalBirthDateException.class, () -> svc.checkLeapYearAnimal(mockAnimal));

        assertEquals("у животного "+mockAnimal.getName()+" не указана дата его рождения", e.getMessage());
    }

    @ParameterizedTest()
    @DisplayName("Leap year algorithm test")
    @ValueSource(longs = {10000, 10001, 10002, 10003, 10004})
    void checkLeapYearAnimal_Algorithm(long checkForDay) {
        boolean isLeapDay = LocalDate.ofEpochDay(checkForDay).isLeapYear();
        when(mockAnimal.getBirthDate()).thenReturn(LocalDate.ofEpochDay(checkForDay));
        when(mockAnimal.getName()).thenReturn("mockAnimal");

        try {
            svc.checkLeapYearAnimal(mockAnimal);
        } catch (InvalidAnimalException e) {
        } catch (InvalidAnimalBirthDateException e) {
        }

        assertEquals(mockAnimal.getName() +
                (isLeapDay ?
                        " БЫЛ рожден в високосный год"
                        :
                        " не был рожден в високосный год\r\n"), outContent.toString());

        AnimalSearchServiceImplTest.setUpStreams(); // сброс буффера
    }
}