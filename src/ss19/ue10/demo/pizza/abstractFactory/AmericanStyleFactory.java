package ss19.ue10.demo.pizza.abstractFactory;

import pizza.dishes.AmericanPizza;
import pizza.dishes.Pizza;
import pizza.dishes.Spaghetti;

public class AmericanStyleFactory extends AbstractStyleFactory {
	
	@Override
	public Pizza createPizza() {
		return new AmericanPizza();
	}

	@Override
	public Spaghetti createSpaghetti() {
		return new Spaghetti("american");
	}
	
}
