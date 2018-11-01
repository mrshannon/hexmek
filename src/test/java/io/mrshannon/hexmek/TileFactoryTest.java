package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileFactoryTest {

    TileFactory factoryA;
    TileFactory factoryB;

    @Before
    public void setUp() throws Exception {
        factoryA = new TileFactory();
        factoryB = new TileFactory();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createTile() {
        // empty terrain id
        var empty = factoryA.createTile("");
        assertEquals("clear:1", empty.getId());
        assertEquals("Clear", empty.getName());
        assertEquals(0, empty.getMovementCost());
        assertEquals(0, empty.getVisibilityCost());

        // clear:1 terrain id
        var clear = factoryA.createTile("clear:1");
        assertEquals("clear:1", clear.getId());
        assertEquals("Clear", clear.getName());
        assertEquals(0, clear.getMovementCost());
        assertEquals(0, clear.getVisibilityCost());

        // woods:1 terrain id
        var lightWoods = factoryA.createTile("woods:1");
        assertEquals("woods:1", lightWoods.getId());
        assertEquals("Light Woods", lightWoods.getName());
        assertEquals(1, lightWoods.getMovementCost());
        assertEquals(1, lightWoods.getVisibilityCost());

        // woods:2 terrain id
        var heavyWoods = factoryA.createTile("woods:2");
        assertEquals("woods:2", heavyWoods.getId());
        assertEquals("Heavy Woods", heavyWoods.getName());
        assertEquals(2, heavyWoods.getMovementCost());
        assertEquals(2, heavyWoods.getVisibilityCost());
    }
}