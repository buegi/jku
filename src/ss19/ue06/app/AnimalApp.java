package ss19.ue06.app;

import ss19.ue06.db.Animal;
import ss19.ue06.db.Database;
import ss19.ue06.db.Person;

import java.util.*;

public class AnimalApp {
    public static void main(String[] args) {
        Database db = new Database();
        db.addPerson("Alf");
        db.addPerson("Beate");
        db.addPerson("David");
        db.addPerson("Christine");
        db.addAnimal(db.getPerson("Alf"), 0, "Hansi", null, null);
        db.addAnimal(db.getPerson("Beate"), 1, "Mausi", null, null);
        db.addAnimal(db.getPerson("David"), 2, "Fritzi", db.getAnimal(0), db.getAnimal(1));
        db.addAnimal(db.getPerson("David"), 3, "Frauli", db.getAnimal(0), db.getAnimal(1));
        db.addAnimal(db.getPerson("Christine"), 4, "Fratzi", db.getAnimal(0), db.getAnimal(1));
        db.addAnimal(db.getPerson("Christine"), 5, "Brummer", db.getAnimal(2), db.getAnimal(3));
        db.addAnimal(db.getPerson("Christine"), 6, "Mini", db.getAnimal(4), db.getAnimal(3));
        db.addAnimal(db.getPerson("Alf"), 7, "Jack", db.getAnimal(5), db.getAnimal(1));
        db.addAnimal(db.getPerson("Beate"), 8, "Beate die Zweite", db.getAnimal(0), db.getAnimal(6));
        db.tradeAnimal(db.getAnimal(0), db.getPerson("David"));

        // TODO Implement queries and print output
        // 2.1
        System.out.println("2.1) ");
        // Was sind die Nachfahren von Hansi (id 0)
        System.out.println("Descendants of Hansi (id 0): " + db.getAnimal(0).getDescendants());
        // Was sind die Nachfahren von Frauli (id 3)
        System.out.println("Descendants of Frauli (id 3): " + db.getAnimal(3).getDescendants());
        // Was sind die Vorfahren von Jack (id 7)
        System.out.println("Ancestors of Jack (id 7): " + db.getAnimal(7).getAncestors());
        System.out.println();

        // 2.2
        System.out.println("2.2) ");
        // Welches Tier von Beate hat die meisten Nachfahren?
        System.out.println("Beates animal with most descendants: "
                + db.getPerson("Beate").getAnimalsSortedByDescendantCount().last());
        System.out.println(
                "Descendants: " + db.getPerson("Beate").getAnimalsSortedByDescendantCount().last().getDescendants());
        System.out.println();
        // Welches Tier von Alf hat die wenigsten Vorfahren?
        System.out.println(
                "Alfs animal with most ancestors: " + db.getPerson("Alf").getAnimalsSortedByAncestorCount().first());
        System.out
                .println("Ancestors: " + db.getPerson("Alf").getAnimalsSortedByAncestorCount().first().getAncestors());
        System.out.println();

        // Welches Tier von Christine hat den lexikalisch kleinsten Namen?
        System.out.println("Christines animal with lexicographically smallest name: "
                + db.getPerson("Christine").getAnimalsSortedByName().first());
        System.out.println();

        // Welches Tier hat die meisten Vorfahren?
        SortedSet<Animal> animalsByAncestorCount = db
                .getAnimals((a1, a2) -> (Integer.compare(a1.getAncestors().size(), a2.getAncestors().size())));
        System.out.println("Animal with most Ancestors: " + animalsByAncestorCount.last());
        System.out.println("Ancestors: " + animalsByAncestorCount.last().getAncestors());
        System.out.println();

        // Welche(r) Zuechter(in) hat den laengsten Namen?
        SortedSet<Person> breederByName = db
                .getPersons((p1, p2) -> (Integer.compare(p1.getName().length(), p2.getName().length())));
        System.out.println("Breeder with longest name: " + breederByName.last());
        System.out.println();

        // Welche(r) Zuechter(in) hat die wenigsten Tiere?
        SortedSet<Person> breederByAnimalCount = db
                .getPersons((p1, p2) -> (Integer.compare(p1.getAnimals().size(), p2.getAnimals().size())));
        System.out.println("Breeder with lowest Animal count: " + breederByAnimalCount.first());
    }
}