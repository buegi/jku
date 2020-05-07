package ss19.ue10.demo.pizza.factoryMethod;

import pizza.dishes.Pizza;
import pizza.dishes.Spaghetti;

/**
 * Implementation of a pizza store using the design pattern "factory method"
 */
public abstract class PizzaStore {
	
	public final Pizza orderPizza() {
		Pizza pizza = createPizza();

		pizza.prepare();
		pizza.bake();
		pizza.box();
		return pizza;
	}

	public Spaghetti orderSpaghetti() {
		Spaghetti spaghetti = createSpaghetti();

		spaghetti.cook();
		return spaghetti;
	}

	public abstract Pizza createPizza();

	public abstract Spaghetti createSpaghetti();
}
