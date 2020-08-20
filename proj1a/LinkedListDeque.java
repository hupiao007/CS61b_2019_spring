/** Build the linked list base. */
public class LinkedListDeque<T> {
    /** Create a node for doubly linked list. */
    public class LinkedNode {
        public T item;
        public LinkedNode prev;
        public LinkedNode next;

        public LinkedNode(T x) {
            item = x;
            prev = this;
            next = this;
        }
    }

    public int size;
    public LinkedNode sentinel;

    //constructor
    public LinkedListDeque() {
        size = 0;
        sentinel = new LinkedNode((T) "Babe");
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        LinkedNode added_first = new LinkedNode(item);
        sentinel.next.prev = added_first;
        added_first.next = sentinel.next;
        sentinel.next = added_first;
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

    public void printDeque() {
        LinkedNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.println();
    }

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
}
