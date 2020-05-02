package ue07.demo.Hash;

import java.util.HashSet;
import java.util.Set;

public class Demo {

  public static void main(String[] args) {
    // equals test
    Person franz1 = new Person("Franz", "Maier", 2020);
    Person franz2 = new Person("Franz", "Maier", 2020);
    boolean eq = franz1.equals(franz2);
    System.out.println(eq);

    // hashCode
    int franz1Hash = franz1.hashCode();
    System.out.println(franz1Hash);

    int franz2Hash = franz2.hashCode();
    System.out.println(franz2Hash);

    // HashSet
    Set<Person> hs = new HashSet<>();
    hs.add(franz1);
    boolean franz2Contained = hs.contains(franz2);
    System.out.println(franz2Contained);
    hs.add(franz2);
  }
}
