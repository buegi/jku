package ue07.demo.EqualsSym;

import java.util.Objects;

public class Person {
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
}
