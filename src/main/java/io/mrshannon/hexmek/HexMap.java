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
        tiles = new Tile[width*height];
        for (int w = 0; w < width; ++w) {
            for (int h = 0; h < height; ++w) {
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
        for (var entry : tiles.entrySet()) {
            if (isHexValid(entry.getKey())) {
                this.tiles[entry.getKey().getColumn() + width*entry.getKey().getRow()] = entry.getValue();
            }
        }
    }

    /**
     * Get terrain tile at the given coordinate.
     *
     * @param coordinate coordinate to retrieve terrain tile for
     * @return the terrain tile that is at the given coordinate
     */
    public Tile getTile(Hex coordinate) {
        checkHexBounds(coordinate);
        return tiles[coordinate.getColumn() + width*coordinate.getRow()];
    }

    /**
     * Determine if a coordinate is valid (within the map).
     *
     * @param coordinate coordinate to check
     * @return true, if the given coordinate is within the map
     */
    public boolean isHexValid(Hex coordinate) {
        boolean heightInBounds = false;
        boolean widthInBounds = (0 < coordinate.getColumn()) && (coordinate.getColumn() <= width);
        // even column
        if ((coordinate.getColumn() & 1) == 0) {
            heightInBounds = (0 < coordinate.getRow()) && (coordinate.getRow() < height);
        } else { // odd column
            heightInBounds = (0 < coordinate.getRow()) && (coordinate.getRow() <= height);
        }
        return widthInBounds && heightInBounds;
    }

    /**
     * Check bounds of coordinate, throwing error if out of bounds.
     *
     * @param coordinate coordinate to check
     */
    private void checkHexBounds(Hex coordinate) {
        if (!isHexValid(coordinate)) {
            throw new IllegalArgumentException(
                    String.format("Hex coordinate (%s) is out of bounds.", coordinate.toString()));
        }
    }
}
