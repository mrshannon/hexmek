package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HexTest {

    private Hex hex;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getColumn() {
        assertEquals(3, (new Hex(3,2)).getColumn());
        assertEquals(2, (new Hex(2,3)).getColumn());
        assertEquals(-3, (new Hex(-3,2)).getColumn());
        assertEquals(0, (new Hex(0,2)).getColumn());
    }

    @Test
    public void getRow() {
        assertEquals(2, (new Hex(3,2)).getRow());
        assertEquals(3, (new Hex(2,3)).getRow());
        assertEquals(-2, (new Hex(3,-2)).getRow());
        assertEquals(0, (new Hex(3,0)).getRow());
    }

    @Test
    public void add() {
        hex = new Hex(0,0);
        hex.add(new Hex(1,0));
        assertEquals(1, hex.getColumn());
        assertEquals(0, hex.getRow());

        hex = new Hex(0,0);
        hex.add(new Hex(-1,0));
        assertEquals(-1, hex.getColumn());
        assertEquals(0, hex.getRow());

        hex = new Hex(0,0);
        hex.add(new Hex(0,1));
        assertEquals(0, hex.getColumn());
        assertEquals(1, hex.getRow());

        hex = new Hex(0,0);
        hex.add(new Hex(0,-1));
        assertEquals(0, hex.getColumn());
        assertEquals(-1, hex.getRow());

        hex = new Hex(0,0);
        hex.add(new Hex(1,1));
        assertEquals(1, hex.getColumn());
        assertEquals(1, hex.getRow());

        hex = new Hex(0,0);
        hex.add(new Hex(1,-1));
        assertEquals(1, hex.getColumn());
        assertEquals(-1, hex.getRow());

        hex = new Hex(0,0);
        hex.add(new Hex(-1,1));
        assertEquals(-1, hex.getColumn());
        assertEquals(1, hex.getRow());

        hex = new Hex(0,0);
        hex.add(new Hex(-1,-1));
        assertEquals(-1, hex.getColumn());
        assertEquals(-1, hex.getRow());

        hex = new Hex(0,-1);
        hex.add(new Hex(0,1));
        assertEquals(0, hex.getColumn());
        assertEquals(0, hex.getRow());

        hex = new Hex(1,0);
        hex.add(new Hex(-1,1));
        assertEquals(0, hex.getColumn());
        assertEquals(0, hex.getRow());

        hex = new Hex(1,1);
        hex.add(new Hex(-1,0));
        assertEquals(0, hex.getColumn());
        assertEquals(0, hex.getRow());
    }

    @Test
    public void translateNorth() {
        hex = new Hex(0,0);
        hex.translateNorth(1);
        assertEquals(0, hex.getColumn());
        assertEquals(-1, hex.getRow());

        hex = new Hex(1,1);
        hex.translateNorth(1);
        assertEquals(1, hex.getColumn());
        assertEquals(0, hex.getRow());

        hex = new Hex(0,3);
        hex.translateNorth(3);
        assertEquals(0, hex.getColumn());
        assertEquals(0, hex.getRow());
    }

    @Test
    public void translateNorthEast() {
        hex = new Hex(0,0);
        hex.translateNorthEast(1);
        assertEquals(1, hex.getColumn());
        assertEquals(0, hex.getRow());

        hex = new Hex(1,1);
        hex.translateNorthEast(1);
        assertEquals(2, hex.getColumn());
        assertEquals(0, hex.getRow());

        hex = new Hex(-3,2);
        hex.translateNorthEast(3);
        assertEquals(0, hex.getColumn());
        assertEquals(0, hex.getRow());
    }

    @Test
    public void translateSouthEast() {
        hex = new Hex(0,0);
        hex.translateSouthEast(1);
        assertEquals(1, hex.getColumn());
        assertEquals(1, hex.getRow());

        hex = new Hex(1,1);
        hex.translateSouthEast(1);
        assertEquals(2, hex.getColumn());
        assertEquals(1, hex.getRow());

        hex = new Hex(-3,-1);
        hex.translateSouthEast(3);
        assertEquals(0, hex.getColumn());
        assertEquals(0, hex.getRow());
    }

    @Test
    public void translateSouth() {
        hex = new Hex(0,0);
        hex.translateSouth(1);
        assertEquals(0, hex.getColumn());
        assertEquals(1, hex.getRow());

        hex = new Hex(1,1);
        hex.translateSouth(1);
        assertEquals(1, hex.getColumn());
        assertEquals(2, hex.getRow());

        hex = new Hex(0,-3);
        hex.translateSouth(3);
        assertEquals(0, hex.getColumn());
        assertEquals(0, hex.getRow());
    }

    @Test
    public void translateSouthWest() {
        hex = new Hex(0,0);
        hex.translateSouthWest(1);
        assertEquals(-1, hex.getColumn());
        assertEquals(1, hex.getRow());

        hex = new Hex(1,1);
        hex.translateSouthWest(1);
        assertEquals(0, hex.getColumn());
        assertEquals(1, hex.getRow());

        hex = new Hex(3,-1);
        hex.translateSouthWest(3);
        assertEquals(0, hex.getColumn());
        assertEquals(0, hex.getRow());
    }

    @Test
    public void translateNorthWest() {
        hex = new Hex(0,0);
        hex.translateNorthWest(1);
        assertEquals(-1, hex.getColumn());
        assertEquals(0, hex.getRow());

        hex = new Hex(1,1);
        hex.translateNorthWest(1);
        assertEquals(0, hex.getColumn());
        assertEquals(0, hex.getRow());

        hex = new Hex(3,2);
        hex.translateNorthWest(3);
        assertEquals(0, hex.getColumn());
        assertEquals(0, hex.getRow());
    }

    @Test
    public void northNeighbor() {
        hex = new Hex(0,0);
        assertEquals(0, hex.northNeighbor().getColumn());
        assertEquals(-1, hex.northNeighbor().getRow());

        hex = new Hex(1,1);
        assertEquals(1, hex.northNeighbor().getColumn());
        assertEquals(0, hex.northNeighbor().getRow());
    }

    @Test
    public void northEastNeighbor() {
        hex = new Hex(0,0);
        assertEquals(1, hex.northEastNeighbor().getColumn());
        assertEquals(0, hex.northEastNeighbor().getRow());

        hex = new Hex(1,1);
        assertEquals(2, hex.northEastNeighbor().getColumn());
        assertEquals(0, hex.northEastNeighbor().getRow());
    }

    @Test
    public void southEastNeighbor() {
        hex = new Hex(0,0);
        assertEquals(1, hex.southEastNeighbor().getColumn());
        assertEquals(1, hex.southEastNeighbor().getRow());

        hex = new Hex(1,1);
        assertEquals(2, hex.southEastNeighbor().getColumn());
        assertEquals(1, hex.southEastNeighbor().getRow());
    }

    @Test
    public void southNeighbor() {
        hex = new Hex(0,0);
        assertEquals(0, hex.southNeighbor().getColumn());
        assertEquals(1, hex.southNeighbor().getRow());

        hex = new Hex(1,1);
        assertEquals(1, hex.southNeighbor().getColumn());
        assertEquals(2, hex.southNeighbor().getRow());
    }

    @Test
    public void southWestNeighbor() {
        hex = new Hex(0,0);
        assertEquals(-1, hex.southWestNeighbor().getColumn());
        assertEquals(1, hex.southWestNeighbor().getRow());

        hex = new Hex(1,1);
        assertEquals(0, hex.southWestNeighbor().getColumn());
        assertEquals(1, hex.southWestNeighbor().getRow());
    }

    @Test
    public void northWestNeighbor() {
        hex = new Hex(0,0);
        assertEquals(-1, hex.northWestNeighbor().getColumn());
        assertEquals(0, hex.northWestNeighbor().getRow());

        hex = new Hex(1,1);
        assertEquals(0, hex.northWestNeighbor().getColumn());
        assertEquals(0, hex.northWestNeighbor().getRow());
    }

    @Test
    public void neighbors() {
        int columns[];
        int rows[];
        int i;

        columns = new int[]{0, 1, 1, 0, -1, -1};
        rows = new int[]{-1, 0, 1, 1, 1, 0};
        hex = new Hex(0, 0);
        i = 0;
        for (var hex : hex.neighbors()) {
            assertEquals(columns[i], hex.getColumn());
            assertEquals(rows[i], hex.getRow());
            ++i;
        }

        columns = new int[]{1, 2, 2, 1, 0, 0};
        rows = new int[]{0, 0, 1, 2, 1, 0};
        hex = new Hex(1, 1);
        i = 0;
        for (var hex : hex.neighbors()) {
            assertEquals(columns[i], hex.getColumn());
            assertEquals(rows[i], hex.getRow());
            ++i;
        }
    }

    @Test
    public void distanceTo() {
        hex = new Hex(0, 0);
        assertEquals(3, hex.distanceTo(new Hex(2,2)));
        assertEquals(3, hex.distanceTo(new Hex(-3,0)));
        assertEquals(2, hex.distanceTo(new Hex(-1,2)));
        assertEquals(2, hex.distanceTo(new Hex(1,-1)));
    }

    @Test
    public void lineTo() {
        int i;
        ArrayList<Hex> hexes;
        hex = new Hex(0, 0);

        i = 0;
        hexes = new ArrayList<>();
        hexes.add(new Hex(0, 0));
        hexes.add(new Hex(1, 1));
        hexes.add(new Hex(2, 1));
        hexes.add(new Hex(3, 2));
        for (var h : hex.lineTo(new Hex(3,2))) {
            assertEquals(hexes.get(i).getColumn(), h.getColumn());
            assertEquals(hexes.get(i).getRow(), h.getRow());
            ++i;
        }

        i = 0;
        hexes = new ArrayList<>();
        hexes.add(new Hex(-1, 4));
        hexes.add(new Hex(-1, 3));
        hexes.add(new Hex(0, 2));
        hexes.add(new Hex(0, 1));
        hexes.add(new Hex(1, 1));
        hexes.add(new Hex(1, 0));
        hexes.add(new Hex(2, -1));
        hexes.add(new Hex(2, -2));
        hex = new Hex(-1, 4);
        for (var h : hex.lineTo(new Hex(2,-2))) {
            System.out.println(hexes.get(i));
            System.out.println(h);
            assertEquals(hexes.get(i).getColumn(), h.getColumn());
            assertEquals(hexes.get(i).getRow(), h.getRow());
            ++i;
        }
    }

    @Test
    public void testEquals() {
        hex = new Hex(40,20);
        assertTrue(hex.equals(new Hex(40, 20)));
        assertFalse(hex.equals(new Hex(41, 20)));
        assertFalse(hex.equals(new Hex(40, 21)));
    }

    @Test
    public void testHashCode() {
        hex = new Hex(56, 53);
        assertEquals(3670069, hex.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("0,0", (new Hex(0, 0)).toString());
        assertEquals("20,99", (new Hex(20, 99)).toString());
        assertEquals("-20,-99", (new Hex(-20, -99)).toString());
    }
}