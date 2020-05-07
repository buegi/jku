package ss20.ue07.demo.TreeMap;

import java.util.Objects;

public class Person implements Comparable<Person> {
  public final String surName;
  public final String firstName;
  public final int hired;

  public Person(String firstName, String surName, int hired) {
    this.firstName = firstName;
    this.surName = surName;
    this.hired = hired;
  }

  @Override
  public String toString() {
    return firstName + " " + surName + " " + hired;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Person) {
      Person other = (Person) obj;
      return surName.equals(other.surName)
          && firstName.equals(other.firstName);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(surName, firstName);
  }

  @Override
  public int compareTo(Person other) {
    int compare = surName.compareTo(other.surName);
    if (compare == 0) {
      compare = firstName.compareTo(other.firstName);
    }
    return compare;
  }

}
