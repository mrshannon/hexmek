package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IndirectFireStrategyTest {

    IndirectFireStrategy lineOfSight;

    @Before
    public void setUp() throws Exception {
        lineOfSight = new IndirectFireStrategy();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void modifier() {
        ArrayList<Tile> tiles = new ArrayList<>();
        assertEquals(0, lineOfSight.modifier(tiles));
    }

}
