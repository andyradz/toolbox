package com.codigo.aplios.toolbox.core.identity.executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Consumer;

class Sumowanie extends RecursiveTask<Long> {

	public static void main(String[] args) {

		Consumer<Long> pp = Sumowanie::printNames;

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		long suma = forkJoinPool.invoke(new Sumowanie(1, 1000000));
		suma = forkJoinPool.invoke(new Sumowanie(-1, 2000000));
		pp.accept(suma);
		suma = forkJoinPool.invoke(new Sumowanie(-1, -1000000));
		pp.accept(suma);
		suma = forkJoinPool.invoke(new Sumowanie(-1343434, -1000000));
		pp.accept(suma);
		suma = forkJoinPool.invoke(new Sumowanie(2, 2));
		pp.accept(suma);
		suma = forkJoinPool.invoke(new Sumowanie(2, 20000));
		pp.accept(suma);
	}

	public static void printNames(Long name) {

		System.out.println(name);
	}

	private final long start, koniec;

	Sumowanie(long start, long koniec) {

		this.start = start;
		this.koniec = koniec;
	}

	@Override
	public Long compute() {

		long suma = 0;
		final long Porcja = 1024;
		if ((this.koniec - this.start) > Porcja) {
			long polowa = (this.koniec - this.start) / 2;
			Sumowanie s1 = new Sumowanie(this.start, this.start + polowa);
			s1.fork();
			Sumowanie s2 = new Sumowanie(this.start + polowa + 1, this.koniec);
			suma = s2.compute() + s1.join();
		} else
			suma = this.sumujBezposrednio();
		return suma;
	}

	// Małe sumy obliczaj sekwencyjnie
	public long sumujBezposrednio() {

		long suma = 0;
		for (long i = this.start; i <= this.koniec; ++i)
			suma += i;
		return suma;
	}
}
