package io.mrshannon.hexmek;

/**
 * A direction in the hexagonal, flat top/bottom map.  Valid directions are "North", "North East", "South East",
 * "South", "South West", and "North West".
 */
public interface Direction {

    /**
     * Rotate the direction to the right.
     *
     * @return the new direction
     */
    Direction rotateRight();

    /**
     * Rotate the direction to the left.
     *
     * @return the new direction
     */
    Direction rotateLeft();

    /**
     * Use direction to propagate a coordinate by 1 unit, returning the new coordinate.
     *
     * @param hex coordinate to propagate
     * @return new hex, which is 1 unit in this direction
     */
    default Hex apply(Hex hex) {
        return apply(1, hex);
    }

    /**
     * Use direction to propagate a coordinate by the given distance, returning the new coordinate.
     *
     * @param distance distance to propagate the coordinate
     * @param hex coordinate to propagate
     * @return new hex, which is 1 unit in this direction
     */
    Hex apply(int distance, Hex hex);
}
