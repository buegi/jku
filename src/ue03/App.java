package ue03;

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

        // Potion (consists of the herbs and animalproducts from above)
        Item pukePotion = new Potion("Dunkeltrank", dill, chives, curry, spiderLeg, worm, egg);


        System.out.println(dill.toString());
        System.out.println("Price: " + dill.getPrice());
        System.out.println("ReducedPrice (HIGH): " + dill.getReducedPrice(DiscountRate.HIGH));
        System.out.println("ReducedPrice: " + dill.getReducedPrice());
        System.out.println("Power: " + dill.getPower());
        System.out.println("CoolDown: " + dill.getCoolDown());

    }
}
