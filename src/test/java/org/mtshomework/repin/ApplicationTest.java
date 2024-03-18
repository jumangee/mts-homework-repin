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

    /*@Test
    @DisplayName("Test for InvalidAnimalBirthDateException")
    void main_AnimalBirthDateException() throws InvalidAnimalBirthDateException {
        doThrow(new InvalidAnimalBirthDateException("Error occurred")).when(svc).animals();

        //assertThrows(InvalidAnimalException.class, () -> new Application().run(svc));
    }*/

    @Test
    void err() {
        assertThrows(InvalidAnimalException.class, () -> new Application().run(new CreateAnimalServiceImpl(), new AnimalsRepositoryImpl()));
    }
}