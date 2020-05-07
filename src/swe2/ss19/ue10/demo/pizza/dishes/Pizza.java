package swe2.ss19.ue10.demo.pizza.dishes;

import swe2.inout.Out;

public abstract class Pizza implements Cloneable {
	
	public abstract void prepare();

	public void bake() {
		Out.println("baking " + this);
	}

	public void box() {
		Out.println("cutting and boxing " + this);
	}

	/**
	 * override protected method defined in class Object to make it public
	 */
	@Override
	public Pizza clone() {
		try {
			return (Pizza) super.clone();
		} catch (CloneNotSupportedException ex) {
			throw new InternalError(ex.getMessage());
		}
	}
}
