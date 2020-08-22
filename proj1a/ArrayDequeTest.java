/** Performs some basic linked list tests. */
public class ArrayDequeTest {
    public static void main(String[] args) {
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
    }
}
