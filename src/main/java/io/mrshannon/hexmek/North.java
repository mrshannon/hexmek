package io.mrshannon.hexmek;

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
    public void apply(int distance, Hex hex) {
        hex.translateNorth(distance);
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
