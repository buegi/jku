package swe2.ss19.ue10.demo.pizza.dishes;

import swe2.inout.Out;

public class Spaghetti implements Cloneable {
	
	private String kind;

	public Spaghetti(String kind) {
		this.kind = kind;
	}

	public void cook() {
		Out.println("cooking " + this);
	}

	@Override
	public String toString() {
		return "spaghetti (" + kind + " kind)";
	}

	/**
	 * override protected method defined in class Object to make it public
	 */
	@Override
	public Spaghetti clone() {
		try {
			return (Spaghetti) super.clone();
		} catch (CloneNotSupportedException ex) {
			throw new InternalError(ex.getMessage());
		}
	}
}
