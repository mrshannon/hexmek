package io.mrshannon.hexmek;

/**
 * South direction.
 *
 * @see io.mrshannon.hexmek.Direction
 */
public class South implements Direction {

    @Override
    public Direction rotateRight() {
        return new SouthWest();
    }

    @Override
    public Direction rotateLeft() {
        return new SouthEast();
    }

    @Override
    public Hex apply(int distance, Hex hex) {
        return hex.south(distance);
    }

    @Override
    public String toString() {
        return "South";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof South;
    }
}
