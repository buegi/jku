package swe2.ss20.ue11.demo.pizza.factorymethod;

import swe2.ss20.ue11.demo.pizza.dishes.Pizza;
import swe2.ss20.ue11.demo.pizza.dishes.Spaghetti;

/**
 * Implementation of a pizza store using the design pattern "factory method"
 */
public abstract class PizzaStore {

    public final Pizza orderPizza() {
        Pizza pizza = createPizza();

        pizza.prepare();
        pizza.bake();
        pizza.box();
        return pizza;
    }

    public Spaghetti orderSpaghetti() {
        Spaghetti spaghetti = createSpaghetti();

        spaghetti.cook();
        return spaghetti;
    }

    public abstract Pizza createPizza();

    public abstract Spaghetti createSpaghetti();
}