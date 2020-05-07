package ss20.ue05.demo.genericproblems;

@SuppressWarnings({"unused", "rawtypes"})
public class G<T> {
    T t;

    public G(T t) {
        this.t = t;
        // Objekterzeugung mit Typparameter nicht m�glich
        // T t = new T(); // does not work
        // T[] ts = new T[10]; // does not work
    }

    public static void main(String[] args) {
        // Typcast auf generische Typen nicht m�glich
        Object io = new G<Integer>(1);
        if (io instanceof G<?>) {
            System.out.println("It's a G<?>");
        }

        // Nicht m�glich, da generischer Typ zur Laufzeit nicht verf�gbar ist
        // if (io instanceof G<Integer>) {
        //	System.out.println("It's a G<?>");
        //}

        Object so = new G<String>("G");
        if (so instanceof G<?>) {
            System.out.println("It's a G<?>");
        }

        // Metaklassen von generischen Typen nicht bekannt:
        // Class clazz = G<Integer>.class;
        Class clazz = G.class;
    }
}