package com.codigo.aplios.toolbox.core.data.sorting;

@FunctionalInterface
public interface Swapable<T> {
	void swap(T[] items, int sourcePos, int targetPos);
}