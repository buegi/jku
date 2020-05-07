package swe2.ss20.ue05.demo.list1;

import java.util.Iterator;

/**
 * This linked list implementation shows the use of generics and inner classes
 *
 * @param <E> the element type
 */
public class LinkedList<E> implements List<E> {

    @Override
    public int size() {
        return n;
    }

    public void add(E elem) {
        head = new Node<E>(elem, head);
        n++;
    }

    @Override
    public boolean contains(Object obj) {
        // TODO using iterator
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO by inner classes
        return null;
    }

    // private and protected --------------------------------------------------

    protected Node<E> head = null;

    protected int n = 0;

    // inner class Node -------------------------------------------------------

    protected static class Node<F> {
        final F value;
        Node<F> next;

        Node(F elem, Node<F> next) {
            this.value = elem;
            this.next = next;
        }
    }
    // inner class IteratorImpl -----------------------------------------------
}