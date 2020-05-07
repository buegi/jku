package swe1.ws17.ue07;

public class Estate {

    private String streetname;
    private int streetnumber;
    private int size;
    private Estate next;

    public void setStreetName(String newStreetname) {
        streetname = newStreetname;
    }

    public String getStreetName() {
        return streetname;
    }

    public void setStreetNumber(int newStreetnumber) {
        streetnumber = newStreetnumber;
    }

    public int getStreetNumber() {
        return streetnumber;
    }

    public void setSize(int newSize) {
        size = newSize;
    }

    public int getSize() {
        return size;
    }

    public Estate getNext() {
        return next;
    }

    public void setNext(Estate newNext) {
        next = newNext;
    }
}