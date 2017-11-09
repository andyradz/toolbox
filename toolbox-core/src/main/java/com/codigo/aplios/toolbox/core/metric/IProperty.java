package com.codigo.aplios.toolbox.core.metric;

/**
 *
 * @author andrzej.radziszewski
 */
public interface IProperty<T> {

	void set(T value);

	T get();
	// TODO: dodać sprawdzanie invaiants:
	// Walidacja danych
	// moze zrobić to za pomoca fluent walidatora
	// dodac readonly, writeonly wlasciowsc
}
