package io.mrshannon.hexmek.models;

/**
 * Weapon range specification and range modifier calculations.
 */
public class WeaponRange {

    private int minimumRange;
    private int shortRange;
    private int mediumRange;
    private int longRange;

    /**
     * Out of range modifier value, guaranteed to be at least 4096.
     */
    public static final int OUT_OF_RANGE = Integer.MAX_VALUE/1024;

    /**
     * Construct a weapon range for a weapon with a minimum range.
     *
     * @param minimumRange minimum range of weapon
     * @param shortRange short range of weapon
     * @param mediumRange medium range of weapon
     * @param longRange long range of weapon
     */
    public WeaponRange(int minimumRange, int shortRange, int mediumRange, int longRange) {
        this.minimumRange = minimumRange;
        this.shortRange = shortRange;
        this.mediumRange = mediumRange;
        this.longRange = longRange;
    }

    /**
     * Construct a weapon range for a weapon without a minimum range.
     *
     * @param shortRange short range of weapon
     * @param mediumRange medium range of weapon
     * @param longRange long range of weapon
     */
    public WeaponRange(int shortRange, int mediumRange, int longRange) {
        this(0, shortRange, mediumRange, longRange);
    }

    /**
     * Get the minimum range of the weapon.
     *
     * @return minimum range of weapon
     */
    public int getMinimumRange() {
        return minimumRange;
    }

    /**
     * Get the short range of the weapon.
     *
     * @return short range of weapon
     */
    public int getShortRange() {
        return shortRange;
    }

    /**
     * Get the medium range of the weapon.
     *
     * @return medium range of weapon
     */
    public int getMediumRange() {
        return mediumRange;
    }

    /**
     * Get the long range of the weapon.
     *
     * @return long range of weapon
     */
    public int getLongRange() {
        return longRange;
    }

    /**
     * Determine if something is out of range, beyond long range.
     *
     * @param range range to target, not including starting hex
     * @return true if range is too great
     */
    public boolean outOfRange(int range) {
        return range > longRange;
    }

    /**
     * Calculate the range modifier for the given range.
     *
     * @param range range to target, not including starting hex
     * @return range modifier for the weapon and the given {@code range}, ({@code WeaponRange.OUT_OF_RANGE} if outside of range)
     */
    public int modifier(int range) {
        if (range <= minimumRange) {
            return minimumRange - range + 1;
        }
        if (range <= shortRange) {
            return 0;
        }
        if (range <= mediumRange) {
            return 2;
        }
        if (range <= longRange) {
            return 4;
        }
        return OUT_OF_RANGE;
    }

    /**
     * Compare two weapon ranges.
     *
     * @param other the other range to compare this range to
     * @return true if both ranges are the same
     */
    public boolean equals(Object other) {
        if (other instanceof WeaponRange) {
            return (minimumRange == ((WeaponRange) other).minimumRange) &&
                    (shortRange == ((WeaponRange) other).shortRange) &&
                    (mediumRange == ((WeaponRange) other).mediumRange) &&
                    (longRange == ((WeaponRange) other).longRange);
        }
        return false;
    }

    /**
     * Get string representation of the range.
     *
     * @return string representation of range
     */
    public String toString() {
        if (minimumRange == 0) {
            return String.format("short range = %s, medium range = %s, long range = %s",
                    shortRange, mediumRange, longRange);
        }
        return String.format("minimum range = %s, short range = %s, medium range = %s, long range = %s",
                minimumRange, shortRange, mediumRange, longRange);
    }
}
