package com.codigo.aplios.toolbox.core.data.collect;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

// http://blog.radoszewski.pl/programming/java/2015/07/31/custom-java-8-collectors.html

public class MostPopular<T> implements Collector<T, Map<T, Integer>, Optional<T>> {

	@Override
	public Supplier<Map<T, Integer>> supplier() {

		return HashMap::new;
	}

	@Override
	public BiConsumer<Map<T, Integer>, T> accumulator() {

		return (acc, elem) -> acc.merge(elem, 1, (old, v) -> old + v);
	}

	@Override
	public BinaryOperator<Map<T, Integer>> combiner() {

		return (acc1, acc2) -> {
			throw new UnsupportedOperationException();
		};
	}

	@Override
	public Function<Map<T, Integer>, Optional<T>> finisher() {

		return (acc) -> acc.entrySet()
			.stream()
			.reduce((a, b) -> a.getValue() > b.getValue() ? a
					: b)
			.map(Map.Entry::getKey);
	}

	@Override
	public Set<Characteristics> characteristics() {

		return Collections.emptySet();
	}

	public static void main(String[] args) {

		List<Integer> integers = Arrays.asList(1,
				1,
				2,
				2,
				2,
				3,
				4,
				5,
				5,
				5,
				5,
				5,
				5,
				5,
				34,
				34,
				34,
				324,
				4,
				4,
				422,
				121,
				23,
				1,
				1,
				1,
				1,
				1,
				1,
				1,
				1,
				1);
		List<Character> characters = Arrays.asList('a',
				'b',
				'c',
				'c',
				'c',
				'd',
				'a',
				'b',
				'c',
				'c',
				'c',
				'd',
				'a',
				'b',
				'c',
				'c',
				'c',
				'd',
				'a',
				'b',
				'c',
				'c',
				'c',
				'd');

		MostPopular.showMostPopular(integers);
		MostPopular.showMostPopular(characters);
	}

	private static <T> void showMostPopular(List<T> list) {

		Optional<T> o = list.stream()
			.collect(new MostPopular<>());

		// System.out.println("Most popular element in ["+ StringUtils.join(list, ",")+ "]: ");
		o.ifPresent(System.out::println);
	}

}
