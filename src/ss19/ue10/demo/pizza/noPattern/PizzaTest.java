package ss19.ue10.demo.pizza.noPattern;

import inout.In;
import inout.Out;
import pizza.dishes.Pizza;
import pizza.dishes.Spaghetti;

public class PizzaTest {
	public static void main(String[] args) {
		Out.println("Welcome to the SSW pizza store (implementation using no design pattern)");
		Out.println("Please select your preferred style:");
		Out.println("1) Italian style");
		Out.println("2) American style");

		String style;
		switch (In.readInt()) {
		case 1:
			style = "italian";
			break;
		case 2:
			style = "american";
			break;
		default:
			Out.println("invalid input");
			return;
		}
		PizzaStore pizzaStore = new PizzaStore(style);

		Pizza pizza = pizzaStore.orderPizza();
		Out.println("ready to eat " + pizza);
		Out.println();

		Spaghetti spaghetti = pizzaStore.orderSpaghetti();
		Out.println("ready to eat " + spaghetti);
		Out.println();
	}
}
