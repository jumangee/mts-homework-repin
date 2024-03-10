package org.mtshomework.repin;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.exceptions.InvalidAnimalBirthDateException;
import org.mtshomework.repin.exceptions.InvalidAnimalException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

    @Mock
    CreateAnimalServiceImpl svc;

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
    @DisplayName("Test for InvalidAnimalException")
    void main_InvalidAnimalException() {
        try {
            doThrow(new InvalidAnimalException("Error occurred")).when(svc).animals(anyInt());
        } catch (InvalidAnimalException e) {
            throw new RuntimeException(e);
        } catch (InvalidAnimalBirthDateException e) {
            throw new RuntimeException(e);
        }

        assertThrows(InvalidAnimalException.class, () -> svc.animals(1));
    }

    @Test
    @DisplayName("Test for InvalidAnimalBirthDateException")
    void main_AnimalBirthDateException() {
        try {
            doThrow(new InvalidAnimalBirthDateException("Error occurred")).when(svc).animals(anyInt());
        } catch (InvalidAnimalException e) {
            throw new RuntimeException(e);
        } catch (InvalidAnimalBirthDateException e) {
            throw new RuntimeException(e);
        }

        assertThrows(InvalidAnimalBirthDateException.class, () -> svc.animals(1));
    }
}