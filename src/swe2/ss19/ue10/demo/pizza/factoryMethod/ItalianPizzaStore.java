package swe2.ss19.ue10.demo.pizza.factoryMethod;

import swe2.ss19.ue10.demo.pizza.dishes.ItalianPizza;
import swe2.ss19.ue10.demo.pizza.dishes.Pizza;
import swe2.ss19.ue10.demo.pizza.dishes.Spaghetti;

public class ItalianPizzaStore extends PizzaStore {
	
	@Override
	public Pizza createPizza() {
		return new ItalianPizza();
	}

	@Override
	public Spaghetti createSpaghetti() {
		return new Spaghetti("italian");
	}
}
