package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.Hex;
import io.mrshannon.hexmek.models.MapLoader;
import io.mrshannon.hexmek.models.Tile;
import io.mrshannon.hexmek.models.TileFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MapLoaderTest {

    MapLoader defaultMapLoader;
    Tile clear;
    Tile lightWoods;
    Tile heavyWoods;

    @Before
    public void setUp() throws Exception {
        defaultMapLoader = new MapLoader("default");
        clear = TileFactory.createTile("clear:1");
        lightWoods = TileFactory.createTile("woods:1");
        heavyWoods = TileFactory.createTile("woods:2");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createMap() throws IOException {
        var defaultMap = defaultMapLoader.createMap();
        assertEquals(15, defaultMap.getWidth());
        assertEquals(17, defaultMap.getHeight());

        assertEquals(lightWoods, defaultMap.getTile(new Hex(1, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 3)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 4)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 11)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 13)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 16)));
        assertEquals(clear, defaultMap.getTile(new Hex(1, 17)));

        assertEquals(lightWoods, defaultMap.getTile(new Hex(2, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 3)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 4)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 10)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(2, 11)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(2, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 13)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(2, 16)));

        assertEquals(clear, defaultMap.getTile(new Hex(3, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 3)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 4)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 11)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(3, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 13)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 16)));
        assertEquals(clear, defaultMap.getTile(new Hex(3, 17)));

        assertEquals(clear, defaultMap.getTile(new Hex(4, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 2)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(4, 3)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 4)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 10)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(4, 11)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(4, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 13)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(4, 16)));

        assertEquals(clear, defaultMap.getTile(new Hex(5, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 2)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(5, 3)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(5, 4)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 11)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(5, 12)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(5, 13)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 16)));
        assertEquals(clear, defaultMap.getTile(new Hex(5, 17)));

        assertEquals(clear, defaultMap.getTile(new Hex(6, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(6, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(6, 3)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(6, 4)));
        assertEquals(clear, defaultMap.getTile(new Hex(6, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(6, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(6, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(6, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(6, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(6, 10)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(6, 11)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(6, 12)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(6, 13)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(6, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(6, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(6, 16)));

        assertEquals(clear, defaultMap.getTile(new Hex(7, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 3)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 4)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 11)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 13)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(7, 14)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(7, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 16)));
        assertEquals(clear, defaultMap.getTile(new Hex(7, 17)));

        assertEquals(clear, defaultMap.getTile(new Hex(8, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 3)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 4)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 11)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 12)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(8, 13)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(8, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(8, 16)));

        assertEquals(clear, defaultMap.getTile(new Hex(9, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 3)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(9, 4)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(9, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 11)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 13)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(9, 14)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(9, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 16)));
        assertEquals(clear, defaultMap.getTile(new Hex(9, 17)));

        assertEquals(clear, defaultMap.getTile(new Hex(10, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 2)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(10, 3)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(10, 4)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(10, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 11)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 13)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(10, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(10, 16)));

        assertEquals(clear, defaultMap.getTile(new Hex(11, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 3)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(11, 4)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(11, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 7)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 8)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(11, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 11)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 13)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 16)));
        assertEquals(clear, defaultMap.getTile(new Hex(11, 17)));

        assertEquals(clear, defaultMap.getTile(new Hex(12, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 3)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(12, 4)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(12, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 7)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(12, 8)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(12, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 11)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 13)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(12, 16)));

        assertEquals(clear, defaultMap.getTile(new Hex(13, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 3)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(13, 4)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(13, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 7)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(13, 8)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(13, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 11)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 13)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 15)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 16)));
        assertEquals(clear, defaultMap.getTile(new Hex(13, 17)));

        assertEquals(clear, defaultMap.getTile(new Hex(14, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 3)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(14, 4)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 6)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 7)));
        assertEquals(heavyWoods, defaultMap.getTile(new Hex(14, 8)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(14, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 11)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 13)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(14, 15)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(14, 16)));

        assertEquals(clear, defaultMap.getTile(new Hex(15, 1)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 2)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 3)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 4)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 5)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 6)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(15, 7)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(15, 8)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 9)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 10)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 11)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 12)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 13)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 14)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 15)));
        assertEquals(lightWoods, defaultMap.getTile(new Hex(15, 16)));
        assertEquals(clear, defaultMap.getTile(new Hex(15, 17)));
    }
}