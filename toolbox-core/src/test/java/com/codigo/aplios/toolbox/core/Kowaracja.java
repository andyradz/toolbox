package com.codigo.aplios.toolbox.core;

import java.util.ArrayList;
import java.util.List;

public class Kowaracja {

	static class TypDanych {

		public TypDanych() {

			super();
		}

		public int getId1() {

			return this.id1;
		}

		public void setId1(int id1) {

			this.id1 = id1;
		}

		public int getId2() {

			return this.id2;
		}

		public void setId2(int id2) {

			this.id2 = id2;
		}

		/**
		 * @param id1
		 * @param id2
		 */
		public TypDanych(int id1, int id2) {

			super();
			this.id1 = id1;
			this.id2 = id2;
		}

		private int id1;

		private int id2;

	}

	public static void main(String[] args) {

		// kowaracja
		Kowaracja.test1();
	}

	private static void log1(List<? extends Number> data) {

		data.forEach(System.out::println);
	}

	private static void log2(List<? super Integer> data) {

		data.add(10000);
		data.forEach(System.out::println);
	}

	private static void log3(List<?> data) {

		data.forEach(System.out::println);
	}

	private static <T> void test1() {

		// nowa klasa
		List<? super Integer> ints1 = new ArrayList<Number>() {
			{
				this.add(213);
			}

		};
		Kowaracja.log2(ints1);

		// kontrwariacja
		List<? super Integer> ints = new ArrayList<Number>() {
			{
				this.add(12);
				this.add(11);
			}

			;
		};
		Kowaracja.log2(ints);
		ints = new ArrayList<Integer>() {
			{
				this.add(12);
				this.add(11);
			}

			;
		};

		Kowaracja.log2(ints);

		// kowariacja
		List<? extends Number> nums = new ArrayList<Integer>() {
			{
				this.add(1);
				this.add(2);
				this.add(3);
				this.add(4);
			}

		};
		Kowaracja.log1(nums);

		nums = new ArrayList<Number>() {
			{
				this.add(1);
				this.add(2);
				this.add(3);
				this.add(4);
			}

		};
		Kowaracja.log1(nums);

		nums = new ArrayList<Byte>() {
			{
				this.add((byte) 12);
				this.add((byte) 11);
			}

			;
		};
		Kowaracja.log1(nums);

		// Biwariacja
		List<?> binums = new ArrayList<Integer>() {
			{
				this.add(223);
			}

		};

		binums = new ArrayList<Byte>() {
			{
				this.add((byte) 3);
			}

		};

		binums = new ArrayList<Number>() {
			{
				this.add((byte) 3);
			}

		};

		binums = nums;
		binums = ints;

		binums = new ArrayList<String>() {
			{
				this.add("Andrzej");
			}

		};

		Kowaracja.log3(binums);

		binums.forEach(System.out::println);
		// Ale jeśli mamy gdzieś dostęp do typu sparametryzowanego <? extends X>, to zabronione jest
		// podstawianie na ten typ konkretniejszych podtypów.
		// Inaczej mielibyśmy sytuację, w której do przekazanej listy dyrektorów dopisywani mogliby
		// być np. asystenci.
		// Nie możemy "podstawiać", ale możemy pobierać (dostajemy coś calkiem bezpiecznego typu -
		// np. typu wyznaczanego przez dolną granicę).
		List<? extends T> sup;
	}

	@Override
	public int hashCode() {

		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {

		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {

		// TODO Auto-generated method stub
		super.finalize();
	}

}
