package ss20.ue07.demo.EqualsSym;

public class Developer extends Person {
  public enum Expertise {FULL_STACK, FRONT_END, BACK_END, DATABASE, UX_DESIGN}

  private final Expertise expertise;

  public Developer(String firstName, String surName, int hired, Expertise expertise) {
    super(firstName, surName, hired);
    this.expertise = expertise;
  }

  // Asymmetric implementation of equals
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Developer) {
      Developer other = (Developer) obj;
      return surName.equals(other.surName)
          && firstName.equals(other.firstName)
          && expertise == other.expertise;
    }
    return false;
  }
}
