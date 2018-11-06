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
     * Use direction to propagate a coordinate by 1 unit.
     *
     * @param hex coordinate to propagate
     */
    default void apply(Hex hex) {
        apply(1, hex);
    }

    /**
     * Use direction to propagate a coordinate by the given distance.
     *
     * @param distance distance to propagate the coordinate
     * @param hex coordinate to propagate
     */
    void apply(int distance, Hex hex);
}
