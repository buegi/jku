package ue03;

public class Potion extends Item {

    private final Item ingredients[];

    public Potion(String name, Item... ingredients) {
        super(name);
        this.ingredients = ingredients;
    }


    @Override
    public float getPower() {
        float sumPower = 0;
        for (Item i : ingredients) {
            sumPower = sumPower + i.getPower();
        }
        return (sumPower * 2);
    }

    @Override
    public float getCoolDown() {
        float max = 0;
        for (Item i : ingredients) {
            if (i.getCoolDown() > max) {
                max = i.getCoolDown();
            }
        }
        return (max * 2);
    }

    @Override
    public float getPrice() {
        float price = 0;
        for (Item i : ingredients) {
            price = price + i.getPrice();
        }
        return price * 1.2f;
    }

    @Override
    public float getDiscountPercent(DiscountRate rate) {
        return 0;
    }
}