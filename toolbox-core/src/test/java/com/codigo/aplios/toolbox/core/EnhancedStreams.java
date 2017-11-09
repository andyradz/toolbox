package com.codigo.aplios.toolbox.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class EnhancedStreams {

	public static void main(String... args) {

		String[] arr = { "A", "B", "C", "D" };
		// ForwardingStream<String> sd = Stream.of(arr).collect(Collectors.toList());

		final EnhancedList<Boolean> bools = () -> Arrays.asList(true, false, false, true);
		bools.stream()
			.select(Boolean.class)
			.apply(true)
			.forEach(System.out::println);
		// bools.stream().sequential().forEach(System.out::println);

		final EnhancedList<String> input = () -> Arrays.asList("foo", "bar");
		List<String> result1 = input.stream()
			.filter(s -> s.startsWith("f"))
			.flatMapCollection(EnhancedStreams::threeOf)
			.toList();
		List<String> result2 = input.stream()
			.filter(s -> s.startsWith("b"))
			.flatMapCollection(EnhancedStreams::threeOf)
			.toList();

		result1.forEach(System.out::println);
		result2.forEach(System.out::println);
	}

	public static List<String> threeOf(String input) {

		return Arrays.asList(input, input, input);
	}

	interface EnhancedList<T> extends ForwardingList<T> {
		@Override
		default EnhancedStream<T> stream() {

			return () -> ForwardingList.super.stream();
		}
	}

	interface EnhancedStream<T> extends ForwardingStream<T> {
		default List<T> toList() {

			return this.collect(Collectors.toList());
		}

		default <R> EnhancedStream<R> flatMapCollection(Function<? super T, ? extends Collection<? extends R>> mapper) {

			return this.flatMap(mapper.andThen(Collection::stream));
		}

		@Override
		default EnhancedStream<T> filter(Predicate<? super T> predicate) {

			return () -> ForwardingStream.super.filter(predicate);
		}

		@Override
		default <R> EnhancedStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {

			return () -> ForwardingStream.super.flatMap(mapper);
		}

		@Override
		default EnhancedStream<T> sequential() {

			return () -> ForwardingStream.super.sequential();
		}

		@Override
		default EnhancedStream<T> parallel() {

			return () -> ForwardingStream.super.parallel();
		}

		@Override
		default EnhancedStream<T> skip(long n) {

			return () -> ForwardingStream.super.skip(n);
		}

		@Override
		default EnhancedStream<T> sorted(Comparator<? super T> comparator) {

			return () -> ForwardingStream.super.sorted(comparator);
		}

		@Override
		default EnhancedStream<T> sorted() {

			return () -> ForwardingStream.super.sorted();
		}

		@Override
		default <R> EnhancedStream<R> map(Function<? super T, ? extends R> mapper) {

			return () -> ForwardingStream.super.map(mapper);
		}

		@Override
		default EnhancedStream<T> peek(Consumer<? super T> action) {

			return () -> ForwardingStream.super.peek(action);
		}

		@Override
		default EnhancedStream<T> distinct() {

			return () -> ForwardingStream.super.distinct();
		}

		@Override
		default EnhancedStream<T> unordered() {

			return () -> ForwardingStream.super.unordered();
		}

		@Override
		default EnhancedStream<T> onClose(Runnable closeHandler) {

			return () -> ForwardingStream.super.onClose(closeHandler);
		}

		@Override
		default EnhancedStream<T> limit(long maxSize) {

			return () -> ForwardingStream.super.limit(maxSize);
		}
	}

	interface ForwardingStream<T> extends Stream<T> {
		Stream<T> delegate();

		default <R> Function<T, Stream<R>> select(Class<R> clazz) {

			return e -> clazz.isInstance(e) ? Stream.of(clazz.cast(e))
					: null;
		}

		default Stream<T> where(Predicate<? super T> predicate) {

			return this.delegate()
				.filter(predicate);
		}

		@Override
		default Stream<T> filter(Predicate<? super T> predicate) {

			return this.delegate()
				.filter(predicate);
		}

		@Override
		default Object[] toArray() {

			return this.delegate()
				.toArray();
		}

		@Override
		default boolean isParallel() {

			return this.delegate()
				.isParallel();
		}

		@Override
		default boolean allMatch(Predicate<? super T> predicate) {

			return this.delegate()
				.allMatch(predicate);
		}

		@Override
		default IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {

			return this.delegate()
				.flatMapToInt(mapper);
		}

		@Override
		default Iterator<T> iterator() {

			return this.delegate()
				.iterator();
		}

		@Override
		default IntStream mapToInt(ToIntFunction<? super T> mapper) {

			return this.delegate()
				.mapToInt(mapper);
		}

		@Override
		default <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {

			return this.delegate()
				.flatMap(mapper);
		}

		@Override
		default void forEach(Consumer<? super T> action) {

			this.delegate()
				.forEach(action);
		}

		@Override
		default DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {

			return this.delegate()
				.flatMapToDouble(mapper);
		}

		@Override
		default Stream<T> sequential() {

			return this.delegate()
				.sequential();
		}

		@Override
		default void close() {

			this.delegate()
				.close();
		}

		@Override
		default Stream<T> parallel() {

			return this.delegate()
				.parallel();
		}

		@Override
		default LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {

			return this.delegate()
				.flatMapToLong(mapper);
		}

		@Override
		default Stream<T> skip(long n) {

			return this.delegate()
				.skip(n);
		}

		@Override
		default <A> A[] toArray(IntFunction<A[]> generator) {

			return this.delegate()
				.toArray(generator);
		}

		@Override
		default Optional<T> max(Comparator<? super T> comparator) {

			return this.delegate()
				.max(comparator);
		}

		@Override
		default Stream<T> sorted(Comparator<? super T> comparator) {

			return this.delegate()
				.sorted(comparator);
		}

		@Override
		default LongStream mapToLong(ToLongFunction<? super T> mapper) {

			return this.delegate()
				.mapToLong(mapper);
		}

		@Override
		default Spliterator<T> spliterator() {

			return this.delegate()
				.spliterator();
		}

		@Override
		default Optional<T> findAny() {

			return this.delegate()
				.findAny();
		}

		@Override
		default <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {

			return this.delegate()
				.reduce(identity, accumulator, combiner);
		}

		@Override
		default long count() {

			return this.delegate()
				.count();
		}

		@Override
		default Optional<T> reduce(BinaryOperator<T> accumulator) {

			return this.delegate()
				.reduce(accumulator);
		}

		@Override
		default <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {

			return this.delegate()
				.collect(supplier, accumulator, combiner);
		}

		@Override
		default <R, A> R collect(Collector<? super T, A, R> collector) {

			return this.delegate()
				.collect(collector);
		}

		@Override
		default Stream<T> sorted() {

			return this.delegate()
				.sorted();
		}

		@Override
		default <R> Stream<R> map(Function<? super T, ? extends R> mapper) {

			return this.delegate()
				.map(mapper);
		}

		@Override
		default Stream<T> peek(Consumer<? super T> action) {

			return this.delegate()
				.peek(action);
		}

		@Override
		default Stream<T> distinct() {

			return this.delegate()
				.distinct();
		}

		@Override
		default boolean anyMatch(Predicate<? super T> predicate) {

			return this.delegate()
				.anyMatch(predicate);
		}

		@Override
		default Stream<T> unordered() {

			return this.delegate()
				.unordered();
		}

		@Override
		default Optional<T> min(Comparator<? super T> comparator) {

			return this.delegate()
				.min(comparator);
		}

		@Override
		default Stream<T> onClose(Runnable closeHandler) {

			return this.delegate()
				.onClose(closeHandler);
		}

		@Override
		default void forEachOrdered(Consumer<? super T> action) {

			this.delegate()
				.forEachOrdered(action);
		}

		@Override
		default DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {

			return this.delegate()
				.mapToDouble(mapper);
		}

		@Override
		default boolean noneMatch(Predicate<? super T> predicate) {

			return this.delegate()
				.noneMatch(predicate);
		}

		@Override
		default Stream<T> limit(long maxSize) {

			return this.delegate()
				.limit(maxSize);
		}

		@Override
		default T reduce(T identity, BinaryOperator<T> accumulator) {

			return this.delegate()
				.reduce(identity, accumulator);
		}

		@Override
		default Optional<T> findFirst() {

			return this.delegate()
				.findFirst();
		}
	}

	interface ForwardingList<T> extends List<T> {
		List<T> delegate();

		@Override
		default int size() {

			return this.delegate()
				.size();
		}

		@Override
		default T get(int index) {

			return this.delegate()
				.get(index);
		}

		@Override
		default boolean contains(Object o) {

			return this.delegate()
				.contains(o);
		}

		@Override
		default ListIterator<T> listIterator() {

			return this.delegate()
				.listIterator();
		}

		@Override
		default <T1> T1[] toArray(T1[] a) {

			return this.delegate()
				.toArray(a);
		}

		@Override
		default Spliterator<T> spliterator() {

			return this.delegate()
				.spliterator();
		}

		@Override
		default boolean removeIf(Predicate<? super T> filter) {

			return this.delegate()
				.removeIf(filter);
		}

		@Override
		default boolean removeAll(Collection<?> c) {

			return this.delegate()
				.removeAll(c);
		}

		@Override
		default boolean remove(Object o) {

			return this.delegate()
				.remove(o);
		}

		@Override
		default T remove(int index) {

			return this.delegate()
				.remove(index);
		}

		@Override
		default int indexOf(Object o) {

			return this.delegate()
				.indexOf(o);
		}

		@Override
		default Iterator<T> iterator() {

			return this.delegate()
				.iterator();
		}

		@Override
		default void forEach(Consumer<? super T> action) {

			this.delegate()
				.forEach(action);
		}

		@Override
		default int lastIndexOf(Object o) {

			return this.delegate()
				.lastIndexOf(o);
		}

		@Override
		default T set(int index, T element) {

			return this.delegate()
				.set(index, element);
		}

		@Override
		default boolean addAll(Collection<? extends T> c) {

			return this.delegate()
				.addAll(c);
		}

		@Override
		default Object[] toArray() {

			return this.delegate()
				.toArray();
		}

		@Override
		default void replaceAll(UnaryOperator<T> operator) {

			this.delegate()
				.replaceAll(operator);
		}

		@Override
		default boolean containsAll(Collection<?> c) {

			return this.delegate()
				.containsAll(c);
		}

		@Override
		default List<T> subList(int fromIndex, int toIndex) {

			return this.delegate()
				.subList(fromIndex, toIndex);
		}

		@Override
		default void add(int index, T element) {

			this.delegate()
				.add(index, element);
		}

		@Override
		default boolean add(T t) {

			return this.delegate()
				.add(t);
		}

		@Override
		default void sort(Comparator<? super T> c) {

			this.delegate()
				.sort(c);
		}

		@Override
		default void clear() {

			this.delegate()
				.clear();
		}

		@Override
		default boolean addAll(int index, Collection<? extends T> c) {

			return this.delegate()
				.addAll(index, c);
		}

		@Override
		default boolean isEmpty() {

			return this.delegate()
				.isEmpty();
		}

		@Override
		default boolean retainAll(Collection<?> c) {

			return this.delegate()
				.retainAll(c);
		}

		@Override
		default Stream<T> stream() {

			return this.delegate()
				.stream();
		}

		@Override
		default Stream<T> parallelStream() {

			return this.delegate()
				.parallelStream();
		}

		@Override
		default ListIterator<T> listIterator(int index) {

			return this.delegate()
				.listIterator(index);
		}
	}
}