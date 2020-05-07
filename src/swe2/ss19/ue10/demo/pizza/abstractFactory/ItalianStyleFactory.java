package swe2.ss19.ue10.demo.pizza.abstractFactory;

import swe2.ss19.ue10.demo.pizza.dishes.ItalianPizza;
import swe2.ss19.ue10.demo.pizza.dishes.Pizza;
import swe2.ss19.ue10.demo.pizza.dishes.Spaghetti;

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