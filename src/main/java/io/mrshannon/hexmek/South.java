package io.mrshannon.hexmek;

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
    public void apply(int distance, Hex hex) {
        hex.translateSouth(distance);
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
