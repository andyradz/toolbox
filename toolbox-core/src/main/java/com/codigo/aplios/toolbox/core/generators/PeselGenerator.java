package com.codigo.aplios.toolbox.core.generators;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PeselGenerator implements Iterator<String> {

	private static final byte limit;
	private int count = 0;
	private static final Random random;

	static {
		limit = 127;
		random = new Random(System.nanoTime());
	}

	@Override
	public boolean hasNext() {

		return (this.count <= PeselGenerator.limit);
	}

	@Override
	public String next() {

		this.count = Math.abs(((PeselGenerator.random.nextInt()) % 100) + 2);
		return String.format("%03d", this.count);
	}

	public static void main(String[] args) {

		// PeselGenerator pes = new PeselGenerator();
		// while (pes.hasNext())
		// System.out.println(pes.next());
		Stream<String> stream = StreamSupport.stream(new PeselIterable().spliterator(), true);
		stream.map(i -> "You threw a " + i)
			.forEach(System.out::println);
	}
}

final class PeselIterable implements Iterable<String> {

	@Override
	public Iterator<String> iterator() {

		return new PeselGenerator();
	}
}
