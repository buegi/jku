package swe2.ss20.ue05.demo.list3;

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
        // TODO using iterator
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

    // additional methods -----------------------------------------------------

    @Override
    public boolean addAll(Iterable<? extends E> elems) {
        for (E e : elems) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean containsAll(Iterable<?> elems) {
        for (Object e : elems) {
            if (!contains(e))
                return false;
        }
        return true;
    }

    public static <T> void copyTo(List<? extends T> from, List<T> to) {
        for (T e : from) {
            to.add(e);
        }
    }

    public static <T> void copyTo2(List<T> from, List<? super T> to) {
        for (T e : from) {
            to.add(e);
        }
    }

    public static <T, F extends T> void copyTo3(List<F> from, List<T> to) {
        for (F e : from) {
            to.add(e);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[size()];
        int i = 0;
        for (E e : this) {
            a[i++] = e;
        }
        return a;
    }

    // does not work, because array of type E cannot be created
    public E[] toEArray() {
        Object[] objs = new Object[size()];
        E[] a = (E[]) objs;
        int i = 0;
        for (E e : this) {
            a[i++] = e;
        }
        return a;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass()
                    .getComponentType(), size());
        }
        Object[] objs = a;
        int i = 0;
        for (E e : this) {
            objs[i++] = e; // will result in ArrayStoreException when not T
            // super E
        }
        return a;
    }

    // Not allowed !!!! Why is not obvious ????
//	 public <T super E> T[] toArray1(T[] a) {
//		 //...
//		 return null;
//	 }
}