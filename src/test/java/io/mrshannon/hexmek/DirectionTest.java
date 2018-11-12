package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    private Direction north;
    private Direction northEast;
    private Direction southEast;
    private Direction south;
    private Direction southWest;
    private Direction northWest;

    @Before
    public void setUp() throws Exception {
        north = new North();
        northEast = new NorthEast();
        southEast = new SouthEast();
        south = new South();
        southWest = new SouthWest();
        northWest = new NorthWest();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testEquals() {
        assertEquals(north, new North());
        assertNotEquals(north, new NorthEast());
        assertNotEquals(north, new SouthEast());
        assertNotEquals(north, new South());
        assertNotEquals(north, new SouthWest());
        assertNotEquals(north, new NorthWest());

        assertEquals(northEast, new NorthEast());
        assertNotEquals(northEast, new SouthEast());
        assertNotEquals(northEast, new South());
        assertNotEquals(northEast, new SouthWest());
        assertNotEquals(northEast, new NorthWest());
        assertNotEquals(northEast, new North());

        assertEquals(southEast, new SouthEast());
        assertNotEquals(southEast, new South());
        assertNotEquals(southEast, new SouthWest());
        assertNotEquals(southEast, new NorthWest());
        assertNotEquals(southEast, new North());
        assertNotEquals(southEast, new NorthEast());

        assertEquals(south, new South());
        assertNotEquals(south, new SouthWest());
        assertNotEquals(south, new NorthWest());
        assertNotEquals(south, new North());
        assertNotEquals(south, new NorthEast());
        assertNotEquals(south, new SouthEast());

        assertEquals(southWest, new SouthWest());
        assertNotEquals(southWest, new NorthWest());
        assertNotEquals(southWest, new North());
        assertNotEquals(southWest, new NorthEast());
        assertNotEquals(southWest, new SouthEast());
        assertNotEquals(southWest, new South());

        assertEquals(northWest, new NorthWest());
        assertNotEquals(northWest, new North());
        assertNotEquals(northWest, new NorthEast());
        assertNotEquals(northWest, new SouthEast());
        assertNotEquals(northWest, new South());
        assertNotEquals(northWest, new SouthWest());
    }

    @Test
    public void rotateRight() {
        assertEquals(new NorthEast(), north.rotateRight());
        assertEquals(new SouthEast(), northEast.rotateRight());
        assertEquals(new South(), southEast.rotateRight());
        assertEquals(new SouthWest(), south.rotateRight());
        assertEquals(new NorthWest(), southWest.rotateRight());
        assertEquals(new North(), northWest.rotateRight());
    }

    @Test
    public void rotateLeft() {
        assertEquals(new NorthWest(), north.rotateLeft());
        assertEquals(new North(), northEast.rotateLeft());
        assertEquals(new NorthEast(), southEast.rotateLeft());
        assertEquals(new SouthEast(), south.rotateLeft());
        assertEquals(new South(), southWest.rotateLeft());
        assertEquals(new SouthWest(), northWest.rotateLeft());
    }

    @Test
    public void testToString() {
        assertEquals("North", north.toString());
        assertEquals("North East", northEast.toString());
        assertEquals("South East", southEast.toString());
        assertEquals("South", south.toString());
        assertEquals("South West", southWest.toString());
        assertEquals("North West", northWest.toString());
    }

    @Test
    public void apply1() {
        Hex hex;

        // even column
        hex = new Hex(4,7);
        assertEquals(new Hex(4, 6), north.apply(hex));

        hex = new Hex(4,7);
        assertEquals(new Hex(5, 7), northEast.apply(hex));

        hex = new Hex(4,7);
        assertEquals(new Hex(5, 8), southEast.apply(hex));

        hex = new Hex(4,7);
        assertEquals(new Hex(4, 8), south.apply(hex));

        hex = new Hex(4,7);
        assertEquals(new Hex(3, 8), southWest.apply(hex));

        hex = new Hex(4,7);
        assertEquals(new Hex(3, 7), northWest.apply(hex));


        // odd column
        hex = new Hex(5,7);
        assertEquals(new Hex(5, 6), north.apply(hex));

        hex = new Hex(5,7);
        assertEquals(new Hex(6, 6), northEast.apply(hex));

        hex = new Hex(5,7);
        assertEquals(new Hex(6, 7), southEast.apply(hex));

        hex = new Hex(5,7);
        assertEquals(new Hex(5, 8), south.apply(hex));

        hex = new Hex(5,7);
        assertEquals(new Hex(4, 7), southWest.apply(hex));

        hex = new Hex(5,7);
        northWest.apply(hex);
        assertEquals(new Hex(4, 6), northWest.apply(hex));
    }

    @Test
    public void apply2() {
        Hex hex;

        hex = new Hex(0,0);
        hex = north.apply(1, hex);
        assertEquals(new Hex(0, -1), hex);
        hex = northEast.apply(2, hex);
        assertEquals(new Hex(2, -2), hex);
        hex = southEast.apply(3, hex);
        assertEquals(new Hex(5, 0), hex);
        hex = south.apply(4, hex);
        assertEquals(new Hex(5, 4), hex);
        hex = southWest.apply(5, hex);
        assertEquals(new Hex(0, 6), hex);
        hex = northWest.apply(5, hex);
        assertEquals(new Hex(-5, 4), hex);
    }
}