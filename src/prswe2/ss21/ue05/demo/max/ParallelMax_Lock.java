package prswe2.ss21.ue05.demo.max;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelMax_Lock {
	
	private ExecutorService executor = Executors.newFixedThreadPool(5); 

	private final long[] numbers; 
	private final int parallelism; 
	private long max; 
	private final Lock maxLock; 
	private CountDownLatch endLatch; 
	
	ParallelMax_Lock(long[] numbers, int parallelism) {
		this.numbers = numbers; 
		this.parallelism = parallelism; 
		this.maxLock = new ReentrantLock(); 
		this.max = numbers[0]; 
	}
	
	public long getMax() {
		return max;
	}

	public void findMaxParallel() {
		endLatch = new CountDownLatch(parallelism); 
		MaxRunnable[] tasks = new MaxRunnable[parallelism]; 
		int size = numbers.length / parallelism; // assumption: numbers.length divisible by parallelism
		for (int i = 0; i < parallelism; i++) {
			tasks[i] = new MaxRunnable(size * i, size * (i + 1)); 
			executor.submit(tasks[i]); 
		}
		try {
			endLatch.await();
		} catch (InterruptedException e) {
		}
		executor.shutdown();
	}

	
	private class MaxRunnable implements Runnable {
		
		private final int from, to; 

		public MaxRunnable(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public void run() {
			for (int i = from; i < to; i++) {
				maxLock.lock();
				try {
					if (numbers[i] >= max) {
						max = numbers[i]; 
					}
				} finally {
					maxLock.unlock(); 
				}
			}
			endLatch.countDown();
		}
	}

}
