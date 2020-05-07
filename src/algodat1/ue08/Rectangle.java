package algodat1.ue08;

public class Rectangle {

	private int a;
	private int b;
	private int x;
	private int y;

	public Rectangle(int a, int b, int x, int y) {
		this.a = a;
		this.b = b;
		this.x = x;
		this.y = y;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
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
		return a * b;
	}
}