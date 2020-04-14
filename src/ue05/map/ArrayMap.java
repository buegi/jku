package ue05.map;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayMap<K, V> implements Map<K, V> {

    @SuppressWarnings("unchecked")
    protected Entry<K, V>[] entries;
    protected int nEntries;

    public ArrayMap() {
        this.entries = (Entry<K, V>[]) new Entry[16];
        this.nEntries = 0;
    }

    protected static class SimpleEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public SimpleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }
    }

    protected int indexOf(K key) {
        int index = 0;
        while (index < entries.length && entries[index] != null) {
            if (entries[index].getKey().equals(key)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void put(K key, V value) {
        if (contains(key)) {
            entries[indexOf(key)] = new SimpleEntry<K, V>(key, value);
        } else {
            if (nEntries >= entries.length) {
                entries = Arrays.copyOf(entries, entries.length * 2);
            }
            entries[nEntries] = new SimpleEntry<K, V>(key, value);
            nEntries++;
        }
    }

    @Override
    public V get(K key) {
        if (contains(key)) {
            return this.entries[this.indexOf(key)].getValue();
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return this.indexOf(key) == -1 ? false : true;
    }

    @Override
    public boolean remove(K key) {
        int index = this.indexOf(key);
        if (index >= 0) {
            System.arraycopy(this.entries, index + 1, this.entries, index, this.size() - index);
            this.nEntries--;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.nEntries;
    }

    @Override
    public Iterator<K> keyIterator() {
        return new Iterator<K>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return (index < entries.length) && (entries[index] != null);
            }

            @Override
            public K next() {
                return entries[index++].getKey();
            }
        };
    }

    @Override
    public Iterator<V> valueIterator() {
        return new Iterator<V>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return (index < entries.length) && (entries[index] != null);
            }

            @Override
            public V next() {
                return entries[index++].getValue();
            }
        };
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return (index < entries.length) && (entries[index] != null);
            }

            @Override
            public Entry<K, V> next() {
                return entries[index++];
            }
        };
    }
}