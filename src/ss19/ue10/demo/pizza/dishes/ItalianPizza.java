package ss19.ue10.demo.pizza.dishes;

import inout.Out;

public class ItalianPizza extends Pizza {
	
	@Override
	public void prepare() {
		Out.println("preparing " + this);
		Out.println("  (very special handling for an italian pizza)");
	}

	@Override
	public String toString() {
		return "italian pizza";
	}
}
