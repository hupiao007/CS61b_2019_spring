import java.awt.*;

/** Performs some basic linked list tests. */
public class ArrayDequeTest {
    public static void main(String[] args) {
        /**
        ArrayDeque<Integer> test1 = new ArrayDeque<Integer>();
        test1.isEmpty();
        test1.addFirst(1);
        test1.size();
        test1.addLast(2);
        test1.addLast(3);
        test1.addFirst(4);
        test1.addLast(5);
        test1.printDeque();
        test1.size();
        System.out.println(test1.size());
        test1.printDeque();
        test1.addFirst(6);
        test1.printDeque();
        test1.addFirst(7);
        test1.addLast(8);
        test1.printDeque();
         */
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
        ArrayDeque.addFirst(0);
        ArrayDeque.get(0);
        ArrayDeque.removeFirst();
        ArrayDeque.addFirst(3);
        ArrayDeque.get(0);
        ArrayDeque.get(0);
        ArrayDeque.get(0);
        ArrayDeque.addLast(7);
        ArrayDeque.addFirst(8);
        ArrayDeque.removeFirst();
        ArrayDeque.addLast(10);
        ArrayDeque.addFirst(11);
        ArrayDeque.removeFirst();
        ArrayDeque.get(0);
        ArrayDeque.addLast(14);
        ArrayDeque.removeFirst();
        ArrayDeque.addLast(16);
        ArrayDeque.addFirst(17);
        ArrayDeque.printDeque();

    }
}
