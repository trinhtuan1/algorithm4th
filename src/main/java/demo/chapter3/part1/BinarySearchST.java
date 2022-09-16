package demo.chapter3.part1;

import java.util.PriorityQueue;
import java.util.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private int n;
    private Key[] keys;
    private Value[] vals;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public int rank(Key key) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public void delete(Key key) {
        if (isEmpty()) return;
        int i = rank(key);
        if(i >= n || keys[i].compareTo(key) != 0) return;

        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        n--;
        keys[n] = null;
        vals[n] = null;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[n - 1];
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new PriorityQueue<>();
        for(int i = rank(lo) ; i < rank(hi); i++) {
            queue.add(keys[i]);
        }
        if(contains(hi)) {
            queue.add(keys[rank(hi)]);
        }
        return queue;
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if(contains(key)) return keys[i];
        else return keys[i - 1];
    }

    public Key select(int i) {
        return keys[i];
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>(20);
        String str = "SEARCHEXAMPLE";

        for(int i = 0; i < str.length(); i++) {
            st.put(String.valueOf(str.charAt(i)), i);
        }

        for(String key : st.keys()) {
            System.out.println(key + " " + st.get(key));
        }
        String ch = "G";
        System.out.println("Floor" + st.floor(ch));
        System.out.println("Ceiling" + st.ceiling(ch));
    }
}
