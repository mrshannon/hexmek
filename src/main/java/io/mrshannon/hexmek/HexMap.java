package io.mrshannon.hexmek;

import java.util.Map;

/**
 * Class that represents rectangular map with hexagonal tiles.
 */
public class HexMap {

    private Tile tiles[];
    private int width;
    private int height;

    /**
     * Construct a map.
     *
     * @param width width of map
     * @param height height of map
     * @param baseTile base tile for map, all tiles will have this type
     */
    public HexMap(int width, int height, Tile baseTile) {
        if (width < 1) {
            throw new IllegalArgumentException("Map width must be positive.");
        }
        if (height < 1) {
            throw new IllegalArgumentException("Map height must be positive.");
        }
        this.width = width;
        this.height = height;
        tiles = new Tile[width*height];
        for (int w = 0; w < width; ++w) {
            for (int h = 0; h < height; ++h) {
                tiles[w + width*h] = baseTile;
            }
        }
    }

    /**
     * Construct map with special tiles.
     *
     * @param width width of map
     * @param height height of map
     * @param baseTile base tile for map, all tiles not mentioned in tiles will have this tile type
     * @param tiles special tiles to place on the map
     */
    public HexMap(int width, int height, Tile baseTile, Map<Hex, Tile> tiles) {
        this(width, height, baseTile);
        System.out.println(String.format("width = %d   height = %d", width, height));
        for (var entry : tiles.entrySet()) {
            if (isHexValid(entry.getKey())) {
                System.out.println(entry.getKey());
                this.tiles[entry.getKey().getColumn() - 1 + width*(entry.getKey().getRow() - 1)] = entry.getValue();
            }
        }
    }

    /**
     * Get width of map.
     *
     * @return width of map in hexes
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get height of map.
     *
     * @return height of map in hexes
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get terrain tile at the given hex.
     *
     * @param hex coordinate to retrieve terrain tile for
     * @return the terrain tile that is at the given hex
     */
    public Tile getTile(Hex hex) {
        checkHexBounds(hex);
        return tiles[hex.getColumn() - 1 + width*(hex.getRow() - 1)];
    }

    /**
     * Determine if a coordinate is valid (within the map).
     *
     * @param hex coordinate to check
     * @return true, if the given hex is within the map
     */
    public boolean isHexValid(Hex hex) {
        boolean heightInBounds = false;
        boolean widthInBounds = (0 < hex.getColumn()) && (hex.getColumn() <= width);
        // even column
        if ((hex.getColumn() & 1) == 0) {
            heightInBounds = (0 < hex.getRow()) && (hex.getRow() < height);
        } else { // odd column
            heightInBounds = (0 < hex.getRow()) && (hex.getRow() <= height);
        }
        return widthInBounds && heightInBounds;
    }

    /**
     * Check bounds of coordinate, throwing error if out of bounds.
     *
     * @param hex coordinate to check
     * @throws IndexOutOfBoundsException if given coordinate is out of bounds
     */
    private void checkHexBounds(Hex hex) {
        if (!isHexValid(hex)) {
            throw new IndexOutOfBoundsException(
                    String.format("Hex coordinate (%s) is out of bounds.", hex.toString()));
        }
    }
}
