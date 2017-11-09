package com.codigo.aplios.toolbox.core.gui;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumTest {

	private enum Markers {
		A1,
		A2,
		A3,
		A4
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Stream.of(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
			.filter(item -> item.doubleValue() > 0)
			.peek(item -> System.out.println(item * 10))
			.peek(item -> System.out.println(item + 10))
			.collect(Collectors.toList());

		Optional<Enum<?>> enu = Optional.empty();
		Optional<Enum<?>> sc = Optional.of(Markers.A1);
		Optional<Enum<?>> nl = Optional.ofNullable(Markers.A1);
		System.out.println(enu);
		System.out.println(sc);
		System.out.println(nl);

	}

}
