package ss19.ue10.demo.pizza.abstractFactory;

import ss19.ue10.demo.pizza.dishes.ItalianPizza;
import ss19.ue10.demo.pizza.dishes.Pizza;
import ss19.ue10.demo.pizza.dishes.Spaghetti;

public class ItalianStyleFactory extends AbstractStyleFactory {

    @Override
    public Pizza createPizza() {
        return new ItalianPizza();
    }

    @Override
    public Spaghetti createSpaghetti() {
        return new Spaghetti("italian");
    }
}