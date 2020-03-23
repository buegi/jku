package ue03;

public class Example {

    public static void main(String[] args) {
        Item chives = new Herb("Schnittlauch", 10, 5);
        Item spiderLeg = new AnimalProduct("Spinnenbein");
        Item darkpotion = new Potion("Dunkeltrank", chives, spiderLeg);
        Item nightpotion = new Potion(
                "Nachttrank",
                darkpotion,
                spiderLeg);
        System.out.println(nightpotion.toString());
        System.out.println(nightpotion.getPrice());
        System.out.println(nightpotion.getReducedPrice(DiscountRate.HIGH));
        System.out.println(nightpotion.getReducedPrice());
        System.out.println(nightpotion.getPower());
        System.out.println(nightpotion.getCoolDown());
    }
}