package org.mtshomework.repin;

import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.exceptions.InvalidAnimalAgeException;
import org.mtshomework.repin.exceptions.InvalidAnimalBirthDateException;
import org.mtshomework.repin.exceptions.InvalidAnimalException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Application {
    public static void run(CreateAnimalServiceImpl svc, AnimalsRepository animalsRepository) {
        try {
            Map<String, List<Animal>> animals = svc.animals();

            System.out.println("findLeapYearNames => " + animalsRepository.findLeapYearNames(animals));

            System.out.println("findOlderAnimal => " + animalsRepository.findOlderAnimal(animals, 4));

            System.out.println("findDuplicate => " + animalsRepository.findDuplicate(animals));

        } catch (RuntimeException e) {
            StackTraceElement[] trace = e.getStackTrace();
            System.out.println("Работа метода завершилась ошибкой:" + e.getMessage());
        } catch (InvalidAnimalBirthDateException e) {
            System.out.println("Некорректное значение animal->birthdate");
            throw new RuntimeException(e);
        } catch (InvalidAnimalAgeException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        run(new CreateAnimalServiceImpl(), new AnimalsRepositoryImpl());
    }
}