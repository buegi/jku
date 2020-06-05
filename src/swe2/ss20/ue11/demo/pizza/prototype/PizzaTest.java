package swe2.ss20.ue11.demo.pizza.prototype;

import swe2.inout.In;
import swe2.inout.Out;
import swe2.ss20.ue11.demo.pizza.dishes.AmericanPizza;
import swe2.ss20.ue11.demo.pizza.dishes.ItalianPizza;
import swe2.ss20.ue11.demo.pizza.dishes.Pizza;
import swe2.ss20.ue11.demo.pizza.dishes.Spaghetti;

public class PizzaTest {
    public static void main(String[] args) {

        Out.println("Welcome to the SSW pizza store (implementation using prototype)");
        Out.println("Please select your preferred style:");
        Out.println("1) Italian style");
        Out.println("2) American style");

        Pizza pizzaPrototype;
        Spaghetti spaghettiPrototype;

        switch (In.readInt()) {
            case 1:
                pizzaPrototype = new ItalianPizza();
                spaghettiPrototype = new Spaghetti("italian");
                break;
            case 2:
                pizzaPrototype = new AmericanPizza();
                spaghettiPrototype = new Spaghetti("american");
                break;
            default:
                Out.println("invalid input");
                return;
        }
        PizzaStore pizzaStore = new PizzaStore(pizzaPrototype, spaghettiPrototype);

        Pizza pizza = pizzaStore.orderPizza();
        Out.println("ready to eat " + pizza);
        Out.println();

        Spaghetti spaghetti = pizzaStore.orderSpaghetti();
        Out.println("ready to eat " + spaghetti);
        Out.println();
    }
}