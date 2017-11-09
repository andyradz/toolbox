package com.codigo.aplios.toolbox.core.proc;

@Factory(id = "Margherita", type = Meal.class)
class MargheritaPizza implements Meal {

	@Override
	public float getPrice() {

		return 6.0f;
	}
}
