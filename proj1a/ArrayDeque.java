import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.desktop.SystemEventListener;

public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] deque;
    //constructor, creates an empty deque
    public ArrayDeque() {
        deque= (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    /** Copy items from old_deque into new_deque. */
    private T[] copyDeque(T[] old_deque, T[] new_deque, int size) {
        int first = minusOne(nextFirst);
        if (nextFirst >= nextLast) {
            System.arraycopy(old_deque, first, new_deque, 1, old_deque.length - first);
            System.arraycopy(old_deque, 0, new_deque, old_deque.length - first + 1,
                    size -(old_deque.length -first));
        } else {
            System.arraycopy(old_deque, first, new_deque, 1, size);
        }
        nextFirst = 0;
        nextLast = size +1;
        return new_deque;
    }

    /** Check and resize the deque. */
    private void resize(T[] prev_deque) {
        if (size/ prev_deque.length < 0.25) {
            T[] a = (T[]) new Object[prev_deque.length /2];
            prev_deque = copyDeque(prev_deque, a, size);
        } else if (size > prev_deque.length - 2) {
            T[] a = (T[]) new Object[prev_deque.length *2];
            prev_deque = copyDeque(prev_deque, a, size);
        } else {
            return;
        }
    }

    /** Minus index by 1. */
    private int minusOne(int index) {
        if (index == 0){
            return deque.length;
        } else {
            return index -1;
        }
    }
    /** Increase index by 1. */
    private int addOne(int index) {
        if (index == deque.length) {
            return 0;
        } else {
            return index +1;
        }
    }

    /** Add an item to the first of deque. */
    public void addFirst(T item) {
        deque[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size ++;
        resize(deque);
    }

    public void addLast(T item) {
        deque[nextLast] = item;
        nextLast = addOne(nextLast);
        size ++;
        resize(deque);
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int tempF = addOne(nextFirst);
        int tempL = minusOne(nextLast);
        while (tempF != tempL) {
            System.out.print(deque[tempF]);
            System.out.print(' ');
            tempF = addOne(tempF);
        }
        System.out.println();
    }

    public T removeFirst() {
        nextFirst = addOne(nextFirst);
        T temp = (T) deque[nextFirst];
        deque[nextFirst] = null;
        size --;
        resize(deque);
        return temp;
    }

    public T removeLast() {
        nextLast = minusOne(nextLast);
        T temp = (T) deque[nextLast];
        deque[nextLast] = null;
        size --;
        resize(deque);
        return temp;
    }

    public T get(int index) {
        return deque[index];
    }

    public ArrayDeque(ArrayDeque other) {
        size = other.size;
        deque = (T[]) new Object[other.deque.length];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        deque = copyDeque((T[]) other.deque, deque, size);
    }
}
