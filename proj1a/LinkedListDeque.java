/** Build the linked list base. */
public class LinkedListDeque<T> {
    /** Create a node for doubly linked list. */
    private class LinkedNode {
        public T item;
        public LinkedNode prev;
        public LinkedNode next;
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
            T item_i = (T) other.get(i);
            addLast(item_i);
        }
    }
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        LinkedNode added_first = new LinkedNode(item);
        sentinel.next.prev = added_first;
        added_first.next = sentinel.next;
        sentinel.next = added_first;
        added_first.prev = sentinel;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        LinkedNode added_last = new LinkedNode(item);
        sentinel.prev.next = added_last;
        added_last.prev = sentinel.prev;
        added_last.next = sentinel;
        sentinel.prev = added_last;
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
        T removed_f = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removed_f;
    }

    /** Removes and returns the item at the back of the deque. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removed_l = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return removed_l;
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
            return getRecursive(p, index -1);
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
