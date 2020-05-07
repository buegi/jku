package swe2.ss19.ue10.demo.pizza.abstractFactory;

import swe2.ss19.ue10.demo.pizza.dishes.Pizza;
import swe2.ss19.ue10.demo.pizza.dishes.Spaghetti;

/**
 * Implementation of a pizza store using the design pattern "abstract factory"
 */
public class PizzaStore {

    private AbstractStyleFactory factory;

    public PizzaStore(AbstractStyleFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza() {
        Pizza pizza = factory.createPizza();

        pizza.prepare();
        pizza.bake();
        pizza.box();
        return pizza;
    }

    public Spaghetti orderSpaghetti() {
        Spaghetti spaghetti = factory.createSpaghetti();

        spaghetti.cook();
        return spaghetti;
    }

    public void setFactory(AbstractStyleFactory factory) {
        this.factory = factory;
    }

}
