package swe2.ss20.ue03;

public interface Priced {

    // Liefert den Preis (in Einheit „Geld“) des jeweiligen Produktes.
    abstract float getPrice();

    // Liefert den Rabattprozentsatz des jeweiligen Produktes, abhängig von der übergebenen Rabattrate
    abstract float getDiscountPercent(DiscountRate rate);

    // DEFAULTS

    // Liefert den Standard-Rabattprozentsatz. Diese Methode soll standardmäßig den Rabattprozentsatz
    // liefern, der mit der Rabattrate DiscountRate.LOW erzielt wird.
    default public float getDiscountPercent() {
        return this.getDiscountPercent(DiscountRate.LOW);
    }

    // Liefert den Rabatt (in Einheit „Geld“), also jene Menge an Geld, die gespart wird, wenn der
    // Rabattprozentsatz der angegebenen Rabattrate angewendet wird
    default public float getDiscount(DiscountRate rate) {
        return this.getDiscountPercent(rate) * this.getPrice();
    }

    // Liefert den Standardrabatt (in Einheit „Geld“), also jene Menge an Geld, die gespart wird, wenn der
    // Standard-Rabattprozentsatz angewendet wird
    default public float getDiscount() {
        return this.getDiscountPercent() * this.getPrice();
    }

    // Liefert den verbilligten Preis, also jene Menge an Geld, die bezahlt werden muss, wenn vom
    // Originalpreis der Rabatt entsprechend der angegebenen Rabattrate abgezogen wird.
    default public float getReducedPrice(DiscountRate rate) {
        return this.getPrice() - (this.getDiscountPercent(rate) * this.getPrice());
    }

    // Liefert den verbilligten Preis, also jene Menge an Geld, die bezahlt werden muss, wenn vom
    // Originalpreis der Standardrabatt abgezogen wird
    default public float getReducedPrice() {
        return this.getPrice() - (this.getPrice() * this.getDiscountPercent());
    }
}