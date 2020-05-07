package swe2.ss19.ue04.item;

public class Drink extends Item {

	private final double volume;

	public Drink(String name, double price, double volume) {
		super(name, price);
		this.volume = volume;
	}

	public double getVolume() {
		return this.volume;
	}

	@Override
	public String toString() {
		return super.toString() + " (" + this.getVolume() + "L)";
	}
}