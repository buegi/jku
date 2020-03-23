package ue03;

public class Herb extends Resource {

    private final float power;
    private final float coolDown;

    public Herb(String name, float power, float coolDown) {
        super(name);
        this.power = power;
        this.coolDown = coolDown;
    }

    @Override
    public float getPower() {
        return this.power;
    }

    @Override
    public float getCoolDown() {
        return this.coolDown;
    }

    @Override
    public String toString() {
        return "Herb: " + super.toString();
    }

    @Override
    public float getDiscountPercent(DiscountRate rate) {
        return 0;
    }
}