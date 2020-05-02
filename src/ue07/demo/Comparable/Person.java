package ue07.demo.Comparable;

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
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Person other = (Person) obj;
    if (hired != other.hired)
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (surName == null) {
      if (other.surName != null)
        return false;
    } else if (!surName.equals(other.surName))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + hired;
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((surName == null) ? 0 : surName.hashCode());
    return result;
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
