package ss19.ue10.demo.pizza.abstractFactory;

import ss19.ue10.demo.pizza.dishes.AustrianPizza;
import ss19.ue10.demo.pizza.dishes.Pizza;
import ss19.ue10.demo.pizza.dishes.Spaghetti;

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
