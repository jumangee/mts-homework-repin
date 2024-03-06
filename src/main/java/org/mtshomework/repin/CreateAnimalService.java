package org.mtshomework.repin;

import org.mtshomework.repin.animals.Animal;
import org.mtshomework.repin.animals.Turtle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CreateAnimalService {
    //ArrayList<Class<?>> classList = new ArrayList<>();
    //List<Method> factoryList;

    /*void registerAnimal(Class<?> animalClass) {
        classList.add(animalClass);
    }*/
    protected static Integer DEFAULT_ANIMALS_AMOUNT = 10;

    abstract void registerAnimal(Class<?> animalFactory) throws NoSuchMethodException;

    protected abstract List<Method> getFactoryList();

    /*Animal create(int t) {
        Method factory = factoryList.get(t);
        try {
            return (Animal) factory.invoke(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }*/

    protected void printInfo(Animal a) {
        System.out.println("+ " + a.getBreed() + " " + a.getName() + " ("+a.getCharacter()+") по цене " + a.getCost());
    }

    public void animals() {
        Iterator<Method> listIterator = getFactoryList().iterator();
        Integer size = 0;
        while (size < DEFAULT_ANIMALS_AMOUNT) {
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
        }
    }
}
