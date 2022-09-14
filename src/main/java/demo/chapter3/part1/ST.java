package demo.chapter3.part1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private TreeMap<Key, Value> st;

    public ST() {
        this.st = new TreeMap<>();
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("call get() with null key");
        return st.get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("call put() with null key");
        if (val == null) st.remove(key);
        else st.put(key, val);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("call delete() with null key");
        st.remove(key);
    }

    public void remove(Key key) {
        if (key == null) throw new IllegalArgumentException("call remove() with null key");
        st.remove(key);
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("call contains() with null key");
        return st.containsKey(key);
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("call min with empty table");
        return st.firstKey();
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("call max with empty table");
        return st.lastKey();
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("call ceiling() with null key");
        return st.ceilingKey(key);
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("call floor() with null key");
        return st.floorKey(key);
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    @Override
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }
}
