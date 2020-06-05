package swe2.ss20.ue11.demo.pizza.abstractfactory;

import swe2.ss20.ue11.demo.pizza.dishes.AustrianPizza;
import swe2.ss20.ue11.demo.pizza.dishes.Pizza;
import swe2.ss20.ue11.demo.pizza.dishes.Spaghetti;

public class AustrianStyleFactory extends AbstractStyleFactory {

    @Override
    public Pizza createPizza() {
        return new AustrianPizza();
    }

    @Override
    public Spaghetti createSpaghetti() {
        return new Spaghetti("austrian");
    }
}