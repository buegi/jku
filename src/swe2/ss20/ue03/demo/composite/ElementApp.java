package swe2.ss20.ue03.demo.composite;

import swe2.inout.Out;

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