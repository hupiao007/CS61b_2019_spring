import org.junit.Test;
import static org.junit.Assert.*;

public class testUnionFind {
    UnionFind dataUnion = new UnionFind(5);

    @Test
    public void testUnionFind() {
        //test validate().
        //dataUnion.sizeOf(6);

        //test union().
        dataUnion.union(0, 1);
        assertEquals(2, dataUnion.sizeOf(0));

        //test parent
        assertEquals(1, dataUnion.parent(0));

        //test connected
        dataUnion.union(0, 2);
        assertTrue(dataUnion.connected(1, 2));

        //test find
        dataUnion.union(3, 4);
        dataUnion.union(0, 3);
        assertEquals(1, dataUnion.find(3));
    }
}
