package prswe2.ss21.ue05.demo.max;

public class SequentialMax {

	private final long[] numbers; 
	private long max; 
	
	SequentialMax(long[] numbers) {
		this.numbers = numbers; 
		this.max = Long.MIN_VALUE; 
	}
	
	public long getMax() {
		return max; 
	}

	public void findMax() {
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] > max) {
				max = numbers[i]; 
			}
		}
	}

}
