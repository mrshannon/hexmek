package io.mrshannon.hexmek;

/**
 * North west direction.
 *
 * @see io.mrshannon.hexmek.Direction
 */
public class NorthWest implements Direction {

    @Override
    public Direction rotateRight() {
        return new North();
    }

    @Override
    public Direction rotateLeft() {
        return new SouthWest();
    }

    @Override
    public Hex apply(int distance, Hex hex) {
        return hex.northWest(distance);
    }

    @Override
    public String toString() {
        return "North West";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NorthWest;
    }
}
