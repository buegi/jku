package prswe2.ss21.ue05.demo.max;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class RecursiveMaxTask extends RecursiveTask<Long> {

	private final int threshhold;
	
	private final long[] numbers;
	private final int from, to; 
	private final String id; 

	public RecursiveMaxTask(long[] numbers, int from, int to, int threshhold, String id) {
		super();
		this.numbers = numbers;
		this.from = from; 
		this.to = to; 
		this.threshhold = threshhold; 
		this.id = id; 
	}

	@Override
	protected Long compute() {
		if (to - from <= threshhold + 1) {
			long max = Arrays.stream(numbers, from, to).max().orElse(Long.MIN_VALUE); 
//			System.out.format(id + " - SEQ: [%d, %d] = %d%n", from, to, max); 
			return max; 
		} else {
			int mid = (from + to) / 2; 
//			System.out.format(id + " - DIV [%d, %d] %n", from, to); 
			RecursiveMaxTask task1 = new RecursiveMaxTask(numbers, from, mid + 1, threshhold, id + ".1"); 
			RecursiveMaxTask task2 = new RecursiveMaxTask(numbers, mid + 1, to, threshhold, id + ".2"); 
			task1.fork(); 
			task2.fork(); 
			long max1 = task1.join(); 
			long max2 = task2.join(); 
			long max = Math.max(max1, max2); 
//			System.out.format(id + " - CMB [%d, %d] = %d%n", from, to, max); 
			return max; 
		}
		
	}
}
