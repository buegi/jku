package ss19.ue10.demo.pizza.abstractFactory;

import inout.In;
import inout.Out;
import pizza.dishes.Pizza;
import pizza.dishes.Spaghetti;

public class PizzaTest {
	
	public static void main(String[] args) {
		Out.println("Welcome to the SSW pizza store (implementation using abstract factory)");
		Out.println("Please select your preferred style:");
		Out.println("1) Italian style");
		Out.println("2) American style");

		AbstractStyleFactory pizzaFactory;
		
		switch (In.readInt()) {
		case 1:
			pizzaFactory = new ItalianStyleFactory();
			break;
		case 2:
			pizzaFactory = new AmericanStyleFactory();
			break;
		case 3: 
			pizzaFactory = new AustrianStyleFactory(); 
			break; 
		default:
			Out.println("invalid input");
			return;
		}
		
		PizzaStore pizzaStore = new PizzaStore(pizzaFactory);

		Pizza pizza = pizzaStore.orderPizza();
		Out.println("ready to eat " + pizza);
		Out.println();

		Spaghetti spaghetti = pizzaStore.orderSpaghetti();
		Out.println("ready to eat " + spaghetti);
		Out.println();
	}
}
