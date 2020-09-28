package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void testConstructor() {
        List<Point> lst = new ArrayList<>();
        lst.add(new Point(2, 3));
        lst.add(new Point(4,2));
        lst.add(new Point(4, 5));
        lst.add(new Point(3, 3));
        lst.add(new Point(1, 5));
        lst.add(new Point(4, 4));


        KDTree kd = new KDTree(lst);
        Point expected = new Point(1, 5);
        Point received = kd.nearest(1, 7);
        assertEquals(expected, received);

        Point expectedA = new Point(4, 2);
        Point receivedA = kd.nearest(5, 1);
        assertEquals(expectedA, receivedA);
    }
}
