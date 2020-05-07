package algodat1.ss19.ue08;

public class Circle {

	private int r;
	private int x;
	private int y;

	public Circle(int r, int x, int y) {
		this.r = r;
		this.x = x;
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getArea() {
		return Math.pow(r, 2) * Math.PI;
	}
}