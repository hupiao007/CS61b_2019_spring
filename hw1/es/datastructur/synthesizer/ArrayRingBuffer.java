package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek.
    * Index of the first inserted item. */
    private int first;
    /* Index for the next enqueue.
    * Index for next item to be inserted in. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int cap;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        cap = capacity;
        rb = (T[]) new Object[cap];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    private int minusIndex(int a) {
        if (a == 0) {
            a = cap - 1;
        } else {
            a--;
        }
        return a;
    }

    private int addIndex(int a) {
        if (a == cap - 1) {
            a = 0;
        } else {
            a++;
        }
        return a;
    }

    private void testExcepFull() {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
    }

    private void testExcepEmpty() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer overflow");
        }
    }

    @Override
    public void enqueue(T x) {
        testExcepFull();
        rb[last] = x;
        fillCount++;
        last = addIndex(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        testExcepEmpty();
        T dT= rb[first];
        rb[first] = null;
        fillCount--;
        first = addIndex(first);
        return dT;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        testExcepEmpty();
        return rb[first];
    }

    @Override
    public int capacity() {
        return cap;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    public Iterator<T> iterator() {
        return new ArryRingIterator();
    }

    private class ArryRingIterator implements Iterator<T> {
        int countIndex;
        int pos;
        public ArryRingIterator() {
            countIndex = 0;
            pos = last;
        }
        public boolean hasNext() {
            return countIndex < fillCount;
        }

        public T next() {
            T i = rb[pos];
            pos = addIndex(pos);
            countIndex++;
            return i;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != ArrayRingBuffer.class) {
            return false;
        }
        while (iterator().hasNext()) {
            if (((ArrayRingBuffer<T>) o).iterator().next() != iterator().next()) {
                return false;
            }
        }
        return true;
    }
}
