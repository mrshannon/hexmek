package io.mrshannon.hexmek;

import java.util.HashMap;

/**
 * A factory for the Tile class that uses the Flyweight pattern to allow an entire map of tiles to be made.
 */
public class TileFactory {

    private static HashMap<String, Tile> tileTypes = new HashMap<>();

    private TileFactory() {
    }

    /**
     * Create tile given the terrain ID.  Uses the flyweight pattern to make tiles cheap.
     *
     * @param terrainId id of terrain to get a tile for
     * @return new tile
     */
    public static Tile createTile(String terrainId) {

        if (terrainId.isEmpty()) {
            terrainId = "clear:1";
        }

        if (!tileTypes.containsKey(terrainId)) {
            switch (terrainId) {
                case "clear:1":
                    tileTypes.put("clear:1", new Tile(terrainId, "Clear", 0, 0));
                    break;
                case "woods:1":
                    tileTypes.put(terrainId, new Tile(terrainId, "Light Woods", 1, 1));
                    break;
                case "woods:2":
                    tileTypes.put(terrainId, new Tile(terrainId, "Heavy Woods", 2, 2));
                    break;
            }
        }

        return tileTypes.get(terrainId);
    }

}
