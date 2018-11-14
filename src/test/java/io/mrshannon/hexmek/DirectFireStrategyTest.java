package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DirectFireStrategyTest {

    DirectFireStrategy lineOfSight;

    @Before
    public void setUp() throws Exception {
        lineOfSight = new DirectFireStrategy();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void modifier() {
        var tiles = new ArrayList<Tile>();
        tiles.add(TileFactory.createTile("woods:2"));
        tiles.add(TileFactory.createTile("clear:1"));
        tiles.add(TileFactory.createTile("clear:1"));
        tiles.add(TileFactory.createTile("clear:1"));
        assertEquals(0, lineOfSight.modifier(tiles));

        tiles.clear();
        tiles.add(TileFactory.createTile("clear:1"));
        tiles.add(TileFactory.createTile("woods:1"));
        tiles.add(TileFactory.createTile("clear:1"));
        tiles.add(TileFactory.createTile("clear:1"));
        assertEquals(1, lineOfSight.modifier(tiles));

        tiles.clear();
        tiles.add(TileFactory.createTile("clear:1"));
        tiles.add(TileFactory.createTile("woods:1"));
        tiles.add(TileFactory.createTile("woods:1"));
        tiles.add(TileFactory.createTile("clear:1"));
        assertEquals(2, lineOfSight.modifier(tiles));

        tiles.clear();
        tiles.add(TileFactory.createTile("clear:1"));
        tiles.add(TileFactory.createTile("woods:1"));
        tiles.add(TileFactory.createTile("woods:1"));
        tiles.add(TileFactory.createTile("woods:1"));
        assertEquals(lineOfSight.OUT_OF_SIGHT, lineOfSight.modifier(tiles));

        tiles.clear();
        tiles.add(TileFactory.createTile("clear:1"));
        tiles.add(TileFactory.createTile("woods:2"));
        tiles.add(TileFactory.createTile("clear:1"));
        tiles.add(TileFactory.createTile("clear:1"));
        assertEquals(2, lineOfSight.modifier(tiles));

        tiles.clear();
        tiles.add(TileFactory.createTile("clear:1"));
        tiles.add(TileFactory.createTile("woods:2"));
        tiles.add(TileFactory.createTile("woods:1"));
        tiles.add(TileFactory.createTile("clear:1"));
        assertEquals(lineOfSight.OUT_OF_SIGHT, lineOfSight.modifier(tiles));

        tiles.clear();
        tiles.add(TileFactory.createTile("clear:1"));
        tiles.add(TileFactory.createTile("woods:2"));
        tiles.add(TileFactory.createTile("woods:2"));
        tiles.add(TileFactory.createTile("clear:1"));
        assertEquals(lineOfSight.OUT_OF_SIGHT, lineOfSight.modifier(tiles));
    }
}