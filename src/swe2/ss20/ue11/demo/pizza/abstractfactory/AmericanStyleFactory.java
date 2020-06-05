package swe2.ss20.ue11.demo.pizza.abstractfactory;

import swe2.ss20.ue11.demo.pizza.dishes.AmericanPizza;
import swe2.ss20.ue11.demo.pizza.dishes.Pizza;
import swe2.ss20.ue11.demo.pizza.dishes.Spaghetti;

public class AmericanStyleFactory extends AbstractStyleFactory {

    @Override
    public Pizza createPizza() {
        return new AmericanPizza();
    }

    @Override
    public Spaghetti createSpaghetti() {
        return new Spaghetti("american");
    }
}