package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordinate in a hexagonal grid (flat top/bottom).
 */
public class Coordinate {

    private int column;
    private int row;

    /**
     * Construct a hexagonal grid coordinate.
     *
     * @param column column number in hexagonal grid
     * @param row row number in hexagonal grid
     */
    public Coordinate(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Get column number of coordinate.
     *
     * @return column number
     */
    public int getColumn() {
        return column;
    }

    /**
     * Get row number of coordinate.
     *
     * @return row number
     */
    public int getRow() {
        return row;
    }

    /**
     * Get list of neighboring cell coordinates, starting at the cell north of the coordinate and rotating clockwise.
     *
     * @return list of neighboring coordinates
     */
    public List<Coordinate> neighbors() {
        var coordinates = new ArrayList<Coordinate>();
        if ((column & 1) == 0) { // even
            coordinates.add(new Coordinate(column, row - 1)); // north
            coordinates.add(new Coordinate(column + 1, row)); // north east
            coordinates.add(new Coordinate(column + 1, row + 1)); // south east
            coordinates.add(new Coordinate(column, row + 1)); // south
            coordinates.add(new Coordinate(column - 1, row + 1)); // south west
            coordinates.add(new Coordinate(column - 1, row)); // north west
        } else { // odd
            coordinates.add(new Coordinate(column, row - 1)); // north
            coordinates.add(new Coordinate(column + 1, row - 1)); // north east
            coordinates.add(new Coordinate(column + 1, row)); // south east
            coordinates.add(new Coordinate(column, row + 1)); // south
            coordinates.add(new Coordinate(column - 1, row)); // south west
            coordinates.add(new Coordinate(column - 1, row - 1)); // north west
        }
        return coordinates;
    }

    /**
     * Determine if two coordinates are the same.
     *
     * @param other other coordinate to compare to
     * @return true if both coordinates are the same
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Coordinate) {
            return (column == ((Coordinate) other).column) && (row == ((Coordinate) other).row);
        }
        return false;
    }

    /**
     * Convert coordinate to a string.
     * @return a string representing the coordinate
     */
    public String toString() {
        return String.format("%02d%02d", column, row);
    }
}
