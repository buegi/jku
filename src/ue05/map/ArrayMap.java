package ue05.map;

import java.util.Iterator;

public class ArrayMap implements Map<K, V> {

    @SuppressWarnings("unchecked")
    protected Entry<K, V>[] entries = (Entry<K, V>[]) new Entry[16];
    protected int nEntries = 0;

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<K> keyIterator() {
        return null;
    }

    @Override
    public Iterator<V> valueIterator() {
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }
}
