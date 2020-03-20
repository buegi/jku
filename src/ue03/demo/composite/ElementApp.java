package ue03.demo.composite;

import inout.Out;

public class ElementApp {

    public static void main(String[] args) {

        Element e =
                new Composite(1,
                        new Atomic(2),
                        new Composite(3,
                                new Atomic(4),
                                new Atomic(5)));

        int result = e.operation();

        Out.println(result);
    }
}