package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a coordinate in a hexagonal grid using cube coordinates.
 *
 * Based on: https://www.redblobgames.com/grids/hexagons/
 */
public class CubeCoordinate {

    private int q;
    private int r;
    private int s;

    /**
     * Construct cube coordinate from raw cube coordinate components.
     *
     * @param q first cube coordinate
     * @param r second cube coordinate
     * @param s third cube coordinate
     */
    public CubeCoordinate(int q, int r, int s) {
        this.q = q;
        this.r = r;
        this.s = s;
    }

    /**
     * Construct a cube coordinate from an even-q offset coordinate.
     * @param coordinate even-q offset coordinate
     */
    public CubeCoordinate(OffsetCoordinate coordinate) {
        q = coordinate.getColumn();
        r = coordinate.getRow() - (coordinate.getColumn() + (coordinate.getColumn() & 1))/2;
        s = -q - r;
    }

    /**
     * Convert to an even-q offset coordinate.
     *
     * @return equivalent even-q offset coordinate.
     */
    public OffsetCoordinate offsetCoordinate() {
        return new OffsetCoordinate(q, r + (q + (q & 1))/2);
    }

    /**
     * Compute distance to another coordinate.
     *
     * @param other coordinate to calculate distance to
     * @return distance to other coordinate
     */
    public int distanceTo(CubeCoordinate other) {
        return (Math.abs(q - other.q) + Math.abs(r - other.r) + Math.abs(s - other.s))/2;
    }

    /**
     * Add a coordinate to this one.
     *
     * @param other coordinate to add
     */
    public void add(CubeCoordinate other) {
        q += other.q;
        r += other.r;
        s += other.s;
    }

    /**
     * Get list of coordinates from this coordinate to another coordinate.
     *
     * @param other the coordinate at the other end of the line
     * @return list of coordinates along the line, from start to end (the other coordinate)
     */
    public List<CubeCoordinate> lineTo(CubeCoordinate other) {
        int n = this.distanceTo(other);
        var results = new ArrayList<CubeCoordinate>();

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

            results.add(new CubeCoordinate((int) qInt, (int) rInt, (int) sInt));
        }

        return results;
    }
}
