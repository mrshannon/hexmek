package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.Hex;
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
        assertEquals(new Hex(1, 0), hex.add(new Hex(1, 0)));

        hex = new Hex(0,0);
        assertEquals(new Hex(-1, 0), hex.add(new Hex(-1, 0)));

        hex = new Hex(0,0);
        assertEquals(new Hex(0, 1), hex.add(new Hex(0, 1)));

        hex = new Hex(0,0);
        assertEquals(new Hex(0, -1), hex.add(new Hex(0, -1)));

        hex = new Hex(0,0);
        assertEquals(new Hex(1, 1), hex.add(new Hex(1, 1)));

        hex = new Hex(0,0);
        assertEquals(new Hex(1, -1), hex.add(new Hex(1, -1)));

        hex = new Hex(0,0);
        assertEquals(new Hex(-1, 1), hex.add(new Hex(-1, 1)));

        hex = new Hex(0,0);
        assertEquals(new Hex(-1, -1), hex.add(new Hex(-1, -1)));

        hex = new Hex(0,-1);
        assertEquals(new Hex(0, 0), hex.add(new Hex(0, 1)));

        hex = new Hex(1,0);
        assertEquals(new Hex(0, 0), hex.add(new Hex(-1, 1)));

        hex = new Hex(1,1);
        assertEquals(new Hex(0, 0), hex.add(new Hex(-1, 0)));
    }

    @Test
    public void north() {
        hex = new Hex(0,0);
        assertEquals(new Hex(0, -1), hex.north());
        assertEquals(new Hex(0, -1), hex.north(1));
        assertEquals(new Hex(0, -3), hex.north(3));
        assertEquals(new Hex(0, 1), hex.north(-1));

        hex = new Hex(1,1);
        assertEquals(new Hex(1, 0), hex.north());
        assertEquals(new Hex(1, 0), hex.north(1));
        assertEquals(new Hex(1, -2), hex.north(3));
        assertEquals(new Hex(1, 2), hex.north(-1));
    }

    @Test
    public void northEast() {
        hex = new Hex(0,0);
        assertEquals(new Hex(1, 0), hex.northEast());
        assertEquals(new Hex(1, 0), hex.northEast(1));
        assertEquals(new Hex(3, -1), hex.northEast(3));
        assertEquals(new Hex(-1, 1), hex.northEast(-1));

        hex = new Hex(1,1);
        assertEquals(new Hex(2, 0), hex.northEast());
        assertEquals(new Hex(2, 0), hex.northEast(1));
        assertEquals(new Hex(4, -1), hex.northEast(3));
        assertEquals(new Hex(0, 1), hex.northEast(-1));
    }

    @Test
    public void southEast() {
        hex = new Hex(0,0);
        assertEquals(new Hex(1, 1), hex.southEast());
        assertEquals(new Hex(1, 1), hex.southEast(1));
        assertEquals(new Hex(3, 2), hex.southEast(3));
        assertEquals(new Hex(-1, 0), hex.southEast(-1));

        hex = new Hex(1,1);
        assertEquals(new Hex(2, 1), hex.southEast());
        assertEquals(new Hex(2, 1), hex.southEast(1));
        assertEquals(new Hex(4, 2), hex.southEast(3));
        assertEquals(new Hex(0, 0), hex.southEast(-1));
    }

    @Test
    public void south() {
        hex = new Hex(0,0);
        assertEquals(new Hex(0, 1), hex.south());
        assertEquals(new Hex(0, 1), hex.south(1));
        assertEquals(new Hex(0, 3), hex.south(3));
        assertEquals(new Hex(0, -1), hex.south(-1));

        hex = new Hex(1,1);
        assertEquals(new Hex(1, 2), hex.south());
        assertEquals(new Hex(1, 2), hex.south(1));
        assertEquals(new Hex(1, 4), hex.south(3));
        assertEquals(new Hex(1, 0), hex.south(-1));
    }

    @Test
    public void southWest() {
        hex = new Hex(0,0);
        assertEquals(new Hex(-1, 1), hex.southWest());
        assertEquals(new Hex(-1, 1), hex.southWest(1));
        assertEquals(new Hex(-3, 2), hex.southWest(3));
        assertEquals(new Hex(1, 0), hex.southWest(-1));

        hex = new Hex(1,1);
        assertEquals(new Hex(0, 1), hex.southWest());
        assertEquals(new Hex(0, 1), hex.southWest(1));
        assertEquals(new Hex(-2, 2), hex.southWest(3));
        assertEquals(new Hex(2, 0), hex.southWest(-1));
    }

    @Test
    public void northWest() {
        hex = new Hex(0,0);
        assertEquals(new Hex(-1, 0), hex.northWest());
        assertEquals(new Hex(-1, 0), hex.northWest(1));
        assertEquals(new Hex(-3, -1), hex.northWest(3));
        assertEquals(new Hex(1, 1), hex.northWest(-1));

        hex = new Hex(1,1);
        assertEquals(new Hex(0, 0), hex.northWest());
        assertEquals(new Hex(0, 0), hex.northWest(1));
        assertEquals(new Hex(-2, -1), hex.northWest(3));
        assertEquals(new Hex(2, 1), hex.northWest(-1));
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
            assertEquals(hexes.get(i).getColumn(), h.getColumn());
            assertEquals(hexes.get(i).getRow(), h.getRow());
            ++i;
        }
    }

    @Test
    public void testEquals() {
        hex = new Hex(40,20);
        assertEquals(new Hex(40, 20), hex);
        assertNotEquals(new Hex(41, 20), hex);
        assertNotEquals(new Hex(40, 21), hex);
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