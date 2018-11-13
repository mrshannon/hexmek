package io.mrshannon.hexmek;

import java.util.Iterator;

/**
 * Line of sight strategy, to compute the line of sight modifier.
 *
 * @see io.mrshannon.hexmek.DirectFireStrategy
 * @see io.mrshannon.hexmek.IndirectFireStrategy
 */
public interface LineOfSightStrategy {

    /**
     * Out of sight modifier value, guaranteed to be at least 4096.
     */
    public static final int OUT_OF_SIGHT = Integer.MAX_VALUE/1024;

    /**
     * Get line of sight modifier to target.
     * @param tiles iterator of tiles from attacking unit (inclusive) to target unit (inclusive)
     * @return line of sight modifier, {@code LineOfSightStrategy.OUT_OF_SIGHT} if target cannot be seen
     */
    int modifier(Iterator<Tile> tiles);

}
