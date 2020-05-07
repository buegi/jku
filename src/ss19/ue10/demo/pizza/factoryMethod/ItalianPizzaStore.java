package ss19.ue10.demo.pizza.factoryMethod;

import pizza.dishes.ItalianPizza;
import pizza.dishes.Pizza;
import pizza.dishes.Spaghetti;

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
