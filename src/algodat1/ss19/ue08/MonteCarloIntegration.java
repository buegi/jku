package algodat1.ss19.ue08;

public class MonteCarloIntegration {

	public static void main(String[] args) {

		Rectangle rec = new Rectangle(10, 10, 0, 0);

		Circle c1 = new Circle(1, 2, 2);
		Circle c2 = new Circle(1, 4, 8);
		Circle c3 = new Circle(1, 6, 2);

		MonteCarloIntegration mci = new MonteCarloIntegration();

		int samplesize = 1000;
		int countIn = 0;
		int countOut = 0;

		for (int i = 0; i < samplesize; i++) {

		}
	}

	private boolean inside(int cicrleN, float x, float y) {

		// TODO
		return false;
	}

	private float area(float a, float b) {

		// TODO
		return a * b;
	}

	private float fRand() {
		return (float) Math.random();
	}

}