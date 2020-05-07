package ss20.ue07.demo.Equals;

public class Demo {

  public static void main(String[] args) {
    Person franz1 = new Person("Franz", "Maier", 2020);
    Person franz2 = new Person("Franz", "Maier", 2020);

    boolean equals = franz1.equals(franz2);
    System.out.println(equals);

    Object obj2 = franz2;
    System.out.println(obj2.toString());
    System.out.println(obj2.equals(franz1));

    equals = franz1.equals(obj2);
    System.out.println(equals);
  }
}
