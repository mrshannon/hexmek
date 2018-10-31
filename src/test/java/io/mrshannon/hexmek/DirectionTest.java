package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    private Direction direction;
    private Direction north;
    private Direction northEast;
    private Direction southEast;
    private Direction south;
    private Direction southWest;
    private Direction northWest;

    @Before
    public void setUp() throws Exception {
        direction = new Direction();
        north = new Direction(Direction.Enum.NORTH);
        northEast = new Direction(Direction.Enum.NORTHEAST);
        southEast = new Direction(Direction.Enum.SOUTHEAST);
        south = new Direction(Direction.Enum.SOUTH);
        southWest = new Direction(Direction.Enum.SOUTHWEST);
        northWest = new Direction(Direction.Enum.NORTHWEST);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDirectionEnum() {
        assertEquals(Direction.Enum.NORTH, direction.getDirectionEnum());
        assertEquals(Direction.Enum.NORTH, north.getDirectionEnum());
        assertEquals(Direction.Enum.NORTHEAST, northEast.getDirectionEnum());
        assertEquals(Direction.Enum.SOUTHEAST, southEast.getDirectionEnum());
        assertEquals(Direction.Enum.SOUTH, south.getDirectionEnum());
        assertEquals(Direction.Enum.SOUTHWEST, southWest.getDirectionEnum());
        assertEquals(Direction.Enum.NORTHWEST, northWest.getDirectionEnum());
    }

    @Test
    public void rotateRight() {
        assertTrue(direction.equals(north));
        direction.rotateRight();
        assertTrue(direction.equals(northEast));
        direction.rotateRight();
        assertTrue(direction.equals(southEast));
        direction.rotateRight();
        assertTrue(direction.equals(south));
        direction.rotateRight();
        assertTrue(direction.equals(southWest));
        direction.rotateRight();
        assertTrue(direction.equals(northWest));
        direction.rotateRight();
        assertTrue(direction.equals(north));
    }

    @Test
    public void rotateLeft() {
        assertTrue(direction.equals(north));
        direction.rotateLeft();
        assertTrue(direction.equals(northWest));
        direction.rotateLeft();
        assertTrue(direction.equals(southWest));
        direction.rotateLeft();
        assertTrue(direction.equals(south));
        direction.rotateLeft();
        assertTrue(direction.equals(southEast));
        direction.rotateLeft();
        assertTrue(direction.equals(northEast));
        direction.rotateLeft();
        assertTrue(direction.equals(north));
    }

    @Test
    public void equals() {
        assertTrue(direction.equals(new Direction(Direction.Enum.NORTH)));
        assertTrue(north.equals(new Direction(Direction.Enum.NORTH)));
        assertTrue(northEast.equals(new Direction(Direction.Enum.NORTHEAST)));
        assertTrue(southEast.equals(new Direction(Direction.Enum.SOUTHEAST)));
        assertTrue(south.equals(new Direction(Direction.Enum.SOUTH)));
        assertTrue(southWest.equals(new Direction(Direction.Enum.SOUTHWEST)));
        assertTrue(northWest.equals(new Direction(Direction.Enum.NORTHWEST)));

        assertFalse(north.equals(new Direction(Direction.Enum.NORTHEAST)));
        assertFalse(northEast.equals(new Direction(Direction.Enum.SOUTHEAST)));
        assertFalse(southEast.equals(new Direction(Direction.Enum.SOUTH)));
        assertFalse(south.equals(new Direction(Direction.Enum.SOUTHWEST)));
        assertFalse(southWest.equals(new Direction(Direction.Enum.NORTHWEST)));
        assertFalse(northWest.equals(new Direction(Direction.Enum.NORTH)));
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
}