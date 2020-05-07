package algodat1.ue07;

import java.util.Stack;

public class ChebyshevPolynomialWithoutStack {

	// Definition
	// T(0) (y) = 1
	// T(1) (y) = y
	// T(n) (y) = 2y * T(n-1) (y) - T(n-2) (y) fuer n > 1

	private int n;
	private int y;

	public ChebyshevPolynomialWithoutStack(int n, int y) {
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
		ChebyshevPolynomialWithoutStack cp = new ChebyshevPolynomialWithoutStack(3, 2);
		System.out.println("Result: " + cp.compute(cp.getN(), cp.getY()));
		// test fuer uebungsfolienbeispiel
		// System.out.println("Result Fibonacci: " + cp.fibonacci(cp.getN(),
		// cp.getY()));
	}

	int compute(int n, int y) {
		int ppn = 1;
		int pn = y;
		int cur = 0;
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return y;
		}
		for (int i = 2; i <= n; i++) {
			cur = 2 * y * pn - ppn;
			ppn = pn;
			pn = cur;
		}
		return cur;
	}

	public void printVars(int state, int n, int y, int r1, int r2, int result, Stack<Integer> stack) {
		System.out.println("State: " + state + ",   n: " + n + ",   y: " + y + ",   r1: " + r1 + ",   r2: " + r2
				+ ",   result: " + result + ",   stack: " + stack + ";");
	}

	public void printCSV(int state, int n, int y, int r1, int r2, int result, Stack<Integer> stack) {
		System.out.println(state + "," + n + "," + y + "," + r1 + "," + r2 + "," + result + "," + stack + ";");
	};
}