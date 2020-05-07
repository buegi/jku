package algodat1.ss19.ue07;

import java.util.Stack;

public class ChebyshevPolynomialIterative {

	// Definition
	// T(0) (y) = 1
	// T(1) (y) = y
	// T(n) (y) = 2y * T(n-1) (y) - T(n-2) (y) fuer n > 1

	private int n;
	private int y;

	public ChebyshevPolynomialIterative(int n, int y) {
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
		ChebyshevPolynomialIterative cp = new ChebyshevPolynomialIterative(3, 2);
		System.out.println("State, n, y, r1, r2, result, stack");
		System.out.println("Result: " + cp.compute(cp.getN(), cp.getY()));
		// test fuer uebungsfolienbeispiel
		// System.out.println("Result Fibonacci: " + cp.fibonacci(cp.getN(),
		// cp.getY()));
	}

	int compute(int n, int y) {
		int r1 = 0;
		int r2 = 0;
		int result = 0;
		int state = 1;
		Stack<Integer> stack = new Stack<Integer>();
		printCSV(state, n, y, r1, r2, result, stack);
		while (state != 0) {
			switch (state) {
			case 1:
				if (n < 0) {
					result = -1;
					state = 0;
					printCSV(state, n, y, r1, r2, result, stack);
				} else if (n == 0) {
					result = 1;
					state = 0;
					printCSV(state, n, y, r1, r2, result, stack);
				} else if (n == 1) {
					result = y;
					state = 0;
					printCSV(state, n, y, r1, r2, result, stack);
				} else {
					stack.push(n);
					stack.push(2);
					n = n - 1;
					state = 1;
					printCSV(state, n, y, r1, r2, result, stack);
				}
				break;
			case 2:
				n = stack.pop();
				r1 = 2 * y * result;
				stack.push(r1);
				stack.push(3);
				n = n - 2;
				state = 1;
				printCSV(state, n, y, r1, r2, result, stack);
				break;
			case 3:
				r1 = stack.pop();
				r2 = result;
				result = r1 - r2;
				state = 0;
				printCSV(state, n, y, r1, r2, result, stack);
				break;
			}
			if (state == 0 && !stack.isEmpty()) {
				state = stack.pop();
				printCSV(state, n, y, r1, r2, result, stack);
			}
		}
		return result;
	}

	public void printVars(int state, int n, int y, int r1, int r2, int result, Stack<Integer> stack) {
		System.out.println("State: " + state + ",   n: " + n + ",   y: " + y + ",   r1: " + r1 + ",   r2: " + r2
				+ ",   result: " + result + ",   stack: " + stack + ";");
	}

	public void printCSV(int state, int n, int y, int r1, int r2, int result, Stack<Integer> stack) {
		System.out.println(state + "," + n + "," + y + "," + r1 + "," + r2 + "," + result + "," + stack + ";");
	};

	// Uebungsfolien
	public int fibonacci(int m, int n) {
		int result = 0;
		int w1 = 0;
		int w2 = 0;
		int state = 1;
		Stack<Integer> stack = new Stack<Integer>();
		while (state != 0) {
			switch (state) {
			case 1:
				if ((m == 1) || (n == 1)) {
					result = m + n;
					state = 0;
				} else {
					stack.push(m);
					stack.push(n);
					stack.push(2);
					m = m - 1;
					state = 1;
				}
				break;
			case 2:
				stack.pop();
				stack.pop();
				w1 = result;
				stack.push(w1);
				stack.push(3);
				n = n - 1;
				state = 1;
				break;
			case 3:
				stack.pop();
				w2 = result;
				result = w1 + w2;
				state = 0;
				break;
			} // switch
			if (state == 0 && !stack.isEmpty()) {
				stack.pop();
			} // while
		}
		return result;
	}
}