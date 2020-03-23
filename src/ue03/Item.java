package ue03;

public abstract class Item implements Priced {

    private String name;
    private int power;
    private int coolDown;


    // Implementiert das Interface Priced.
    // Überschreiben Sie getDiscountPercent(DiscountRate rate) so, dass bei einer Rabattrate
    // von LOW 1% Rabatt, bei MEDIUM 5% Rabatt und bei HIGH 15% Rabatt zurückgegeben wird.
    // Überschreiben sie getPrice() nicht, dies wird von den Unterklassen implementiert.


    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                '}';
    }
}
