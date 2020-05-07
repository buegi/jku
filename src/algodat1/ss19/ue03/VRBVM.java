package algodat1.ss19.ue03;

public class VRBVM {
	// vintage red bull vending machine

	private int tenCentCount;
	private int fiftyCentCount;
	private int RedBullCanCount;

	public VRBVM(int tenCentCount, int fiftyCentCount, int RedBullCanCount) {
		this.tenCentCount = tenCentCount;
		this.fiftyCentCount = fiftyCentCount;
		this.RedBullCanCount = RedBullCanCount;
	}

	public static void insertCoin(int insertedCoin) {
		int tempTenCentCount = 0;
		int tempFiftyCentCount = 0;
		int actualInsertedSum = 0;
		int change = 0;
		boolean handOut = false;
		String msg;

		if (insertedCoin == 10) {
			tempTenCentCount++;
			actualInsertedSum = (tempTenCentCount * 10) + (tempFiftyCentCount * 50);
		}
		if (insertedCoin == 50) {
			tempFiftyCentCount++;
			actualInsertedSum = (tempTenCentCount * 10) + (tempFiftyCentCount * 50);
		}

		if (actualInsertedSum >= 90) {
			handOut = true;
			change = actualInsertedSum - 90;
		} else {
			msg = "You need to insert another " + Math.abs((actualInsertedSum - 90)) + "Cents!";
		}
	}
}