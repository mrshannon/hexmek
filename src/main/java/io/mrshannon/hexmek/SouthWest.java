package io.mrshannon.hexmek;


/**
 * South west direction.
 *
 * @see io.mrshannon.hexmek.Direction
 */
public class SouthWest implements Direction {

    @Override
    public Direction rotateRight() {
        return new NorthWest();
    }

    @Override
    public Direction rotateLeft() {
        return new South();
    }

    @Override
    public Hex apply(int distance, Hex hex) {
        return hex.southWest(distance);
    }

    @Override
    public String toString() {
        return "South West";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SouthWest;
    }
}
