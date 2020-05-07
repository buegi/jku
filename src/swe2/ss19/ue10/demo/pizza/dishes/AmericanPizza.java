package swe2.ss19.ue10.demo.pizza.dishes;

import swe2.inout.Out;

public class AmericanPizza extends Pizza {
	
	@Override
	public void prepare() {
		Out.println("preparing " + this);
		Out.println("  (very special handling for an american pizza)");
	}

	@Override
	public String toString() {
		return "american pizza";
	}
}
