package bearmaps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private class Node<T> implements Comparable<Node>{
        private T item;
        private double priority;
        //constructor
        private Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
        T getItem() {return item;}
        double getPriority() {return priority;}
        void setPriority(double priority) {this.priority = priority;}

        @Override
        public int compareTo(Node o) {
            if (o == null) return 0;
            return Double.compare(this.priority, o.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            }
            return ((ArrayHeapMinPQ.Node) o).getItem().equals(item);
        }

        @Override
        public int hashCode() {return item.hashCode();}
    }

    private ArrayList<Node> minHeap;
    private HashMap<T, Integer> heapMap;
    //constructor of ArrayHeapMinPQ
    public ArrayHeapMinPQ() {
        minHeap = new ArrayList<>();
        minHeap.add(null);
        heapMap = new HashMap<>();
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new NoSuchElementException("The item is already in");
        }
        Node n = new Node(item, priority);
        minHeap.add(n);
        heapMap.put(item, size());
        swim(size());
    }

    private void swim(int k) {
        if (minHeap.get(k).compareTo(minHeap.get(parent(k))) == -1) {
            T kItem = (T) minHeap.get(k).getItem();
            T kParent = (T) minHeap.get(parent(k)).getItem();
            swap(k, parent(k));
            heapMap.put(kItem, parent(k));
            heapMap.put(kParent, k);
            swim(parent(k));
        }
    }

    private int parent(int k) {return k / 2;}
    private int leftChild(int k) {return k * 2;}
    private int rightChild(int k) {return k * 2 + 1;}

    private void swap(int x, int y) {
        Node temp = minHeap.get(x);
        minHeap.set(x, minHeap.get(y));
        minHeap.set(y, temp);
    }

    @Override
    public boolean contains(T item) {
        if (heapMap.containsKey(item)) return true;
        return false;
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("It is empty.");
        }
        return (T) minHeap.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        T tmp = getSmallest();
        if (size() == 1) {
            minHeap.clear();
            minHeap.add(null);
            heapMap.clear();
        }
        heapMap.remove(minHeap.get(1).getItem());
        minHeap.set(1, minHeap.get(size()));
        minHeap.remove(size());
        heapMap.put(getSmallest(), 1);
        sink(1);
        return tmp;
    }

    private void sink(int k) {
        if (minHeap.get(k).compareTo(minHeap.get(leftChild(k))) == 1 && (rightChild(k) > size()
                || minHeap.get(leftChild(k)).compareTo(minHeap.get(rightChild(k))) == -1)) {
            T kItem = (T) minHeap.get(k).getItem();
            T kLeftChild = (T) minHeap.get(leftChild(k)).getItem();
            swap(k, leftChild(k));
            heapMap.put(kItem, leftChild(k));
            heapMap.put(kLeftChild, k);
            if (leftChild(k) < size()) sink(leftChild(k));
        } else if (rightChild(k) <= size() && minHeap.get(k).compareTo(minHeap.get(rightChild(k))) == 1 ) {
            T kItem = (T) minHeap.get(k).getItem();
            T kRightChild = (T) minHeap.get(rightChild(k)).getItem();
            swap(k, rightChild(k));
            heapMap.put(kItem, leftChild(k));
            heapMap.put(kRightChild, k);
            if (rightChild(k) < size()) sink(rightChild(k));
        }
    }

    @Override
    public int size() {
        return minHeap.size() - 1;
    }

    @Override
    public void changePriority(T item, double priority) {
        int id = heapMap.get(item);
        Node n = minHeap.get(id);
        n.setPriority(priority);
    }
}
