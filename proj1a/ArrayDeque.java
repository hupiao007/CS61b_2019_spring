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
        nextLast = 1;
    }

    /** Copy items from old_deque into new_deque. */
    private void copyDeque(T[] old_deque, T[] new_deque, int size) {
        int first = addOne(nextFirst);
        if (nextFirst >= nextLast) {
            System.arraycopy(old_deque, first, new_deque, 1, old_deque.length - first);
            System.arraycopy(old_deque, 0, new_deque, old_deque.length - first + 1,
                    size -(old_deque.length -first));
        } else {
            System.arraycopy(old_deque, first, new_deque, 1, size);
        }
        nextFirst = 0;
        nextLast = size +1;
    }

    /** Check and resize the deque. */
    private void resize(T[] prev_deque) {
        double s = size;
        while (s/ prev_deque.length < 0.25 && size > 8){
            T[] a = (T[]) new Object[prev_deque.length / 2];
            copyDeque(prev_deque, a, size);
            prev_deque = a;
        } while(s/ prev_deque.length > 0.5) {
            T[] a = (T[]) new Object[prev_deque.length * 2];
            copyDeque(prev_deque, a, size);
            prev_deque = a;
        }
    }

    /** Minus index by 1. */
    private int minusOne(int index) {
        if (index == 0){
            return deque.length -1;
        } else {
            return index -1;
        }
    }
    /** Increase index by 1. */
    private int addOne(int index) {
        if (index == deque.length - 1) {
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
        System.out.print(deque[tempL]);
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = addOne(nextFirst);
        T temp = (T) deque[nextFirst];
        //deque[nextFirst] = null;
        size --;
        resize(deque);
        return temp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T temp = (T) deque[nextLast];
        //deque[nextLast] = null;
        size --;
        resize(deque);
        return temp;
    }

    public T get(int index) {
        index += addOne(nextFirst);
        if (index < deque.length) {
            return deque[index];
        } else {
            index -= deque.length;
            return deque[index];
        }
    }

    public ArrayDeque(ArrayDeque other) {
        size = other.size;
        deque = (T[]) new Object[other.deque.length];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        copyDeque((T[]) other.deque, deque, size);
    }
}
