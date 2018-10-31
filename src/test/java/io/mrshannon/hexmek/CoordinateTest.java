package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CoordinateTest {

    Coordinate evenColumn;
    Coordinate oddColumn;

    @Before
    public void setUp() throws Exception {
        evenColumn = new Coordinate(4, 7);
        oddColumn = new Coordinate(5, 7);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getColumn() {
        assertEquals(4, evenColumn.getColumn());
        assertEquals(5, oddColumn.getColumn());
    }

    @Test
    public void getRow() {
        assertEquals(7, evenColumn.getRow());
        assertEquals(7, oddColumn.getRow());
    }

    @Test
    public void neighbors() {
        List<Coordinate> neighbors;

        neighbors = evenColumn.neighbors();
        assertEquals(6, neighbors.size());

        // left in to catch overload error (instead of override)
        assertTrue(neighbors.contains(new Coordinate(4,6)));
        assertTrue(neighbors.contains(new Coordinate(5,7)));
        assertTrue(neighbors.contains(new Coordinate(5,8)));
        assertTrue(neighbors.contains(new Coordinate(4,8)));
        assertTrue(neighbors.contains(new Coordinate(3,8)));
        assertTrue(neighbors.contains(new Coordinate(3,7)));

        assertTrue(neighbors.get(0).equals(new Coordinate(4, 6)));
        assertTrue(neighbors.get(1).equals(new Coordinate(5, 7)));
        assertTrue(neighbors.get(2).equals(new Coordinate(5, 8)));
        assertTrue(neighbors.get(3).equals(new Coordinate(4, 8)));
        assertTrue(neighbors.get(4).equals(new Coordinate(3, 8)));
        assertTrue(neighbors.get(5).equals(new Coordinate(3, 7)));

        neighbors = oddColumn.neighbors();
        assertEquals(6, neighbors.size());

        // left in to catch overload error (instead of override)
        assertTrue(neighbors.contains(new Coordinate(5,6)));
        assertTrue(neighbors.contains(new Coordinate(6,6)));
        assertTrue(neighbors.contains(new Coordinate(6,7)));
        assertTrue(neighbors.contains(new Coordinate(5,8)));
        assertTrue(neighbors.contains(new Coordinate(4,7)));
        assertTrue(neighbors.contains(new Coordinate(4,6)));

        assertTrue(neighbors.get(0).equals(new Coordinate(5, 6)));
        assertTrue(neighbors.get(1).equals(new Coordinate(6, 6)));
        assertTrue(neighbors.get(2).equals(new Coordinate(6, 7)));
        assertTrue(neighbors.get(3).equals(new Coordinate(5, 8)));
        assertTrue(neighbors.get(4).equals(new Coordinate(4, 7)));
        assertTrue(neighbors.get(5).equals(new Coordinate(4, 6)));
    }

    @Test
    public void equals() {
        assertTrue(evenColumn.equals(new Coordinate(4, 7)));
        assertTrue(oddColumn.equals(new Coordinate(5, 7)));

        assertFalse(evenColumn.equals(new Coordinate(5, 7)));
        assertFalse(evenColumn.equals(new Coordinate(4, 8)));

        assertFalse(oddColumn.equals(new Coordinate(6, 7)));
        assertFalse(oddColumn.equals(new Coordinate(5, 8)));
    }

    @Test
    public void testToString() {
        assertEquals("0407", evenColumn.toString());
        assertEquals("0507", oddColumn.toString());
        assertEquals("1317", (new Coordinate(13, 17)).toString());
    }
}