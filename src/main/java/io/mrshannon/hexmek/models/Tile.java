package io.mrshannon.hexmek.models;

/**
 * A terrain tile that controls movement and visibility cost.
 */
public class Tile {

    private String id;
    private String name;
    private int movementCost;
    private int visibilityCost;

    /**
     * Construct a tile, this should never be used, it is here for sole use by the TileFactory.
     *
     * @param id terrain ID
     * @param name name of tile
     * @param movementCost movement point cost to traverse tile
     * @param visibilityCost attack/visibility modifier
     */
    Tile(String id, String name, int movementCost, int visibilityCost) {
        this.id = id;
        this.name = name;
        this.movementCost = movementCost;
        this.visibilityCost = visibilityCost;
    }

    /**
     * Get the ID of the terrain tile.
     *
     * @return id of terrain
     */
    public String getId() {
        return id;
    }

    /**
     * Get name of tile.
     *
     * @return name of terrain
     */
    public String getName() {
        return name;
    }

    /**
     * Get cost to move into the tile.
     *
     * @return movement cost
     */
    public int getMovementCost() {
        return movementCost;
    }

    /**
     * Get cost to attack modifier if shooting through or into the tile.
     *
     * @return visibility/attack modifier
     */
    public int getVisibilityCost() {
        return visibilityCost;
    }

    /**
     * Get string representation of terrain tile.
     *
     * @return string representation
     */
    public String toString() {
        return name;
    }
}
