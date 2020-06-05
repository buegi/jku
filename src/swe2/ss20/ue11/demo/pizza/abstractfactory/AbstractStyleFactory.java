package swe2.ss20.ue11.demo.pizza.abstractfactory;

import swe2.ss20.ue11.demo.pizza.dishes.Pizza;
import swe2.ss20.ue11.demo.pizza.dishes.Spaghetti;

public abstract class AbstractStyleFactory {

    public abstract Pizza createPizza();

    public abstract Spaghetti createSpaghetti();
}