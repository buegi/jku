package ss19.ue10.demo.pizza.abstractFactory;

import ss19.ue10.demo.pizza.dishes.Pizza;
import ss19.ue10.demo.pizza.dishes.Spaghetti;

public abstract class AbstractStyleFactory {

    public abstract Pizza createPizza();

    public abstract Spaghetti createSpaghetti();
}
