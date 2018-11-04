package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a coordinate in a hexagonal grid.
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
     * Add another coordinate to this one.
     *
     * @param other coordinate to add
     */
    public void add(Hex other) {
        q += other.q;
        r += other.r;
        s += other.s;
    }

    /**
     * Translate the coordinate north by a given distance.
     *
     * @param amount distance to translate
     */
    public void translateNorth(int amount) {
        r -= amount;
        s += amount;
    }

    /**
     * Translate the coordinate north-east by a given distance.
     *
     * @param amount distance to translate
     */
    public void translateNorthEast(int amount) {
        q += amount;
        r -= amount;
    }

    /**
     * Translate the coordinate south-east by a given distance.
     *
     * @param amount distance to translate
     */
    public void translateSouthEast(int amount) {
        q += amount;
        s -= amount;
    }

    /**
     * Translate the coordinate south by a given distance.
     *
     * @param amount distance to translate
     */
    public void translateSouth(int amount) {
        r += amount;
        s -= amount;
    }

    /**
     * Translate the coordinate south-est by a given distance.
     *
     * @param amount distance to translate
     */
    public void translateSouthWest(int amount) {
        q -= amount;
        r += amount;
    }

    /**
     * Translate the coordinate north-west by a given distance.
     *
     * @param amount distance to translate
     */
    public void translateNorthWest(int amount) {
        q -= amount;
        s += amount;
    }

    /**
     * Get neighbor to the north.
     *
     * @return northern neighbor coordinate
     */
    public Hex northNeighbor() {
        var neighbor = new Hex(this);
        neighbor.translateNorth(1);
        return neighbor;
    }

    /**
     * Get neighbor to the north-east.
     *
     * @return northeastern neighbor coordinate
     */
    public Hex northEastNeighbor() {
        var neighbor = new Hex(this);
        neighbor.translateNorthEast(1);
        return neighbor;
    }

    /**
     * Get neighbor to the south-east.
     *
     * @return southeastern neighbor coordinate
     */
    public Hex southEastNeighbor() {
        var neighbor = new Hex(this);
        neighbor.translateSouthEast(1);
        return neighbor;
    }

    /**
     * Get neighbor to the south.
     *
     * @return southern neighbor coordinate
     */
    public Hex southNeighbor() {
        var neighbor = new Hex(this);
        neighbor.translateSouth(1);
        return neighbor;
    }

    /**
     * Get neighbor to the south-west.
     *
     * @return southwestern neighbor coordinate
     */
    public Hex southWestNeighbor() {
        var neighbor = new Hex(this);
        neighbor.translateSouthWest(1);
        return neighbor;
    }

    /**
     * Get neighbor to the north-west.
     *
     * @return northwestern neighbor coordinate
     */
    public Hex northWestNeighbor() {
        var neighbor = new Hex(this);
        neighbor.translateNorthWest(1);
        return neighbor;
    }

    /**
     * Get list of neighboring cell coordinates, starting at the cell north of the coordinate and rotating clockwise.
     *
     * @return list of neighboring coordinates
     */
    public List<Hex> neighbors() {
        var coordinates = new ArrayList<Hex>();
        coordinates.add(northNeighbor());
        coordinates.add(northEastNeighbor());
        coordinates.add(southEastNeighbor());
        coordinates.add(southNeighbor());
        coordinates.add(southWestNeighbor());
        coordinates.add(northWestNeighbor());
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
     * Get list of coordinates from this coordinate to another coordinate.
     *
     * @param other the coordinate at the other end of the line
     * @return list of coordinates along the line, from start to end (the other coordinate)
     */
    public List<Hex> lineTo(Hex other) {
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
