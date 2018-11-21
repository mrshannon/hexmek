package io.mrshannon.hexmek.models;

/**
 * Indirect fire strategy, unaffected by terrain.
 */
public class IndirectFireStrategy implements LineOfSightStrategy {

    @Override
    public int modifier(Iterable<Tile> tiles) {
        return 0;
    }

}
