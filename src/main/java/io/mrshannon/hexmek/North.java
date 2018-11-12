package io.mrshannon.hexmek;

/**
 * North direction.
 *
 * @see io.mrshannon.hexmek.Direction
 */
public class North implements Direction {

    @Override
    public Direction rotateRight() {
        return new NorthEast();
    }

    @Override
    public Direction rotateLeft() {
        return new NorthWest();
    }

    @Override
    public Hex apply(int distance, Hex hex) {
        return hex.north(distance);
    }

    @Override
    public String toString() {
        return "North";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof North;
    }
}
