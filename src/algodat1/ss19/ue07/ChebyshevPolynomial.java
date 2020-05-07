package algodat1.ss19.ue07;

public class ChebyshevPolynomial {

	// Definition
	// T(0) (y) = 1
	// T(1) (y) = y
	// T(n) (y) = 2y * T(n-1) (y) - T(n-2) (y) fuer n > 1

	private int n;
	private int y;

	public ChebyshevPolynomial(int n, int y) {
		this.n = n;
		this.y = y;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static void main(String[] args) {
		ChebyshevPolynomial cp = new ChebyshevPolynomial(3, 2);
		System.out.println(cp.compute(cp.getN(), cp.getY()));
	}

	public int compute(int n, int y) {
		if (n < 0 ) {
			return -1;
		} else if (n == 0) {
			return 1;
		} else if (n == 1) {
			return y;
		} else {
			return (2 * y * compute(n - 1, y)) - (compute(n - 2, y));
		}
	}
}