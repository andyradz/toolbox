package com.codigo.aplios.toolbox.core.numbers;

import java.util.HashSet;
import java.util.Set;

public class ForLoopPrimes {
	public static Set<Integer> findPrimes(int maxPrimeTry) {

		Set<Integer> s = new HashSet<>();

		// The candidates to try (1 is not a prime number by definition!)
		outer: for (int i = 2; i <= maxPrimeTry; i++) {
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

	public static void main(String args[]) {

		int maxPrimeTry = 9999999;

		long startTime = System.currentTimeMillis();

		Set<Integer> s = ForLoopPrimes.findPrimes(maxPrimeTry);

		long timeTaken = System.currentTimeMillis() - startTime;

		s.stream()
			.sorted()
			.forEach(System.out::println);

		System.out.println("Time taken: " + timeTaken);
	}
}