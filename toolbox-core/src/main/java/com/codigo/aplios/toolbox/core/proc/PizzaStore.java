package com.codigo.aplios.toolbox.core.proc;

import java.io.IOException;

class PizzaStore {

	private MealFactory factory = new MealFactory();

	public Meal order(String mealName) {

		return this.factory.create(mealName);
	}

	// public Meal order(String mealName) {
	//
	// if (mealName == null) {
	// throw new IllegalArgumentException("Name of the meal is null!");
	// }
	//
	// if ("Margherita".equals(mealName)) {
	// return new MargheritaPizza();
	// }
	//
	// if ("Calzone".equals(mealName)) {
	// return new CalzonePizza();
	// }
	//
	// if ("Tiramisu".equals(mealName)) {
	// return new Tiramisu();
	// }
	//
	// throw new IllegalArgumentException("Unknown meal '" + mealName + "'");
	// }

	public static void main(String[] args) throws IOException {

		PizzaStore pizzaStore = new PizzaStore();
		Meal meal = pizzaStore.order("CalzonePizza");
		System.out.println("Bill: $" + meal.getPrice());
	}
}
