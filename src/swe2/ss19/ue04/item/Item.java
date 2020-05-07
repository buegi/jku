package swe2.ss19.ue04.item;

public abstract class Item implements Comparable<Item> {

	private final String name;
	private final double price;

	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}

	protected String getName() {
		return this.name;
	}

	protected double getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return this.getName() + ": " + this.getPrice();
	}

	@Override
	public int compareTo(Item o) {
		// return -1 if price of o is lower than price of this
		if ((this.getPrice() - o.getPrice()) > 0) {
			return 1;
		}
		// return 1 if price of o is higher than price of this
		if ((this.getPrice() - o.getPrice()) < 0) {
			return -1;
		}
		// returns < 0 when the String calling the method is lexicographically first
		return this.name.compareToIgnoreCase(o.getName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
}