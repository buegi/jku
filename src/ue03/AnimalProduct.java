package ue03;

public class AnimalProduct extends Resource {

    private static final int COOLDOWN = 10;

    public AnimalProduct(String name) {
        super(name);
    }

    @Override
    public float getPower() {
        return (this.getPrice() * 2);
    }

    public float getCoolDown() {
        return this.COOLDOWN;
    }

    @Override
    public String toString() {
        return "AnimalProduct: " + super.toString();
    }

    @Override
    public float getDiscountPercent(DiscountRate rate) {
        return super.getDiscount(DiscountRate.LOW);
    }
}