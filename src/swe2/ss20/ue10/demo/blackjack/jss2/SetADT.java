package swe2.ss20.ue10.demo.blackjack.jss2;

import java.util.Iterator;

public interface SetADT<T> {
    //  Adds one element to this bag
    void add(T element);

    //  Removes and returns a random element from this bag
    T removeRandom();

    //  Removes and returns the specified element from this bag
    T remove(T element);

    //  Returns the union of this bag and the parameter
    SetADT union(SetADT set);

    //  Returns true if this bag contains the parameter
    boolean contains(T target);

    //  Returns true if this bag and the parameter contain exactly
    //  the same elements
    boolean equals(SetADT set);

    //  Returns true if this set contains no elements
    boolean isEmpty();

    //  Returns the number of elements in this set
    int size();

    //  Returns an iterator for the elements in this bag
    Iterator iterator();

    //  Returns a string representation of this bag
    String toString();
}