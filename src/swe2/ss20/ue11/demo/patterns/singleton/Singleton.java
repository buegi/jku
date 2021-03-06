package swe2.ss20.ue11.demo.patterns.singleton;

public class Singleton {

    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private Singleton() {
        super();
    }

    public void useSingleton() {
        System.out.println("I used the singleton " + this.hashCode());
    }
}