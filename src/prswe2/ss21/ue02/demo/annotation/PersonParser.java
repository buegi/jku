package prswe2.ss21.ue02.demo.annotation;

import prswe2.ss21.ue02.demo.person.Gender;
import prswe2.ss21.ue02.demo.person.Person;

public class PersonParser implements Parser<Person> {

    @Override
    public Person parse(String text) {
        // parses: Max Male
        String[] ws = text.split(" ");
        return new Person(ws[0], ws[1].equals("Male") ? Gender.Male : Gender.Female);
    }
}