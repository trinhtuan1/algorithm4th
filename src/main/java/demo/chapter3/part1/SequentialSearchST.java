package demo.chapter3.part1;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class SequentialSearchST<Key, Value> {
    private int n;
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isContains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        for(Node x = first; x != null; x = x.next) {
            if(key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        for(Node x = first; x != null; x = x.next) {
            if(key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new ArrayBlockingQueue<>(n);
        for(Node x = first; x != null; x = x.next) {
            queue.add(x.key);
        }
        return queue;
    }

    public void delete(Key key) {
        first = delete(first, key);
    }

    public Node delete(Node x, Key key) {
        if(x == null) return null;
        if(key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        String str = "SEARCHEXAMPLE";

        for(int i = 0; i < str.length(); i++) {
            st.put(String.valueOf(str.charAt(i)), i);
        }

        for(String key : st.keys()) {
            System.out.println(key + " " + st.get(key));
        }
    }
}