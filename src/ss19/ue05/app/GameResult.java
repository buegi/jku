package ss19.ue05.app;

public class GameResult {

	// TODO
	private final String name;
	private final double gewinn;

	public GameResult(String name, double gewinn) {
		this.name = name;
		this.gewinn = gewinn;
	}

	public String getName() {
		return name;
	}

	public double getGewinn() {
		return gewinn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(gewinn);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		GameResult other = (GameResult) obj;
		if (Double.doubleToLongBits(gewinn) != Double.doubleToLongBits(other.gewinn))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}