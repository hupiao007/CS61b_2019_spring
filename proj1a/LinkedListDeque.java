/** Build the linked list base. */
public class LinkedListDeque<T> {
    /** Create a node for doubly linked list. */
    private class LinkedNode {
        private T item;
        private LinkedNode prev;
        private LinkedNode next;
        // constructor
        public LinkedNode(T x) {
            item = x;
            prev = this;
            next = this;
        }
    }

    private int size = 0;
    private LinkedNode sentinel = new LinkedNode((T) "babe");

    //constructor
    public LinkedListDeque() {
        sentinel = new LinkedNode((T) "babe");
        size = 0;
    }

    /** Creates a deep copy of other. */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new LinkedNode((T) "Babe");
        for (int i = 0; i < other.size; i += 1) {
            T itemi = (T) other.get(i);
            addLast(itemi);
        }
    }
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        LinkedNode addedFirst = new LinkedNode(item);
        sentinel.next.prev = addedFirst;
        addedFirst.next = sentinel.next;
        sentinel.next = addedFirst;
        addedFirst.prev = sentinel;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        LinkedNode addedLast = new LinkedNode(item);
        sentinel.prev.next = addedLast;
        addedLast.prev = sentinel.prev;
        addedLast.next = sentinel;
        sentinel.prev = addedLast;
        size += 1;
    }

    /**  Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return sentinel.prev == sentinel;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    public void printDeque() {
        LinkedNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removedF = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removedF;
    }

    /** Removes and returns the item at the back of the deque. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removedL = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return removedL;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        LinkedNode p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /** Private helper method for get recursive. */
    private T getRecursive(LinkedNode p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            p = p.next;
            return getRecursive(p, index - 1);
        }
    }
    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } else {
            LinkedNode p = sentinel.next;
            return getRecursive(p, index);
        }
    }
}
