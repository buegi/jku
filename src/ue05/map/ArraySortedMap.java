package ue05.map;

import java.util.Arrays;

public class ArraySortedMap<K extends Comparable<? super K>, V> extends ArrayMap<K, V> implements SortedMap<K, V> {

    @Override
    public void put(K key, V value) {
        if (this.nEntries >= this.entries.length) {
            this.entries = Arrays.copyOf(this.entries, this.entries.length * 2);
        }
        // contains key
        if (contains(key)) {
            this.entries[indexOf(key)] = new SimpleEntry<>(key, value);
            // does not contain key
        } else {
            int index = 0;
            while (this.entries[index] != null && key.compareTo(this.entries[index].getKey()) >= 0) {
                index++;
            }
            System.arraycopy(this.entries, index, this.entries, index + 1, this.nEntries - index);
            this.entries[index] = new SimpleEntry<>(key, value);
            this.nEntries++;
        }
    }

    @Override
    protected int indexOf(K key) {
        int index = 0;
        while (index < this.entries.length && this.entries[index] != null && key.compareTo(this.entries[index].getKey()) >= 0) {
            if (this.entries[index].getKey().equals(key)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}