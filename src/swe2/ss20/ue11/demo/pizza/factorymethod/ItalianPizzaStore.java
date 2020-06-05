package swe2.ss20.ue11.demo.pizza.factorymethod;

import swe2.ss20.ue11.demo.pizza.dishes.ItalianPizza;
import swe2.ss20.ue11.demo.pizza.dishes.Pizza;
import swe2.ss20.ue11.demo.pizza.dishes.Spaghetti;

public class ItalianPizzaStore extends PizzaStore {

    @Override
    public Pizza createPizza() {
        return new ItalianPizza();
    }

    @Override
    public Spaghetti createSpaghetti() {
        return new Spaghetti("italian");
    }
}