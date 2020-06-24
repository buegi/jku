package swe2.ss20.exam.stream;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Person {

    private String name;
    private int age;
    private Set<String> hobbies;

    public Person(String name, int age, String... hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new TreeSet<>();
        this.hobbies.addAll(Arrays.asList(hobbies));
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }
}