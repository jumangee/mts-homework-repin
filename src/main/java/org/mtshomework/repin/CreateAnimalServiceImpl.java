package org.mtshomework.repin;

import org.mtshomework.repin.animals.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateAnimalServiceImpl extends CreateAnimalService {
    public void animals() {
        Integer size = 0;
        do {
            this.printInfo(create());
            size++;
        } while (size < DEFAULT_ANIMALS_AMOUNT);
    }

    public void animals(Integer amount) {
        for (int i = 0; i < amount; i++) {
            this.printInfo(create());
        }
    }
}
