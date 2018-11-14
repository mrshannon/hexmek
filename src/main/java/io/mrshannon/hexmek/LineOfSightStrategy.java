package io.mrshannon.hexmek;

/**
 * Line of sight strategy, to compute the line of sight modifier.
 *
 * @see DirectFireStrategy
 * @see IndirectFireStrategy
 */
public interface LineOfSightStrategy {

    /**
     * Out of sight modifier value, guaranteed to be at least 4096.
     */
    public static final int OUT_OF_SIGHT = Integer.MAX_VALUE/1024;

    /**
     * Maximum visibility modifier that still maintains line of sight.
     */
    public static final int MAX_VISIBILITY_MODIFIER = 2;

    /**
     * Get line of sight modifier to target.
     * @param tiles iterable of tiles from attacking unit (inclusive) to target unit (inclusive)
     * @return line of sight modifier, {@code LineOfSightStrategy.OUT_OF_SIGHT} if target cannot be seen
     */
    int modifier(Iterable<Tile> tiles);

}
