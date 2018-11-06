package io.mrshannon.hexmek;

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
    public void apply(int distance, Hex hex) {
        hex.translateSouthEast(distance);
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
