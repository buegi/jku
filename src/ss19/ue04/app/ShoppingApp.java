package ss19.ue04.app;

import ss19.ue04.item.Drink;
import ss19.ue04.item.Food;
import ss19.ue04.item.Item;
import ss19.ue04.list.LinkedList;

public class ShoppingApp {
    public static void main(String[] args) {
        Item bread = new Food("Brot", 2.39, 0.5);

        LinkedList<Item> itemList = new LinkedList<Item>();
        itemList.add(new Drink("Fanta", 1.39, 1.00));
        itemList.add(new Drink("Cola", 1.49, 1.00));
        itemList.add(new Drink("Mineralwasser", 0.79, 0.5));
        itemList.add(new Drink("Mineralwasser", 0.79, 0.5));
        itemList.add(new Drink("Mineralwasser", 0.79, 0.5));
        itemList.add(bread);
        itemList.add(new Food("Schinken", 17.49, 1.0));
        itemList.add(new Food("Schokolade", 1.00, 0.125));

        System.out.println("--- Complete List ---");
        print(itemList);

        System.out.println();
        System.out.println("--- index/contains (as usual first element (head) is index 0 ---");
        System.out.println("Index of bread: " + itemList.indexOf(bread));
        System.out.println("Index 5: " + itemList.get(5));
        System.out.println("Contains bread: " + itemList.contains(bread));
        System.out.println();
        System.out.println("--- remove ---");
        System.out.println("Remove bread: " + itemList.remove(bread)); // remove bread
        System.out.println("Remove index 0: " + itemList.remove(0)); // remove Index 0 / Position 0 / head
        System.out.println();
        System.out.println("--- List after removes ---");
        print(itemList);
        System.out.println();
        System.out.println("--- below ---");
        print(itemList.below(new Drink("Apfelsaft", 1.0, 1.0)));
        System.out.println();
        System.out.println("--- above ---");
        print(itemList.above(new Drink("Apfelsaft", 1.0, 1.0)));
        System.out.println();
        System.out.println("--- removeLast of itemList ---");
        System.out.println(itemList.removeLast());
        System.out.println();
        System.out.println("--- List after all operations ---");
        print(itemList);
    }

    private static <T extends Comparable<T>> void print(LinkedList<T> list) {
        for (T element : list) {
            System.out.println(element.toString());
        }
        System.out.println("Actual number of elements in list: " + list.size());
    }
}