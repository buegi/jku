package ss19.ue10.demo.pizza.noPattern;

import pizza.dishes.AmericanPizza;
import pizza.dishes.ItalianPizza;
import pizza.dishes.Pizza;
import pizza.dishes.Spaghetti;

/**
 * Implementation of a pizza store using no design pattern
 */
public class PizzaStore {
	private String style;

	public PizzaStore(String style) {
		this.style = style;
	}

	public Pizza orderPizza() {
		Pizza pizza;
		if (style.equals("italian")) {
			pizza = new ItalianPizza();
		} else if (style.equals("american")) {
			pizza = new AmericanPizza();
		} else {
			throw new Error("invalid style");
		}

		pizza.prepare();
		pizza.bake();
		pizza.box();
		return pizza;
	}

	public Spaghetti orderSpaghetti() {
		Spaghetti spaghetti = new Spaghetti(style);

		spaghetti.cook();
		return spaghetti;
	}
}
