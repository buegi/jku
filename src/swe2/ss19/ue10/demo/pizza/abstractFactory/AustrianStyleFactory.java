package swe2.ss19.ue10.demo.pizza.abstractFactory;

import swe2.ss19.ue10.demo.pizza.dishes.AustrianPizza;
import swe2.ss19.ue10.demo.pizza.dishes.Pizza;
import swe2.ss19.ue10.demo.pizza.dishes.Spaghetti;

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
