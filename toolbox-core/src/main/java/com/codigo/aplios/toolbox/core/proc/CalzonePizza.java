package com.codigo.aplios.toolbox.core.proc;

@Factory(id = "Calzone", type = Meal.class)
class CalzonePizza implements Meal {

	@Override
	public float getPrice() {

		return 8.5f;
	}
}
