package prswe2.ss21.ue02.demo.person;

import java.io.Serializable;

import prswe2.ss21.ue02.demo.annotation.*;

@Parse(value = PersonParser.class)
public class Person {
    private String name;
    private Gender gender;

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String toString() {
        return name + ", gender: " + gender;
    }
}