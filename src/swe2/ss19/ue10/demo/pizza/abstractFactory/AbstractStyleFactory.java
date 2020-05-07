package swe2.ss19.ue10.demo.pizza.abstractFactory;

import swe2.ss19.ue10.demo.pizza.dishes.Pizza;
import swe2.ss19.ue10.demo.pizza.dishes.Spaghetti;

public abstract class AbstractStyleFactory {

    public abstract Pizza createPizza();

    public abstract Spaghetti createSpaghetti();
}
