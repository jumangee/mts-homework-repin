package org.mtshomework.repin;

import org.mtshomework.repin.exceptions.InvalidAnimalBirthDateException;
import org.mtshomework.repin.exceptions.InvalidAnimalException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Application {
    public void run(CreateAnimalServiceImpl svc) throws InvalidAnimalException {
        try {
            System.out.println("#1: default animals (10)");
            svc.animals();

            System.out.println("\n#2: for-loop animals (15)");
            svc.animals(15);

            System.out.println("\n#3: do-while animals (10)");
            svc.animals();
        } catch (InvalidAnimalBirthDateException e) {
            System.out.println("Работа метода завершилась ошибкой");
            throw new InvalidAnimalException(e.getMessage());
        }
    }
    public void main(String[] args) throws InvalidAnimalException {
        run(new CreateAnimalServiceImpl());
    }
}