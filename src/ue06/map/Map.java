package ue06.map;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import ue06.demo.list.List;
import ue06.list.LinkedList;

/**
 * A map from keys to values.
 *
 * @param <K> The type of the keys
 * @param <V> The type of the values
 */
public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {

    /**
     * Inner class for entries of maps.
     *
     * @param <K> The key of the entry
     * @param <V> The value of the entry
     */
    public static interface Entry<K, V> {
        /**
         * Gets the key of this entry.
         *
         * @return The key of this entry
         */
        K getKey();

        /**
         * Gets the value of this entry.
         *
         * @return The value of this entry
         */
        V getValue();
    }


    /**
     * Add a value for the given key to the map.
     * If there already exists a value assigned to the given key it should be replaced.
     *
     * @param key   The key
     * @param value The value
     */
    void put(K key, V value);

    /**
     * Gives the value assigned to a given key.
     *
     * @param key The key
     * @return The value assigned to the key, or null if no matching entry is found
     */
    V get(K key);

    /**
     * Check whether an entry exists for the given key.
     *
     * @param key The key
     * @return True if this map contains an entry for the given key, otherwise false
     */
    boolean contains(K key);

    /**
     * Removes the entry for the given key if existing.
     *
     * @param key The key
     * @return True if an entry has been removed, otherwise false
     */
    boolean remove(K key);

    /**
     * Returns the number of entries in this map.
     *
     * @return The number of entries
     */
    int size();

    /**
     * An iterator that iterates over all keys in this map.
     *
     * @return An iterator that iterates over all keys in this map
     */
    Iterator<K> keyIterator();

    /**
     * An iterator that iterates over all values in this map.
     *
     * @return An iterator that iterates over all values in this map
     */
    Iterator<V> valueIterator();

    /**
     * An iterator that iterates over all key-value pairs in this map.
     *
     * @return An iterator that iterates over all key-value pairs in this map
     */
    Iterator<Entry<K, V>> iterator();

    // TODO: Higher order functions for Map

    // map
    default <R> Map<K, R> map(BiFunction<? super K, ? super V, ? extends R> mapper) {
        // TODO
        Map<K, R> result = new ArrayMap<K, R>();
        for (Entry<K, V> entry : this) {
            result.put(entry.getKey(), mapper.apply(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    // filter 
    default Map<K, V> filter(BiPredicate<? super K, ? super V> predicate) {
        // TODO
        Map<K, V> result = new ArrayMap<K, V>();
        for (Entry<K, V> entry : this) {
            if (predicate.test(entry.getKey(), entry.getValue())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    // forEach
    default void forEach(BiConsumer<? super K, ? super V> action) {
        // TODO
        for (Entry<K, V> entry : this) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }

    // reduce
    default <R> R reduce(R initial, BiFunction<R, Entry<? super K, ? super V>, R> acc) {
        // TODO
        R result = initial;
        for (Entry<K, V> entry : this) {
            result = acc.apply(result, entry);
        }
        return result;
    }

    // find
    default Optional<Entry<K, V>> find(BiPredicate<? super K, ? super V> predicate) {
        // TODO
        for (Entry<K, V> entry : this) {
            if (predicate.test(entry.getKey(), entry.getValue())) {
                return Optional.of(entry);
            }
        }
        return Optional.empty();
    }

    // group
    default <G, R> Map<G, List<R>> group(
            BiFunction<? super K, ? super V, G> groupingFn,
            BiFunction<? super K, ? super V, R> valueFn) {
        // TODO
        Map<G, R> result = new ArrayMap();



        return null;
    }
}