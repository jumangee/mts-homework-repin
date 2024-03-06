package org.mtshomework.repin;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Application {
    public static void main(String[] args) {
        CreateAnimalServiceImpl svc = new CreateAnimalServiceImpl();
        System.out.println("#1: default animals (10)");
        ((CreateAnimalService)svc).animals();

        System.out.println("#2: for-loop animals (15)");
        svc.animals(15);

        System.out.println("#3: do-while animals (10)");
        svc.animals();
    }
}