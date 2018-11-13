package io.mrshannon.hexmek;

import java.util.Iterator;

/**
 * Indirect fire strategy, unaffected by terrain.
 */
public class IndirectFireStrategy implements LineOfSightStrategy {

    @Override
    public int modifier(Iterator<Tile> tiles) {
        return 0;
    }

}
