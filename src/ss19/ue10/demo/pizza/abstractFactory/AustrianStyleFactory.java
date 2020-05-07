package ss19.ue10.demo.pizza.abstractFactory;

import pizza.dishes.AustrianPizza;
import pizza.dishes.Pizza;
import pizza.dishes.Spaghetti;

public class AustrianStyleFactory extends AbstractStyleFactory {
	
	@Override
	public Pizza createPizza() {
		return new AustrianPizza();
	}

	@Override
	public Spaghetti createSpaghetti() {
		return new Spaghetti("austrian");
	}
}
