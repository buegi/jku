package ss19.ue10.demo.pizza.factoryMethod;

import ss19.ue10.demo.pizza.dishes.AmericanPizza;
import ss19.ue10.demo.pizza.dishes.Pizza;
import ss19.ue10.demo.pizza.dishes.Spaghetti;

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
