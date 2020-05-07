package swe2.ss20.ue03.demo.composite;

public class Atomic extends Element {

    public Atomic(int i) {
        super(i);
    }

    @Override
    public int operation() {
        return 1;
    }
}