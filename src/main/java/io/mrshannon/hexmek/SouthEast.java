package io.mrshannon.hexmek;


/**
 * South east direction.
 *
 * @see io.mrshannon.hexmek.Direction
 */
public class SouthEast implements Direction {

    @Override
    public Direction rotateRight() {
        return new South();
    }

    @Override
    public Direction rotateLeft() {
        return new NorthEast();
    }

    @Override
    public Hex apply(int distance, Hex hex) {
        return hex.southEast(distance);
    }

    @Override
    public String toString() {
        return "South East";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SouthEast;
    }
}
