package ue03;

public interface Priced {

    // Liefert den Preis (in Einheit „Geld“) des jeweiligen Produktes.
    abstract float getPrice();

    // Liefert den Rabattprozentsatz des jeweiligen Produktes, abhängig von der übergebenen Rabattrate
    abstract float getDiscountPercent(DiscountRate rate);


    // Liefert den Standard-Rabattprozentsatz. Diese Methode soll standardmäßig den Rabattprozentsatz
    // liefern, der mit der Rabattrate DiscountRate.LOW erzielt wird.
    default float getDiscountPercent() {
        return DiscountRate.LOW;
    }

    // Liefert den Rabatt (in Einheit „Geld“), also jene Menge an Geld, die gespart wird, wenn der
    // Rabattprozentsatz der angegebenen Rabattrate angewendet wird

    default float getDiscount(DiscountRate rate);

    // Liefert den Standardrabatt (in Einheit „Geld“), also jene Menge an Geld, die gespart wird, wenn der
    // Standard-Rabattprozentsatz angewendet wird
    default float getDiscount();

    // Liefert den verbilligten Preis, also jene Menge an Geld, die bezahlt werden muss, wenn vom
    // Originalpreis der Rabatt entsprechend der angegebenen Rabattrate abgezogen wird.
    default getReducedPrice(DiscountRate rate);

    // Liefert den verbilligten Preis, also jene Menge an Geld, die bezahlt werden muss, wenn vom
    // Originalpreis der Standardrabatt abgezogen wird
    default getReducedPrice();


}
