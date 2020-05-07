package ss20.ue05.demo.genericproblems;

// Generische Exception-Klassen nicht erlaubt. 
public class GException/*<T>*/ extends Exception {
    //T elem;

    public static void main(String[] args) {

        try {
            throw new GException();
            // do something
        } catch (GException/*<String>*/ excpt) { // generischen Typ zur Laufzeit pr�fen kann nicht funktionireren

        }
    }
}