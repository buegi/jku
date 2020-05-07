package swe2.ss19.ue10.demo.pizza.factoryMethod;

import swe2.ss19.ue10.demo.pizza.dishes.AmericanPizza;
import swe2.ss19.ue10.demo.pizza.dishes.Pizza;
import swe2.ss19.ue10.demo.pizza.dishes.Spaghetti;

public class AmericanPizzaStore extends PizzaStore {
	
	@Override
	public Pizza createPizza() {
		return new AmericanPizza();
	}

	@Override
	public Spaghetti createSpaghetti() {
		return new Spaghetti("american");
	}
}
