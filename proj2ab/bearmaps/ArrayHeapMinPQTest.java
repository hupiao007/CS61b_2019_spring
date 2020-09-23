package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testHeap() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();

        // Add 1, 2, 3 with priority 0.1, 0.2, 0.3
        heap.add(1, 0.1);
        heap.add(2, 0.2);
        heap.add(3, 0.3);

        //test get smallest
        assertTrue(1 == heap.getSmallest());
        assertTrue(1 == heap.removeSmallest());
        assertTrue(2 == heap.getSmallest());
        assertTrue(2 == heap.size());
        assertTrue(heap.contains(2));
        assertFalse(heap.contains(4));

        heap.changePriority(2, 0.5);
        //System.out.println(heap);
        assertFalse(heap.contains(1));
        assertTrue(heap.contains(3));
        heap.add(1, 0.1);
        assertTrue(1 == heap.getSmallest());
    }
}
