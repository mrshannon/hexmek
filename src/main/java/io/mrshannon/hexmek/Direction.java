package io.mrshannon.hexmek;

/**
 * A direction in the hexagonal, flat top/bottom map.  Valid directions are "North", "North East", "South East",
 * "South", "South West", and "North West".
 */
public class Direction {

    /**
     * Direction enums.
     */
    public enum Enum {
        /**
         * North
         */
        NORTH,

        /**
         * North East
         */
        NORTHEAST,

        /**
         * South East
         */
        SOUTHEAST,

        /**
         * South
         */
        SOUTH,

        /**
         * South West
         */
        SOUTHWEST,

        /**
         * North West
         */
        NORTHWEST
    }

    private Enum direction;

    /**
     * Default constructor, initial direction is North.
     */
    public Direction() {
        direction = Enum.NORTH;
    }

    /**
     * Construct from the raw direction enum.
     *
     * @param direction direction enum
     */
    public Direction(Enum direction) {
        this.direction = direction;
    }

    /**
     * Copy constructor.
     *
     * @param other another direction to copy
     */
    public Direction(Direction other) {
        this.direction = other.direction;
    }

    /**
     * Get the underlying direction enum.
     *
     * @return current direction enum of the direction
     */
    public Enum getDirectionEnum() {
        return direction;
    }

    /**
     * Rotate the direction to the right.
     */
    public void rotateRight() {
        var values = Enum.values();
        direction = values[(direction.ordinal() + 1) % values.length];
    }

    /**
     * Rotate the direction to the left.
     */
    public void rotateLeft() {
        var values = Enum.values();
        int newOrdinal = (direction.ordinal() - 1);
        if (newOrdinal < 0) {
            newOrdinal += values.length;
        }
        direction = values[newOrdinal];
    }

    /**
     * Use direction to propagate a coordinate by one unit.
     *
     * @param coordinate coordinate to propagate
     * @return propagated coordinate
     */
    public Coordinate propagateCoordinate(Coordinate coordinate) {
        switch (direction) {
            case NORTH:
                return new Coordinate(coordinate.getColumn(), coordinate.getRow() - 1);
            case NORTHEAST:
                if ((coordinate.getColumn() & 1) == 0) { // even
                    return new Coordinate(coordinate.getColumn() + 1, coordinate.getRow());
                } else { // odd
                    return new Coordinate(coordinate.getColumn() + 1, coordinate.getRow() - 1);
                }
            case SOUTHEAST:
                if ((coordinate.getColumn() & 1) == 0) { // even
                    return new Coordinate(coordinate.getColumn() + 1, coordinate.getRow() + 1);
                } else { // odd
                    return new Coordinate(coordinate.getColumn() + 1, coordinate.getRow());
                }
            case SOUTH:
                return new Coordinate(coordinate.getColumn(), coordinate.getRow() + 1);
            case SOUTHWEST:
                if ((coordinate.getColumn() & 1) == 0) { // even
                    return new Coordinate(coordinate.getColumn() - 1, coordinate.getRow() + 1);
                } else { // odd
                    return new Coordinate(coordinate.getColumn() - 1, coordinate.getRow());
                }
            case NORTHWEST:
                if ((coordinate.getColumn() & 1) == 0) { // even
                    return new Coordinate(coordinate.getColumn() - 1, coordinate.getRow() );
                } else { // odd
                    return new Coordinate(coordinate.getColumn() - 1, coordinate.getRow() - 1);
                }
        }
        return null; // not possible to reach this
    }

    /**
     * Check if two directions are the same.
     *
     * @param other direction to compare to
     * @return true if both directions are the same
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Direction) {
            return direction.equals(((Direction) other).direction);
        }
        return false;
    }

    /**
     * Get string representing the direction.
     *
     * @return representative direction string
     */
    public String toString() {
        switch (direction) {
            case NORTH:
                return "North";
            case NORTHEAST:
                return "North East";
            case SOUTHEAST:
                return "South East";
            case SOUTH:
                return "South";
            case SOUTHWEST:
                return "South West";
            case NORTHWEST:
                return "North West";
        }
        // not reachable
        return "Invalid Direction";
    }

}