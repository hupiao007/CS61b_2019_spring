package hw2;
import org.junit.Test;
import static org.junit.Assert.*;

public class PercolationTest {

    @Test
    public void testOpen() {
        Percolation p = new Percolation(5);
        p.open(1,1);
        assertTrue(p.isOpen(1, 1));
        assertFalse(p.isFull(1, 1));
        p.open(0, 1);
        assertTrue(p.isFull(1, 1));
        p.open(2, 1);
        p.open(3, 1);
        p.open(4, 1);
        assertTrue(p.isFull(3, 1));
        assertTrue(p.isFull(4, 1));
        //assertTrue(p.percolates());
        assertTrue(p.isFull(2, 1));
        assertFalse(p.isOpen(2, 2));
        assertFalse(p.isFull(2, 3));
    }
}
