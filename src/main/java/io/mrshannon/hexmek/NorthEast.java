package io.mrshannon.hexmek;

/**
 * North east direction.
 *
 * @see io.mrshannon.hexmek.Direction
 */
public class NorthEast implements Direction {

    @Override
    public Direction rotateRight() {
        return new SouthEast();
    }

    @Override
    public Direction rotateLeft() {
        return new North();
    }

    @Override
    public Hex apply(int distance, Hex hex) {
        return hex.northEast(distance);
    }

    @Override
    public String toString() {
        return "North East";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NorthEast;
    }
}
