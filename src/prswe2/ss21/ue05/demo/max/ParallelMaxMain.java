package prswe2.ss21.ue05.demo.max;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ParallelMaxMain {
	
	private static final Random RAND = new Random(); 
	private static final int N = 2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2*2; //*2*2  
	private static final int MAX_VALUE = 1000;
	private static final int PARALLELISM = 4; 
	private static final int THRESHHOLD =  N / 2*2*2; //*2 

	public static void main(String[] args) {
		long[] numbers = createNumberArray(N); 
		long start; 

		System.out.println("numbers = " + numbers.length + ", parallelism = " + PARALLELISM); 
		System.out.println(); 
		
		// sequential
		start = System.currentTimeMillis(); 
		SequentialMax calc0 = new SequentialMax(numbers); 
		calc0.findMax();
		long max0 = calc0.getMax(); 
		System.out.println("SequentialMax = " + max0); 
		System.out.format("Elapsed time [ms] %,d%n", (System.currentTimeMillis() - start)); 
		System.out.println(); 

		// using synchronized 
		start = System.currentTimeMillis(); 
		ParallelMax_Synchronized calc1 = new ParallelMax_Synchronized(numbers, PARALLELISM); 
		calc1.findMaxParallel();
		long max1 = calc1.getMax(); 
		System.out.println("ParallelMax_Synchronized = " + max1); 
		System.out.format("Elapsed time [ms] %,d%n", (System.currentTimeMillis() - start)); 
		System.out.println(); 

		// using AtomicLong
		start = System.currentTimeMillis(); 
		ParallelMax_AtomicLong calc2 = new ParallelMax_AtomicLong(numbers, PARALLELISM); 
		calc2.findMaxParallel();
		long max2 = calc2.getMax(); 
		System.out.println("ParallelMax_AtomicLong = " + max2); 
		System.out.format("Elapsed time [ms] %,d%n", (System.currentTimeMillis() - start)); 
		System.out.println(); 
	
		// using Lock
		start = System.currentTimeMillis(); 
		ParallelMax_Lock calc3 = new ParallelMax_Lock(numbers, PARALLELISM); 
		calc3.findMaxParallel();
		long max3 = calc3.getMax(); 
		System.out.println("ParallelMax_Lock = " + max3); 
		System.out.format("Elapsed time [ms] %,d%n", (System.currentTimeMillis() - start)); 
		System.out.println(); 
		
		// with local variables only 
		start = System.currentTimeMillis(); 
		ParallelMax_Locals calc4 = new ParallelMax_Locals(numbers, PARALLELISM); 
		calc4.findMaxParallel();
		long max4 = calc4.getMax(); 
		System.out.println("ParallelMax_Locals = " + max4); 
		System.out.format("Elapsed time [ms] %,d%n", (System.currentTimeMillis() - start)); 
		System.out.println(); 

		// parallel but without any synchronization (not a valid solution)
		start = System.currentTimeMillis(); 
		ParallelMax_NotSynchronized calc5 = new ParallelMax_NotSynchronized(numbers, PARALLELISM); 
		calc5.findMaxParallel();
		long max5 = calc5.getMax(); 
		System.out.println("ParallelMax_NotSynchronized = " + max5); 
		System.out.format("Elapsed time [ms] %,d%n", (System.currentTimeMillis() - start)); 
		System.out.println(); 
		
		// with fork-join framework
		start = System.currentTimeMillis(); 
		RecursiveMaxTask task = new RecursiveMaxTask(numbers, 0, numbers.length, THRESHHOLD, "1"); 
		long max = ForkJoinPool.commonPool().invoke(task);
		System.out.println("RecursiveMaxTask = " + max); 
		System.out.format("Elapsed time [ms] %,d%n", (System.currentTimeMillis() - start)); 

	}

	/**
	 * Creates an array with length n and random values from 0 to MAX_VALUE
	 * @param n length of the array
	 * @return the array with random values
	 */
	private static long[] createNumberArray(int n) {
		long[] ns = new long[n]; 
		for (int i = 0; i < n; i++) {
			ns[i] = RAND.nextInt(MAX_VALUE); 
		}
		return ns;
	}

}
