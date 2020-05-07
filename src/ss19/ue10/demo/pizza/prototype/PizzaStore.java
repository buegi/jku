package ss19.ue10.demo.pizza.prototype;

import java.util.ArrayList;
import java.util.List;

import ss19.ue10.demo.pizza.dishes.Pizza;
import ss19.ue10.demo.pizza.dishes.Spaghetti;

/**
 * Implementation of a pizza store using the design pattern "prototype"
 */
public class PizzaStore {

    private Pizza pizzaPrototype;
    private Spaghetti spaghettiPrototype;

    public PizzaStore(Pizza pizzaPrototype, Spaghetti spaghettiPrototype) {
        this.pizzaPrototype = pizzaPrototype;
        this.spaghettiPrototype = spaghettiPrototype;
    }

    public void setPizzaPrototype(Pizza pizzaPrototype) {
        this.pizzaPrototype = pizzaPrototype;
    }

    public void setSpaghettiPrototype(Spaghetti spaghettiPrototype) {
        this.spaghettiPrototype = spaghettiPrototype;
    }

    public Pizza orderPizza() {

        Pizza pizza = pizzaPrototype.clone();

        pizza.prepare();
        pizza.bake();
        pizza.box();
        return pizza;
    }

    public Spaghetti orderSpaghetti() {
        Spaghetti spaghetti = spaghettiPrototype.clone();

        spaghetti.cook();
        return spaghetti;
    }

}
