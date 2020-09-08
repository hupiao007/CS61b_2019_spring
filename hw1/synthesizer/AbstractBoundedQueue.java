package synthesizer;

import java.util.Iterator;

public abstract class AbstractBoundedQueue {
    /**
    int capacity();     // return size of the buffer
    int fillCount();    // return number of items currently in the buffer
    void enqueue(T x);  // add item x to the end
    T dequeue();        // delete and return item from the front
    T peek();           // return (but do not delete) item from the front
    Iterator<T> iterator();
    // is the buffer empty (fillCount equals zero)?
    boolean isEmpty() {
        return fillCount() == 0;
    }
    // is the buffer full (fillCount is same as capacity)?
    boolean isFull()  {
        return capacity() == fillCount();
    }
     */
}
