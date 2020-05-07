package ss19.ue10.demo.pizza.prototype;

import inout.In;
import inout.Out;
import pizza.dishes.AmericanPizza;
import pizza.dishes.ItalianPizza;
import pizza.dishes.Pizza;
import pizza.dishes.Spaghetti;

public class PizzaTest {
	public static void main(String[] args) {
		
		Out.println("Welcome to the SSW pizza store (implementation using prototype)");
		Out.println("Please select your preferred style:");
		Out.println("1) Italian style");
		Out.println("2) American style");

		Pizza pizzaPrototype;
		Spaghetti spaghettiPrototype;
		
		switch (In.readInt()) {
		case 1:
			pizzaPrototype = new ItalianPizza();
			spaghettiPrototype = new Spaghetti("italian");
			break;
		case 2:
			pizzaPrototype = new AmericanPizza();
			spaghettiPrototype = new Spaghetti("american");
			break;
		default:
			Out.println("invalid input");
			return;
		}
		PizzaStore pizzaStore = new PizzaStore(pizzaPrototype, spaghettiPrototype);

		Pizza pizza = pizzaStore.orderPizza();
		Out.println("ready to eat " + pizza);
		Out.println();

		Spaghetti spaghetti = pizzaStore.orderSpaghetti();
		Out.println("ready to eat " + spaghetti);
		Out.println();
	}
}
