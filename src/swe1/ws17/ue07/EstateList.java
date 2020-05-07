package swe1.ws17.ue07;

public class EstateList {

    private Estate head = null;
    private Estate tail = null;

    // buy Estate
    public void buyEstate(String streetname, int streetnumber, int size) {
        Estate newobject = new Estate();
        newobject.setStreetName(streetname);
        newobject.setStreetNumber(streetnumber);
        newobject.setSize(size);

        if (head == null) {
            head = newobject;
            tail = newobject;
        } else {
            tail.setNext(newobject);
            tail = newobject;
        }
    }

    // sell estate
    public void sellEstate(String streetname, int streetnumber) {
        if (head == null) {
            System.err.println("Nothing to delete!");
        } else {
            Estate actual = head;
            Estate prev = null;

            while (actual != null) {
                if (actual.getStreetName() == streetname && actual.getStreetNumber() == streetnumber) {
                    if (prev == null) {
                        head = head.getNext();
                    } else {
                        prev.setNext(actual.getNext());
                    }
                }
                prev = actual;
                actual = actual.getNext();
            }
        }
    }

    // print estate list
    public void printEstateList() {
        System.out.println("==== Estate Manager ====");
        Estate temp = head;
        while (temp != null) {
            System.out.println(temp.getStreetName() + " " + temp.getStreetNumber() + " with " + temp.getSize() + " m�");
            temp = temp.getNext();

        }
        System.out.println("==== ===== ========= ====");
        System.out.println("");
    }

    // selectively printing of the estate list, by name or by area size (min/max)
    public void printSelectiveEstateList(String streetname, int minsize, int maxsize) {
        System.out.println("==== Estate Manager ====");
        Estate temp = head;
        while (temp != null) {
            if (streetname != null) {
                if (temp.getStreetName() == streetname) {
                    System.out.println(
                            temp.getStreetName() + " " + temp.getStreetNumber() + " with " + temp.getSize() + " m�");
                } else {
                }
            }
            if (minsize != 0 || maxsize != 0) {
                if (temp.getSize() >= minsize && temp.getSize() <= maxsize) {
                    System.out.println(
                            temp.getStreetName() + " " + temp.getStreetNumber() + " with " + temp.getSize() + " m�");
                } else {
                }
            }
            temp = temp.getNext();
        }
        System.out.println("==== ===== ========= ====");
        System.out.println("");
    }

    // Ordered insert into the estate list
    public void orderedbuyEstate(String streetname, int streetnumber, int size) {
        Estate newobject = new Estate();
        newobject.setStreetName(streetname);
        newobject.setStreetNumber(streetnumber);
        newobject.setSize(size);

        if (head == null) {
            head = newobject;
            tail = newobject;
        } else {
            Estate actual = head;
            while (actual != null && (newobject.getStreetName().compareTo(actual.getStreetName()) >= 0)) {
                if (newobject.getStreetName().compareTo(actual.getStreetName()) > 0)
                    actual = actual.getNext();
                else
                    actual = actual;
                break;

            }
            newobject.setNext(actual.getNext());
            actual.setNext(newobject);
        }
    }
}