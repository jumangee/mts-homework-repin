package org.mtshomework.repin;

import org.mtshomework.repin.animals.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateAnimalServiceImpl extends CreateAnimalService {
    protected List<Method> factoryList = new ArrayList<>();

    void registerAnimal(Class<?> animalClass) throws NoSuchMethodException {
        factoryList.add(animalClass.getMethod("factory"));
    }

    protected List<Method> getFactoryList() {
        return factoryList;
    }

    CreateAnimalServiceImpl() {
        try {

            registerAnimal(Turtle.class);
            registerAnimal(Wolf.class);
            registerAnimal(Eagle.class);
            registerAnimal(Antelope.class);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public void animals() {
        Iterator<Method> listIterator = getFactoryList().iterator();
        Integer size = 0;
        do {
            if (!listIterator.hasNext()) {
                listIterator = getFactoryList().iterator();
            }
            try {
                this.printInfo((Animal) listIterator.next().invoke(null));
                size++;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } while (size < DEFAULT_ANIMALS_AMOUNT);
    }

    public void animals(Integer amount) {
        Iterator<Method> listIterator = getFactoryList().iterator();
        for (int i = 0; i < amount; i++) {
            if (!listIterator.hasNext()) {
                listIterator = getFactoryList().iterator();
            }
            try {
                this.printInfo((Animal) listIterator.next().invoke(null));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
