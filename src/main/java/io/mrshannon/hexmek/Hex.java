package io.mrshannon.hexmek;

import java.util.ArrayList;

/**
 * Represents a coordinate in a hexagonal grid.
 *
 * This is an immutable type.  This is not exactly typical object oriented design, but because this is a type that is
 * typically passed around as a data type it is convenient to make this immutable so getters do not accidentally expose
 * a mutable state.
 *
 * Based on: https://www.redblobgames.com/grids/hexagons/
 */
public class Hex {

    private int q;
    private int r;
    private int s;

    /**
     * Construct hex coordinate from column and row.
     *
     * @param column column number
     * @param row row number
     */
    public Hex(int column, int row) {
        q = column;
        r = row - (int)(column + (column & 1))/2;
        s = -q - r;
    }

    /**
     * Copy constructor.
     *
     * @param other coordinate to copy
     */
    public Hex(Hex other) {
        this.q = other.q;
        this.r = other.r;
        this.s = other.s;
    }

    /**
     * Construct coordinate from raw cube coordinate components.
     *
     * @param q first cube coordinate
     * @param r second cube coordinate
     * @param s third cube coordinate
     */
    private Hex(int q, int r, int s) {
        this.q = q;
        this.r = r;
        this.s = s;
    }

    /**
     * Get column of coordinate.
     *
     * @return column number
     */
    public int getColumn() {
        return q;
    }

    /**
     * Get row of coordinate.
     *
     * @return row number
     */
    public int getRow() {
        return r + (int)(q + (q & 1))/2;
    }

    /**
     * Add another coordinate to this one and return the result
     *
     * @param other coordinate to add to this one
     * @return new hex that is the sum of this hex and the other
     */
    public Hex add(Hex other) {
        return new Hex(q + other.q, r + other.r, s + other.s);
    }

    /**
     * Get coordinate that is directly north of of this hex.
     *
     * @return new hex, directly north of this hex
     */
    public Hex north() {
        return north(1);
    }

    /**
     * Get coordinate that is north of this hex by a given distance.
     *
     * @param distance distance to new hex, can be negative
     * @return new hex, {@code distance} north of this hex
     */
    public Hex north(int distance) {
        return new Hex(q, r - distance, s + distance);
    }

    /**
     * Get coordinate that is directly north east of of this hex.
     *
     * @return new hex, directly north east of this hex
     */
    public Hex northEast() {
        return northEast(1);
    }

    /**
     * Get coordinate that is north east of this hex by a given distance.
     *
     * @param distance distance to new hex, can be negative
     * @return new hex, {@code distance} north east of this hex
     */
    public Hex northEast(int distance) {
        return new Hex(q + distance, r - distance, s);
    }

    /**
     * Get coordinate that is directly south east of of this hex.
     *
     * @return new hex, directly south east of this hex
     */
    public Hex southEast() {
        return southEast(1);
    }

    /**
     * Get coordinate that is south east of this hex by a given distance.
     *
     * @param distance distance to new hex, can be negative
     * @return new hex, {@code distance} south east of this hex
     */
    public Hex southEast(int distance) {
        return new Hex(q + distance, r, s - distance);
    }

    /**
     * Get coordinate that is directly south of of this hex.
     *
     * @return new hex, directly south of this hex
     */
    public Hex south() {
        return south(1);
    }

    /**
     * Get coordinate that is south of this hex by a given distance.
     *
     * @param amount distance to new hex, can be negative
     * @return new hex, amount south of this hex
     */
    public Hex south(int amount) {
        return new Hex(q, r + amount, s - amount);
    }

    /**
     * Get coordinate that is directly south west of of this hex.
     *
     * @return new hex, directly south west of this hex
     */
    public Hex southWest() {
        return southWest(1);
    }

    /**
     * Get coordinate that is south east of this hex by a given distance.
     *
     * @param distance distance to new hex, can be negative
     * @return new hex, {@code distance} south east of this hex
     */
    public Hex southWest(int distance) {
        return new Hex(q - distance, r + distance, s);
    }

    /**
     * Get coordinate that is directly south west of of this hex.
     *
     * @return new hex, directly south west of this hex
     */
    public Hex northWest() {
        return northWest(1);
    }

    /**
     * Get coordinate that is south east of this hex by a given distance.
     *
     * @param refactor distance to new hex, can be negative
     * @return new hex, refactor south east of this hex
     */
    public Hex northWest(int refactor) {
        return new Hex(q - refactor, r, s + refactor);
    }

    /**
     * Get iterable to neighboring cell coordinates, starting at the cell north of the coordinate and rotating
     * clockwise.
     *
     * @return iterable to neighboring coordinates
     */
    public Iterable<Hex> neighbors() {
        var coordinates = new ArrayList<Hex>();
        coordinates.add(north(1));
        coordinates.add(northEast(1));
        coordinates.add(southEast(1));
        coordinates.add(south(1));
        coordinates.add(southWest(1));
        coordinates.add(northWest(1));
        return coordinates;
    }

    /**
     * Compute distance to another coordinate.
     *
     * @param other coordinate to calculate distance to
     * @return distance to other coordinate
     */
    public int distanceTo(Hex other) {
        return (Math.abs(q - other.q) + Math.abs(r - other.r) + Math.abs(s - other.s))/2;
    }

    /**
     * Get iterable to coordinates from this coordinate to another coordinate.
     *
     * @param other the coordinate at the other end of the line
     * @return iterable to coordinates along the line, from start to end (the other coordinate)
     */
    public Iterable<Hex> lineTo(Hex other) {
        int n = this.distanceTo(other);
        var results = new ArrayList<Hex>();

        for (int i = 0; i <= n; ++i) {

            double qLerp = q + (other.q - q) * 1.0/n * i;
            double rLerp = r + (other.r - r) * 1.0/n * i;
            double sLerp = s + (other.s - s) * 1.0/n * i;

            long qInt = Math.round(qLerp);
            long rInt = Math.round(rLerp);
            long sInt = Math.round(sLerp);

            double qDiff = Math.abs(qInt - qLerp);
            double rDiff = Math.abs(qInt - qLerp);
            double sDiff = Math.abs(qInt - qLerp);

            if ((qDiff > rDiff) && (qDiff > sDiff)) {
                qInt = -rInt - sInt;
            } else if (rInt > sInt) {
                rInt = -qInt - sInt;
            } else {
                sInt = -qInt - rInt;
            }

            results.add(new Hex((int) qInt, (int) rInt, (int) sInt));
        }

        return results;
    }

    /**
     * Determine if two coordinates are the same.
     *
     * @param other other coordinate to compare to
     * @return true if both coordinates are the same
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Hex) {
            Hex coordinate = (Hex) other;
            return (q == coordinate.q) && (r == coordinate.r) && (s == coordinate.s);
        }
        return false;
    }

    /**
     * Get hashcode for the coordinate.
     *
     * @return hash code for coordinate.
     */
    @Override
    public int hashCode() {
        return (getColumn() << 16) + getRow();
    }

    /**
     * Convert coordinate to a string.
     * @return a string representing the coordinate
     */
    @Override
    public String toString() {
        return String.format("%d,%d", getColumn(), getRow());
    }
}
