package io.mrshannon.hexmek;

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
    public void apply(int distance, Hex hex) {
        hex.translateNorthEast(distance);
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
