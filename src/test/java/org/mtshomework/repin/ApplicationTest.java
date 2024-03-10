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

    @Test
    @DisplayName("Test for InvalidAnimalException")
    void main_InvalidAnimalException() throws InvalidAnimalException, InvalidAnimalBirthDateException {
        doThrow(new InvalidAnimalException("Error occurred")).when(svc).animals(anyInt());

        assertThrows(InvalidAnimalException.class, () -> svc.animals(1));
    }

    @Test
    @DisplayName("Test for InvalidAnimalBirthDateException")
    void main_AnimalBirthDateException() throws InvalidAnimalException, InvalidAnimalBirthDateException {
        doThrow(new InvalidAnimalBirthDateException("Error occurred")).when(svc).animals(anyInt());

        assertThrows(InvalidAnimalBirthDateException.class, () -> svc.animals(1));
    }
}