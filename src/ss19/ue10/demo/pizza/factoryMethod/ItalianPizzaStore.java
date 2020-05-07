package ss19.ue10.demo.pizza.factoryMethod;

import ss19.ue10.demo.pizza.dishes.ItalianPizza;
import ss19.ue10.demo.pizza.dishes.Pizza;
import ss19.ue10.demo.pizza.dishes.Spaghetti;

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
