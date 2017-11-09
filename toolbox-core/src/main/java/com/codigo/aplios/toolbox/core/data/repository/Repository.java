package com.codigo.aplios.toolbox.core.data.repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.codigo.aplios.toolbox.core.domain.common.Identity;

public interface Repository<T extends Identity> {

	default Optional<T> get(Integer key) {

		return this.get()
			.stream()
			.filter(entity -> entity.getId()
				.equals(key))
			.findAny();
	}

	default Optional<T> get(int key) {

		return this.get()
			.stream()
			.filter(entity -> entity.getId()
				.equals(Integer.valueOf(key)))
			.findAny();
	}

	default Set<T> get(Predicate<T> predicate) {

		return this.get()
			.stream()
			.filter(predicate)
			.collect(Collectors.toSet());
	}

	Set<T> get();

	void save(T entity);

	default void persist(T... entities) {

		this.persist(Arrays.asList(entities));
	}

	default void persist(Collection<T> entities) {

		entities.forEach(this::save);
	}

	void merge(T entity);

	default void merge(T... entities) {

		this.update(Arrays.asList(entities));
	}

	default void update(Collection<T> entities) {

		entities.forEach(this::merge);
	}

	void remove(T entity);

	default void remove(Iterator<T> entities) {

		entities.forEachRemaining(this::remove);
	}

	default void remove(Integer keyId) {

		this.remove(entity -> entity.getId() == keyId);
	}

	default void remove(Predicate<T> predicate) {

		this.get()
			.forEach(this::remove);
	}

	default void remove(Collection<T> entities) {

		entities.forEach(this::remove);
	}

	default void delete(final T... entities) {

		this.remove(Arrays.asList(entities));
	}

	default long count() {

		return this.get()
			.stream()
			.count();
	}

	default Long countAsync() throws InterruptedException, ExecutionException {

		CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> this.get()
			.stream()
			.count());
		return completableFuture.get();
	}

	long removeAll();

	boolean isAutoCommit();

}
