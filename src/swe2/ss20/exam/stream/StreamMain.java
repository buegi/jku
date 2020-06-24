package swe2.ss20.exam.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMain {

    public static void main(String[] args) {

        Person person1 = new Person("Sepp", 20, "Angeln", "Fischen", "Radfahren");
        Person person2 = new Person("Timmy", 14, "Angeln", "Fischen", "Spazieren");
        Person person3 = new Person("Alex", 17, "Reiten", "Rodeln", "SWE2");
        Person person4 = new Person("Joe", 23, "Sex", "Football", "Rock'n'Roll");
        Person person5 = new Person("Irgendwer", 58, "Essen", "Trinken");

        List<Person> persons = new ArrayList<>();

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);

        long numPersons18OrOver = persons.stream().filter(p -> p.getAge() >= 18).count();
        System.out.println("numPersons18OrOver: " + numPersons18OrOver);

        List<String> namesSortedDesc =
                persons.stream().map(p -> p.getName())
                        .sorted((p1, p2) -> p2.compareTo(p1))
                        .collect(Collectors.toList());

        for (String elem : namesSortedDesc) {
            System.out.println(elem.toString());
        }

        boolean footballIsHobby =
                persons.stream().map(p -> p.getHobbies()).anyMatch(h -> {
                    for (String st : h) {
                        if (st.equals("Football")) {
                            return true;
                        }
                    }
                    return false;
                });

        System.out.println("footballIsHobby: " + footballIsHobby);
    }
}
