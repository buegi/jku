package swe2.ss20.ue03;

public class Herb extends Resource {

    private float power;
    private float coolDown;

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
}