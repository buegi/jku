package algodat1.ss19.ue04;

public class LowLevelAnalysis {

	public static void main(String[] args) {

		algoOne(11);
		// algoTwo(1000);
	}

	private static int algoOne(int n) {
		int loopCount1 = 0;
		int loopCount2 = 0;

		int sum = 1;
		int i = 1;
		while (i <= n) {
			int p = 2;
			int j = 2;
			loopCount1++;
			//System.out.println(i + " " + j);
			while (j <= i) {
				p = p * 2;
				j = j + 1;
				loopCount2++;
				//System.out.println(i + " " + j);
			}
			sum = sum + p;
			i = i + 1;
		}
		int avg = sum / (n + 1);
		System.out.println("algoOne: " + loopCount1 + " " + loopCount2);
		return avg;
	}

	private static int algoTwo(int n) {
		int loopCount = 0;
		int sum = 1;
		int p = 1;
		int i = 1;
		while (i <= n) {
			p = p * 2;
			sum = sum + p;
			i = i + 1;
			loopCount++;
		}
		int avg = sum / (n + 1);
		System.out.println("algoTwo: " + loopCount);
		return avg;
	}
}