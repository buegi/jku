package ss19.ue10.demo.pizza.abstractFactory;

import pizza.dishes.ItalianPizza;
import pizza.dishes.Pizza;
import pizza.dishes.Spaghetti;

public class ItalianStyleFactory extends AbstractStyleFactory {
	
	@Override
	public Pizza createPizza() {
		return new ItalianPizza();
	}

	@Override
	public Spaghetti createSpaghetti() {
		return new Spaghetti("italian");
	}
}
