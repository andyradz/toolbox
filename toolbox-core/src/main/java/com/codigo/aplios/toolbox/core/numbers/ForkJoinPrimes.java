package com.codigo.aplios.toolbox.core.numbers;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ForkJoinPrimes {
	private static int workSize;
	private static Queue<Results> resultsQueue;

	// Use this to collect work
	private static class Results {
		public final int minPrimeTry;
		public final int maxPrimeTry;
		public final Set resultSet;

		public Results(int minPrimeTry, int maxPrimeTry, Set resultSet) {

			this.minPrimeTry = minPrimeTry;
			this.maxPrimeTry = maxPrimeTry;
			this.resultSet = resultSet;
		}
	}

	private static class FindPrimes extends RecursiveAction {
		private final int start;
		private final int end;

		public FindPrimes(int start, int end) {

			this.start = start;
			this.end = end;
		}

		private Set<Integer> findPrimes(int minPrimeTry, int maxPrimeTry) {

			Set<Integer> s = new HashSet<>();

			// The candidates to try
			// (1 is not a prime number by definition!)
			outer: for (int i = minPrimeTry; i <= maxPrimeTry; i++) {
				// Only need to try up to sqrt(i) - see notes
				int maxJ = (int) Math.sqrt(i);

				// Our divisor candidates
				for (int j = 2; j <= maxJ; j++)
					// If we can divide exactly by j, i is not prime
					if (((i / j) * j) == i)
						continue outer;

				// If we got here, it's prime
				s.add(i);
			}

			return s;
		}

		@Override
		protected void compute() {

			// Small enough for us?
			if ((this.end - this.start) < ForkJoinPrimes.workSize)
				ForkJoinPrimes.resultsQueue
					.offer(new Results(this.start, this.end, this.findPrimes(this.start, this.end)));
			else {
				// Divide into two pieces
				int mid = (this.start + this.end) / 2;

				ForkJoinTask.invokeAll(new FindPrimes(this.start, mid), new FindPrimes(mid + 1, this.end));
			}
		}
	}

	public static void main(String args[]) {

		int maxPrimeTry = 9999999;
		int maxWorkDivisor = 8;

		ForkJoinPrimes.workSize = (maxPrimeTry + 1) / maxWorkDivisor;

		ForkJoinPool pool = new ForkJoinPool();

		ForkJoinPrimes.resultsQueue = new ConcurrentLinkedQueue<>();

		long startTime = System.currentTimeMillis();

		pool.invoke(new FindPrimes(2, maxPrimeTry));

		long timeTaken = System.currentTimeMillis() - startTime;

		System.out.println("Number of tasks executed: " + ForkJoinPrimes.resultsQueue.size());

		while (ForkJoinPrimes.resultsQueue.size() > 0) {
			Results results = ForkJoinPrimes.resultsQueue.poll();

			Set<Integer> s = results.resultSet;

			s.stream()
				.sorted()
				.forEach(System.out::println);
		}

		System.out.println("Time taken: " + timeTaken);
	}
}
