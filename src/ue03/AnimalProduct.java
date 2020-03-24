package ue03;

public class AnimalProduct extends Resource {

    private static final int COOLDOWN = 10;

    public AnimalProduct(String name) {
        super(name);
    }

    @Override
    public float getPower() {
        return (this.getPrice() * 2f);
    }

    public float getCoolDown() {
        return COOLDOWN;
    }

    @Override
    public String toString() {
        return "AnimalProduct: " + super.toString();
    }
}