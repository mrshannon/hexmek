package io.mrshannon.hexmek;

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
    public void apply(int distance, Hex hex) {
        hex.translateSouthWest(distance);
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
