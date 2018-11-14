package io.mrshannon.hexmek;

/**
 * Indirect fire strategy, unaffected by terrain.
 */
public class IndirectFireStrategy implements LineOfSightStrategy {

    @Override
    public int modifier(Iterable<Tile> tiles) {
        return 0;
    }

}
