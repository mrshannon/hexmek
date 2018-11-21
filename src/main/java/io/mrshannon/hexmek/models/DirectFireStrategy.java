package io.mrshannon.hexmek.models;


/**
 * Direct fire strategy, affected by terrain.
 */
public class DirectFireStrategy implements LineOfSightStrategy {

    @Override
    public int modifier(Iterable<Tile> tiles) {
        int sum = 0;
        boolean first = true;
        for (var tile : tiles) {
            if (first) {
                first = false;
            } else {
                sum += tile.getVisibilityCost();
            }
        }
        if (sum > MAX_VISIBILITY_MODIFIER) {
            return OUT_OF_SIGHT;
        }
        return sum;
    }

}
