package ss20.ue06.demo.list;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface List<E> extends Iterable<E> {

    /**
     * Creates a list with the given elements
     *
     * @param <T> the element type
     * @param ts  the elements for the list
     * @return the list with the given elements
     */
    public static <T> List<T> of(@SuppressWarnings("unchecked") T... ts) {
        List<T> list = new LinkedList<T>();
        for (T t : ts) {
            list.add(t);
        }
        return list;
    }

    /**
     * Returns the number of elements of this list
     *
     * @return the number of elements
     */
    public int size();

    /**
     * Tests if this list is empty.
     *
     * @return <code>true</code> if empty, <code>false</code> otherwise
     */
    public default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds an element to this list
     *
     * @param elem the element to be added
     */
    public void add(E elem);

    /**
     * Tests if the object is contained in this list
     *
     * @param obj the object to be tested
     * @return <code>true</code> if object is contained, <code>false</code>
     * otherwise
     */
    public boolean contains(Object obj);

    /**
     * Adds all elements to this list.
     *
     * @param elems the elements to add
     * @return <code>true</code> if successful
     */
    public boolean addAll(Iterable<? extends E> elems);

    /**
     * Tests if all elements are contained in this list.
     *
     * @param elems the elements to test
     * @return <code>true</code> if all elements are contained
     */
    public boolean containsAll(Iterable<?> c);

    /**
     * Creates an object array with the elements of this list.
     *
     * @return the object array with the elements
     */
    public Object[] toArray();

    /**
     * Creates an array of type <code>T</code> with the elements of this list.
     *
     * @return the object array with the elements
     */
    public <T> T[] toArray(T[] a);

    // Demo 6 Lambdas  ---------------------

    public default <R> List<R> map(Function<? super E, ? extends R> mapper) {
//		List<R> result = new LinkedList<R>();
        List<R> result = createResultList();
        for (E e : this) {
            result.add(mapper.apply(e));
        }
        return result;
    }

    public default List<E> filter(Predicate<? super E> predicate) {
        List<E> result = createResultList();
        for (E e : this) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public default void forEach(Consumer<? super E> action) {
        for (E e : this) {
            action.accept(e);
        }
    }

    public default <U> U reduce(U identity, BiFunction<U, ? super E, U> accumulator) {
        U result = identity;
        for (E e : this) {
            result = accumulator.apply(result, e);
        }
        return result;
    }

    public default Optional<E> reduce(BinaryOperator<E> accumulator) {
        if (this.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(reduce(this.iterator().next(), accumulator));
        }
    }

    public default Optional<E> find(Predicate<? super E> predicate) {
        for (E e : this) {
            if (predicate.test(e)) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

    public default List<E> copy() {
        return map(e -> e);
    }

    public default Optional<E> max(Comparator<? super E> comparator) {
        return reduce((max, e) -> {
            if (comparator.compare(e, max) > 0) {
                return e;
            } else {
                return max;
            }
        });
    }

    public default Optional<E> min(Comparator<? super E> comparator) {
        return reduce((max, e) -> {
            if (comparator.compare(e, max) < 0) {
                return e;
            } else {
                return max;
            }
        });
    }

    public default boolean contains2(Object o) {
        return find(e -> e.equals(o)).isPresent();
    }

    public default boolean all(Predicate<? super E> predicate) {
        return find(predicate.negate()).isEmpty();
    }

    public default boolean any(Predicate<? super E> predicate) {
        return find(predicate).isPresent();
    }

    public static <T> List<T> generate(int n, Supplier<T> supplier) {
        List<T> result = new LinkedList<T>();
        for (int i = 0; i < n; i++) {
            result.add(supplier.get());
        }
        return result;
    }

    public static <T> List<T> iterate(int n, T first, UnaryOperator<T> next) {
        List<T> result = new LinkedList<T>();
        T nextElem = first;
        result.add(first);
        for (int i = 1; i < n; i++) {
            nextElem = next.apply(nextElem);
            result.add(nextElem);
        }
        return result;
    }

    public <R> List<R> createResultList();
}