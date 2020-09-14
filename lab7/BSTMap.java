import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        this.size = 0;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    private Node findKey(K key, Node n) {
        if (n == null) {
            return null;
        }
        int compareKey = n.key.compareTo(key);
        if (compareKey == 0) {
            return n;
        } else if (compareKey < 0){
            return findKey(key, n.right);
        } else {
            return findKey(key, n.left);
        }
    }
    @Override
    public boolean containsKey(K key) {
        return findKey(key, this.root) != null;
    }

    @Override
    public V get(K key) {
        Node n = findKey(key, this.root);
        if (n == null) {
            return null;
        }
        return n.value;
    }

    @Override
    public int size() {
        return this.size;
    }


    private Node putHelper(K k1, V v1, Node n) {
        if (n == null) {
            n = new Node(k1, v1);
            this.size++;
        }
        int compareKey = n.key.compareTo(k1);
        if (compareKey == 0) {
            n.value = v1;
        } else if (compareKey < 0) {
            n.right = putHelper(k1, v1, n.right);
        } else {
            n.left = putHelper(k1, v1, n.left);
        }
        return n;
    }
    @Override
    public void put(K key, V value) {
        if (this.root == null) {
            this.root = new Node(key, value);
            //bstM.root.size++;
            this.size++;
        }
        putHelper(key, value, this.root);
    }

    private void keySetHelper(Node n, Set<K> keySet) {
        if (n != null) {
            keySet.add(n.key);
            keySetHelper(n.left, keySet);
            keySetHelper(n.right, keySet);
        }
    }
    @Override
    public Set<K> keySet() {
        if (this.root == null) {
            return null;
        } else{
            Set<K> keySet = new HashSet<K>();
            keySet.add(this.root.key);
            keySetHelper(this.root.left, keySet);
            keySetHelper(this.root.right, keySet);
            return keySet;
        }
        //throw new UnsupportedOperationException("unsupported method");
    }

    private V removeHelper(K key, Node n) {
        return null;
    }
    private Node findReplace(K key, Node n) {
        //if (n.key == key)
        return null;
    }
    @Override
    public V remove(K key) {
        /**
        if (this.root.key.compareTo(key) == 0) {
        } */
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("unsupported method");
    }


    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("unsupported method");
    }
}
