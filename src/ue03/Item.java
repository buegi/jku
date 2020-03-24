package ue03;

public abstract class Item implements Priced {

    private final String name;
    protected float power;
    protected float coolDown;

    public Item(String name) {
        this.name = name;
    }

    // Implementiert das Interface Priced.
    // Überschreiben Sie getDiscountPercent(DiscountRate rate) so, dass bei einer Rabattrate
    // von LOW 1% Rabatt, bei MEDIUM 5% Rabatt und bei HIGH 15% Rabatt zurückgegeben wird.
    // Überschreiben sie getPrice() nicht, dies wird von den Unterklassen implementiert.

    @Override
    public float getDiscountPercent(DiscountRate rate) {
        switch (rate) {
            case LOW:
                return 0.01f;

            case MEDIUM:
                return 0.05f;

            case HIGH:
                return 0.15f;

            default:
                return 0.0f;
        }
    }

    public String getName() {
        return this.name;
    }

    public abstract float getPower();

    public abstract float getCoolDown();

    @Override
    public String toString() {
        return this.name;
    }
}