package org.mtshomework.repin;

import org.mtshomework.repin.animals.*;
import org.mtshomework.repin.exceptions.InvalidAnimalBirthDateException;
import org.mtshomework.repin.exceptions.InvalidAnimalException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateAnimalServiceImpl extends CreateAnimalService {

    AnimalSearchService search = new AnimalSearchServiceImpl();

    @Override
    protected void printInfo(Animal a) throws InvalidAnimalBirthDateException {
        //System.out.println("+ " + a.getBreed() + " " + a.getName() + " ("+a.getCharacter()+") по цене " + a.getCost());
        super.printInfo(a);
        search.checkLeapYearAnimal(a);
    }

    public void animals() throws InvalidAnimalBirthDateException {
        Integer size = 0;
        do {
            this.printInfo(create());
            size++;
        } while (size < DEFAULT_ANIMALS_AMOUNT);
    }

    public void animals(Integer amount) throws InvalidAnimalBirthDateException {
        for (int i = 0; i < amount; i++) {
            this.printInfo(create());
        }
    }
}
