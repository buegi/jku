package swe2.ss20.ue11.demo.pizza.dishes;

import swe2.inout.Out;

public class AustrianPizza extends Pizza {
    @Override
    public void prepare() {
        Out.println("preparing " + this);
        Out.println("  (very special handling for an austrian pizza)");
    }

    @Override
    public String toString() {
        return "austrian pizza";
    }
}