package swe1.ws17.ue06;

public class Person {

    private int age;
    private String name;

    // +getAge(): int
    public int getAge() {
        return age;
    }

    // +setAge(int newAge): void
    public void setAge(int newAge) {
        age = newAge;
    }

    // +getName(): String
    public String getName() {
        return name;
    }

    // +setName(String newName): void
    public void setName(String newName) {
        name = newName;
    }
}