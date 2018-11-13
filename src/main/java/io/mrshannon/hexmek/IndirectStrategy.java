package io.mrshannon.hexmek;

/**
 * Indirect fire strategy, unaffected by terrain.
 */
public class IndirectStrategy implements LineOfSightStrategy {

    @Override
    public int modifier(Iterable<Tile> tiles) {
        return 0;
    }

}
