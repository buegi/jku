package ss20.ue03;

public abstract class Resource extends Item {
    public Resource(String name) {
        super(name);
    }

    public float getPrice() {
        return (float) this.getName().length();
    }
}