package com.codigo.aplios.toolbox.core.data.repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.codigo.aplios.toolbox.core.domain.common.Identity;

// dodać runintran transaction gedzie bedziemy logowac operacje
// dodać state encji

enum EntityState {
	TRANSIENT,
	MANAGED,
	DETACHED,
	REMOVED;
}

public class MemoryRepository<T extends Identity> implements Repository<T> {

	private Set<T> datset;
	private int counterId;

	public MemoryRepository() {

		this.datset = new HashSet<>();
	}

	@Override
	public Set<T> get() {

		return this.datset;
	}

	@Override
	public void save(T entity) {

		entity.setId(++this.counterId);
		this.datset.add(entity);
	}

	@Override
	public void persist(Collection<T> entities) {

		entities.forEach(this::save);
	}

	@Override
	@SafeVarargs
	public final void persist(T... entities) {

		Stream.of(entities)
			.forEach(this::save);
	}

	@Override
	public void remove(Identity entity) {

		this.datset.remove(entity);
	}

	@Override
	public void remove(Integer key) {

		this.datset.removeIf(item -> item.getId()
			.equals(key));
	}

	@Override
	public void remove(Predicate<T> predicate) {

		this.remove(this.get(predicate));
	}

	@Override
	public void remove(Collection<T> entities) {

		this.datset.removeAll(entities);
	}

	@Override
	public long removeAll() {

		final long dataCount = this.datset.size();
		this.datset.clear();
		return dataCount;
	}

	@Override
	public boolean isAutoCommit() {

		return true;
	}

	@Override
	public void merge(T entity) {
		// if (this.datset.contains(entity))
		// this.datset.stream().filter(item->item.getId().equals(entity.getId())).forEach(e-> e.se);

	}

}
