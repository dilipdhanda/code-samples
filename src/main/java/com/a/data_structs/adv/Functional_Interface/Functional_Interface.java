package com.a.data_structs.adv.Functional_Interface;

import java.util.function.ToDoubleFunction;

@java.lang.FunctionalInterface
interface FunctionalInterface{ // name could be anything

    public int multiplyNumber(int x, int y);

}

public class Functional_Interface {

    public static void main(String[] args) {

        FunctionalInterface functionalInterface = (x, y) -> x * y;
        System.out.println("The result is: "+functionalInterface.multiplyNumber(2, 3));

        ToDoubleFunction<String> length = x -> x.length();
        System.out.println(length.applyAsDouble("Hello Maddy!"));
    }
}

