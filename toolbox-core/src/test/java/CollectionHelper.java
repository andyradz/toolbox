import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// https://docs.oracle.com/javase/tutorial/extra/generics/morefun.html

interface Addable<E> {
	void add(E item);
}

class AddableCollection<E> implements Addable<E> {
	private Collection<E> c;

	public AddableCollection(Collection<E> c) {

		this.c = c;
	}

	@Override
	public void add(E item) {

		this.c.add(item);
	}
}

class SomeContainer<T> implements Iterable<T> {
	private ArrayList<T> storage = new ArrayList<>();

	public void addElement(T item) {

		this.storage.add(item);
	} // tutaj "addElement"

	@Override
	public Iterator<T> iterator() {

		return this.storage.iterator();
	}
}

// Nie warto dla każdego takiego przypadku tworzyć (wyraźnie)
// oddzielnej klasy poprzez dziedziczenie czy też kompozycję
// B. Eckel proponuje "abstract composition adapter"

abstract class AddableAdapter<S, T> implements Addable<T> {
	private S seq;

	public AddableAdapter(S sequence) {

		this.seq = sequence;
	}

	public S getSequence() {

		return this.seq;
	}

	@Override
	public abstract void add(T item);
}

class Zwierz {
}

class Pies extends Zwierz {
}

class Kot extends Zwierz {
}

public class CollectionHelper {

	public static void main(String[] args) {

		// Adaptacja kolekcji:
		List<String> words = new ArrayList<>();
		CollectionHelper.fill(new AddableCollection<>(words), String.class, "Ala", "ma", "kota");
		for (String w : words)
			System.out.println(w);
		System.out.println("----------------------");
		List<Integer> ints = new ArrayList<>();
		CollectionHelper.fill(new AddableCollection<>(ints), Integer.class, "1", "2", "33");
		for (int i : ints)
			System.out.println(i);
		System.out.println("----------------------");

		// Użycie abstrakcyjnego adaptera
		SomeContainer<Zwierz> con = new SomeContainer<>();

		// I tu uwaga: generics pięknie zadziałały na klasie wewn.
		// Naprawdę łatwo jest ad hoc tworzyć konkretne adaptery
		// (kompilator wie jakiego typu jest wynik getSequence()
		// i bez castingu możemy wołać metody!

		Addable<Zwierz> zwierzeta = new AddableAdapter<SomeContainer<Zwierz>, Zwierz>(con) {
			@Override
			public void add(Zwierz item) {

				this.getSequence()
					.addElement(item);
			}
		};

		CollectionHelper.fill(zwierzeta, Pies.class, 7);
		CollectionHelper.fill(zwierzeta, Kot.class, 3);

		for (Zwierz z : con)
			System.out.println(z);
	}

	public static <E, V> void fill(Addable<E> cons, Class<? extends E> type, V... args) {

		for (V arg : args)
			try {
				final Constructor<?> ctor = type.getDeclaredConstructor(arg.getClass());
				cons.add((E) (ctor.newInstance(arg)));

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static <E, V> void fill(Addable<E> cons, Class<? extends E> type, int count) {

		for (int idx = 0; idx < count; idx++)
			try {
				cons.add(type.newInstance());
			} catch (Exception e) {

			}
	}

	public static <T extends Comparable<? super T>> T max(Collection<T> coll) {

		return null;
	}

	public static <T extends Object & Comparable<? super T>> T max1(Collection<T> coll) {

		return null;
	}

	public static void odd(Comparable<? super Addable<?>> dane) {

	}

}
