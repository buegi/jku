package swe2.ss19.ue04.item;

public class Food extends Item {

	private final double weight;

	public Food(String name, double price, double weight) {
		super(name, price);
		this.weight = weight;
	}

	public double getWeight() {
		return this.weight;
	}

	@Override
	public String toString() {
		return super.getName() + ": " + super.getPrice() + " (" + this.getWeight() + "kg)";
	}
}