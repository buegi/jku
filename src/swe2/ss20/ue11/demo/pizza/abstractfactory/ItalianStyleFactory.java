package swe2.ss20.ue11.demo.pizza.abstractfactory;

import swe2.ss20.ue11.demo.pizza.dishes.ItalianPizza;
import swe2.ss20.ue11.demo.pizza.dishes.Pizza;
import swe2.ss20.ue11.demo.pizza.dishes.Spaghetti;

public class ItalianStyleFactory extends AbstractStyleFactory {

    @Override
    public Pizza createPizza() {
        return new ItalianPizza();
    }

    @Override
    public Spaghetti createSpaghetti() {
        return new Spaghetti("italian");
    }
}