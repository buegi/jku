package swe2.ss19.ue10.demo.pizza.dishes;

import swe2.inout.Out;

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
