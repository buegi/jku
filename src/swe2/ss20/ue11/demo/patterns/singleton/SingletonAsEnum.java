package swe2.ss20.ue11.demo.patterns.singleton;

public enum SingletonAsEnum {
    INSTANCE;

    public void useSingleton() {
        System.out.println("I used the singleton " + this.hashCode());
    }
}