package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HexMapTest {
    private HexMap clearMap;
    private HexMap woodsMap;

    @Before
    public void setUp() throws Exception {
        clearMap = new HexMap(2, 3, TileFactory.createTile("clear:1"));

        var tiles = new HashMap<Hex, Tile>();
        tiles.put(new Hex(1, 1), TileFactory.createTile("woods:1"));
        tiles.put(new Hex(3, 2), TileFactory.createTile("woods:2"));
        tiles.put(new Hex(2, 1), TileFactory.createTile("woods:1"));
        woodsMap = new HexMap(3, 2, TileFactory.createTile("clear:1"), tiles);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTile() {
        clearMap.getTile(new Hex(1, 1)).equals(TileFactory.createTile("clear:1"));
        clearMap.getTile(new Hex(1, 2)).equals(TileFactory.createTile("clear:1"));
        clearMap.getTile(new Hex(1, 3)).equals(TileFactory.createTile("clear:1"));
        clearMap.getTile(new Hex(2, 1)).equals(TileFactory.createTile("clear:1"));
        clearMap.getTile(new Hex(2, 2)).equals(TileFactory.createTile("clear:1"));

        woodsMap.getTile(new Hex(1, 1)).equals(TileFactory.createTile("woods:1"));
        woodsMap.getTile(new Hex(1, 2)).equals(TileFactory.createTile("clear:1"));
        woodsMap.getTile(new Hex(2, 1)).equals(TileFactory.createTile("woods:1"));
        woodsMap.getTile(new Hex(3, 1)).equals(TileFactory.createTile("clear:1"));
        woodsMap.getTile(new Hex(3, 2)).equals(TileFactory.createTile("woods:2"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getTileException() {
        woodsMap.getTile(new Hex(2, 2));
    }

    @Test
    public void isHexValid() {
        assertFalse(clearMap.isHexValid(new Hex(0, 1)));
        assertFalse(clearMap.isHexValid(new Hex(1, 0)));
        assertTrue(clearMap.isHexValid(new Hex(1, 1)));
        assertTrue(clearMap.isHexValid(new Hex(1, 2)));
        assertTrue(clearMap.isHexValid(new Hex(1, 3)));
        assertFalse(clearMap.isHexValid(new Hex(1, 4)));
        assertFalse(clearMap.isHexValid(new Hex(2, 0)));
        assertTrue(clearMap.isHexValid(new Hex(2, 1)));
        assertTrue(clearMap.isHexValid(new Hex(2, 2)));
        assertFalse(clearMap.isHexValid(new Hex(2, 3)));
        assertFalse(clearMap.isHexValid(new Hex(3, 1)));


        assertFalse(woodsMap.isHexValid(new Hex(0, 1)));
        assertFalse(woodsMap.isHexValid(new Hex(1, 0)));
        assertTrue(woodsMap.isHexValid(new Hex(1, 1)));
        assertTrue(woodsMap.isHexValid(new Hex(1, 2)));
        assertFalse(woodsMap.isHexValid(new Hex(1, 3)));
        assertFalse(woodsMap.isHexValid(new Hex(2, 0)));
        assertTrue(woodsMap.isHexValid(new Hex(2, 1)));
        assertFalse(woodsMap.isHexValid(new Hex(2, 2)));
        assertFalse(woodsMap.isHexValid(new Hex(3, 0)));
        assertTrue(woodsMap.isHexValid(new Hex(3, 1)));
        assertTrue(woodsMap.isHexValid(new Hex(3, 2)));
        assertFalse(woodsMap.isHexValid(new Hex(3, 3)));
        assertFalse(woodsMap.isHexValid(new Hex(4, 1)));
    }
}