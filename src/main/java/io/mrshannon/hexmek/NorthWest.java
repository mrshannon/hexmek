package io.mrshannon.hexmek;

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
    public void apply(int distance, Hex hex) {
        hex.translateNorthWest(distance);
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
