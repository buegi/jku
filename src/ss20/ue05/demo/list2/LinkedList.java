package ss20.ue05.demo.list2;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        // foreach loop on this is possible because we implement Iterable
        for (E val : this) {
            if (val.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current;

            {
                current = head;
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (current != null) {
                    E value = current.value;
                    current = current.next;
                    return value;
                } else {
                    throw new NoSuchElementException("No more element in list");
                }
            }
        };
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

    private class IteratorImpl implements Iterator<E> {
        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (current != null) {
                E value = current.value;
                current = current.next;
                return value;
            } else {
                throw new NoSuchElementException("No more element in the list");
            }
        }
    }
}