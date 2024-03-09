package org.mtshomework.repin;

import org.mtshomework.repin.animals.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public abstract class CreateAnimalService {
    enum ANIMAL {
        WOLF {
            public Animal create() {
                return new Wolf();
            }
        },
        EAGLE {
            public Animal create() {
                return new Eagle();
            }
        },
        TURTLE {
            public Animal create() {
                return new Turtle();
            }
        },
        ANTELOPE {
            public Animal create() {
                return new Antelope();
            }
        };

        public Animal create() {
            return null;
        }

        private static final ANIMAL[] vals = values();

        public ANIMAL next() {
            return vals[(this.ordinal() + 1) % vals.length];
        }
    }

    private ANIMAL nextAnimal = ANIMAL.WOLF;

    Animal create() {
        Animal result = nextAnimal.create();
        nextAnimal = nextAnimal.next();
        return result;
    }

    protected static Integer DEFAULT_ANIMALS_AMOUNT = 10;

    protected void printInfo(Animal a) {
        System.out.println("+ " + a.getBreed() + " " + a.getName() + " ("+a.getCharacter()+") по цене " + a.getCost());
    }

    public void animals() {
        Integer size = 0;
        while (size < DEFAULT_ANIMALS_AMOUNT) {
            this.printInfo(create());
        }
    }
}
