package ss20.ue03;

public class App {

    public static void main(String[] args) {

        // Herbs
        Item dill = new Herb("Dill", 18, 7);
        Item chives = new Herb("Schnittlauch", 10, 5);
        Item curry = new Herb("Curry", 5, 3);

        // AnimalProducts
        Item spiderLeg = new AnimalProduct("Spinnenbein");
        Item worm = new AnimalProduct("Wurm");
        Item egg = new AnimalProduct("Ei");

        // Potions (consists of the herbs and animalproducts from above and a potion)
        Item anyPotion = new Potion("EinTrank", curry, worm, egg);
        Item pukePotion = new Potion("Kotztrank", dill, chives, curry, spiderLeg, worm, egg, anyPotion);

        // Test outputs
        System.out.println(dill.toString());
        System.out.println("Price: " + dill.getPrice());
        System.out.println("ReducedPrice (HIGH): " + dill.getReducedPrice(DiscountRate.HIGH));
        System.out.println("ReducedPrice: " + dill.getReducedPrice());
        System.out.println("Power: " + dill.getPower());
        System.out.println("CoolDown: " + dill.getCoolDown());
        System.out.println();
        System.out.println(pukePotion.toString());
        System.out.println("Price: " + pukePotion.getPrice());
        System.out.println("ReducedPrice (HIGH): " + pukePotion.getReducedPrice(DiscountRate.HIGH));
        System.out.println("ReducedPrice: " + pukePotion.getReducedPrice());
        System.out.println("Power: " + pukePotion.getPower());
        System.out.println("CoolDown: " + pukePotion.getCoolDown());
    }
}