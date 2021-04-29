package prswe2.ss21.ue05.demo.max;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelMax_Locals {
	
	private ExecutorService executor = Executors.newFixedThreadPool(5); 

	private final long[] numbers; 
	private final int parallelism; 
	private long max = Long.MIN_VALUE; 
	
	ParallelMax_Locals(long[] numbers, int parallelism) {
		this.numbers = numbers; 
		this.parallelism = parallelism; 
	}
	
	public long getMax() {
		synchronized (numbers) { 
			return max; 
		}
	}

	public void findMaxParallel() {
		MaxCallable[] tasks = new MaxCallable[parallelism]; 
		@SuppressWarnings("unchecked")
		Future<Long>[] ftrMaxs = (Future<Long>[]) new Future[parallelism]; 
		int size = numbers.length / parallelism; // assumption: numbers.length divisible by parallelism
		for (int i = 0; i < parallelism; i++) {
			tasks[i] = new MaxCallable(size * i, size * (i + 1)); 
			ftrMaxs[i] = executor.submit(tasks[i]); 
		}
		for (Future<Long> ftrMax : ftrMaxs) {
			long localMax;
			try {
				localMax = ftrMax.get();  // blocks
				if (localMax > max) {
					max = localMax; 
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} 
			
		}
		executor.shutdown();
	}

	
	private class MaxCallable implements Callable<Long> {
		
		private final int from, to; 

		public MaxCallable(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public Long call() {
			long localMax = Long.MIN_VALUE; 
			for (int i = from; i < to; i++) {
				if (numbers[i] >= localMax) {
					localMax = numbers[i]; 
				}
			}
			return localMax; 
		}
	}

}
